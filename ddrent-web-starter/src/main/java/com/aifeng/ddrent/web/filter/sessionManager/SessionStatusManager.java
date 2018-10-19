/**
 * Copyright © 2018 aifeng club All rights reserved.
 *
 * @Package: com.aifeng.ddrent.web.filter.sessionManager
 * @author imart·deng
 * @date 创建时间：2018/10/19 14:48
 * @version 1.0
 */
package com.aifeng.ddrent.web.filter.sessionManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: UserStatusManager
 * @Description: 用户会话信息管理
 * @author: imart·deng
 * @date: 2018/10/19 14:48 
 *
 */
public class SessionStatusManager {
    private static final Logger logger = LoggerFactory.getLogger(SessionStatusManager.class);

    /** 最近一次清除时间 */
    private volatile long lastClearMilli;

    /** 容器 */
    private static Map<String, Session> USER_STATUS = new ConcurrentHashMap<String, Session>(256);

    /** 失效时间 ,单位：秒*/
    private volatile static int INVALID_TIME = 60 * 60;

    /** 最大保存的过期时间 ，确保状态删除， 单位：秒*/
    private final static int SAVE_EXPIRED_TIME = 2 * 60 * 60;

    /** 单例 */
    private static volatile  SessionStatusManager userStatusManager;

    /** 清除任务 */
    private Runnable task;

    private SessionStatusManager() {
        super();
        task = initTask();
        int processors = Runtime.getRuntime().availableProcessors();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(processors);
        scheduler.scheduleAtFixedRate(task, 0, 30, TimeUnit.SECONDS);
    }

    private Runnable initTask() {
        task = new Runnable() {
            @Override
            public void run() {
                // 缓存表为空的时候不做任何操作
                if(USER_STATUS.isEmpty()) return ;

                /** 1、获取当前时间，相对创建时间应该失效时间，相对创建时间应该失效的缓存时间*/
                //当前时间*
                Calendar cal = Calendar.getInstance();
                Date now = cal.getTime();

                //相对创建时间应该失效时间（创建时间之后的60秒）
                cal.add(Calendar.SECOND, -INVALID_TIME);
                Date limitTime = cal.getTime();

                // 相对创建时间应该失效的缓存时间
                cal.add(Calendar.SECOND, INVALID_TIME - SAVE_EXPIRED_TIME);
                Date saveLimit = cal.getTime();

                // 更新缓存清理时间
                lastClearMilli = now.getTime();

                /** 2、查找是否有失效数据*/
                List<String> removedLis = new ArrayList<>(128);

                Session sessionInfo = null;
                Date insertTime = null;
                for (Map.Entry<String, Session> entry : USER_STATUS.entrySet()) {
                    sessionInfo = entry.getValue();
                    insertTime = sessionInfo.getInsertTime();

                    /** 如果超出了缓存时间，则先把超出缓存时间的记录清空*/
                    if(saveLimit.after(insertTime)) {
                        removedLis.add(entry.getKey());
                    }else {
                        /** 如果没有设置失效时间 */
                        if(null == sessionInfo.getDueTime()) {
                            if(limitTime.after(sessionInfo.getCreateTime())) {
                                removedLis.add(entry.getKey());
                            }
                        }
                        /** 设置了失效时间*/
                        else {
                            if(now.after(sessionInfo.getDueTime())) {
                                removedLis.add(entry.getKey());
                            }
                        }
                    }
                }

                if(!removedLis.isEmpty()) {
                    // 批量移除
                    remove(removedLis);
                    logger.info("[移除本地状态信息]， 移除token列表{}", removedLis.toString());
                }

            }
        };
        return task;
    }

    public static SessionStatusManager getInstance() {
        SessionStatusManager instance = userStatusManager;
        if(null == userStatusManager) {
            synchronized(USER_STATUS) {
                if(null == userStatusManager) {
                    userStatusManager = new SessionStatusManager();
                }
            }
        }
        return instance;
    }

    public <T> Session<T> add(String uniqueId, Date createTime, String key, T data) {
        Session sessionInfo = new Session(uniqueId, createTime, key, data);
        addSession(sessionInfo);
        return sessionInfo;
    }

    public <T> Session<T> add(String uniqueId, Date createTime, String key, Date duDate, T data) {
        Session sessionInfo = new Session(uniqueId, createTime, key, duDate, data);
        sessionInfo.setInsertTime(new Date());
        addSession(sessionInfo);
        return sessionInfo;
    }

    public Session get(String key) {
        return getSession(key);
    }

    public void remove(String key) {
        USER_STATUS.remove(key);
    }

    public void remove(List<String> keys) {
        if(null == keys || keys.isEmpty()) return;

        for (String key:keys) {
            USER_STATUS.remove(key);
        }
    }

    private Session getSession(String key) {

        /** 获取用户状态信息*/
        Session sessionInfo = USER_STATUS.get(key);

        if(null != sessionInfo) {
            boolean invalid = false ;

            /** 获取当前时间*/
            Calendar cal = Calendar.getInstance();
            Date now = cal.getTime();
            cal.add(Calendar.SECOND, -INVALID_TIME);
            Date limitTime = cal.getTime();

            /** 如果没有设置失效时间 */
            if(null == sessionInfo.getDueTime()) {
                if(limitTime.after(sessionInfo.getCreateTime())) {
                    remove(key);
                    invalid = true;
                }
            }
            /** 设置了失效时间*/
            else {
                if(now.after(sessionInfo.getDueTime())) {
                    remove(key);
                    invalid = true;
                }
            }

            if(invalid) {
                sessionInfo = null;
            }
        }

        return sessionInfo;
    }

    private void addSession(Session sessionInfo) {
        Session info = USER_STATUS.get(sessionInfo.getKey());

        /** 每日只允许插入最新时间的状态信息 */
        if(null == info || info.getCreateTime().before(sessionInfo.getCreateTime())) {
            USER_STATUS.put(sessionInfo.getKey(), sessionInfo);
        }
    }

    public long getLastClearMilli() {
        return lastClearMilli;
    }

    /**
     * 获取缓存大小
     * @return
     */
    public int getSize() {
        return USER_STATUS.size();
    }



    /**
     * 用户状态信息
     * @author dengxf
     *
     */
    public static class Session<T> {
        /** 状态唯一，用于却分唯一性 */
        private String uniqueId;

        /** 创建时间 ，用于更新最新的创建记录*/
        private Date createTime;

        /** 插入时间*/
        private Date insertTime;

        /** 预期时间*/
        private Date dueTime;

        /** 用于判定用户 */
        private String key;

        /** 用户姓名*/
        private String name;

        /** 存储对象 */
        private T data;

        /** 判定用户是否激活 */
        private boolean active;

        public Session(String uniqueId, Date createTime, String key, T data) {
            super();
            this.uniqueId = uniqueId;
            this.createTime = createTime;
            this.key = key;
            this.data = data;
        }

        public Session(String uniqueId, Date createTime, String key, Date dueTime, T data) {
            super();
            this.uniqueId = uniqueId;
            this.createTime = createTime;
            this.key = key;
            this.dueTime = dueTime;
            this.data = data;
        }

        public String getUniqueId() {
            return uniqueId;
        }

        public Session setUniqueId(String uniqueId) {
            this.uniqueId = uniqueId;
            return this;
        }

        public Date getCreateTime() {
            return createTime;
        }

        public Session setCreateTime(Date creatTime) {
            this.createTime = creatTime;
            return this;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isActive() {
            return active;
        }

        public Session setActive(boolean active) {
            this.active = active;
            return this;
        }

        public Date getDueTime() {
            return dueTime;
        }

        public Session setDueTime(Date dueTime) {
            this.dueTime = dueTime;
            return this;
        }

        public Date getInsertTime() {
            return insertTime;
        }

        public void setInsertTime(Date insertTime) {
            this.insertTime = insertTime;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Session{" +
                    "uniqueId='" + uniqueId + '\'' +
                    ", createTime=" + createTime +
                    ", insertTime=" + insertTime +
                    ", dueTime=" + dueTime +
                    ", key='" + key + '\'' +
                    ", name='" + name + '\'' +
                    ", data=" + data +
                    ", active=" + active +
                    '}';
        }
    }

    public static void main(String[] args) {
        SessionStatusManager userStatusManager = SessionStatusManager.getInstance();
        Calendar cal = Calendar.getInstance();
        userStatusManager.add("111", cal.getTime(), "dengxf", null).setActive(true);
        cal.add(Calendar.SECOND, 10);
        userStatusManager.add("222", cal.getTime(), "liangzx", null).setActive(true);
        cal.add(Calendar.SECOND, 10);
        userStatusManager.add("333", cal.getTime(), "cuimq", null).setActive(false);
        cal.add(Calendar.SECOND, 10);
        userStatusManager.add("444", cal.getTime(), "chensy", null).setActive(false);
    }
}
