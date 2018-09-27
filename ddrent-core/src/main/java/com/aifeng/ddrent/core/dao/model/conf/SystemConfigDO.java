package com.aifeng.ddrent.core.dao.model.conf;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aifeng.ddrent.core.dao.model.BaseDOI;

@Table(name = "ddrent_system_config")
public class SystemConfigDO implements BaseDOI {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 前缀，也可以做为分组
     */
    @Column(name = "PRE_FIX_KEY")
    private String preFixKey;

    /**
     * 配置键值
     */
    @Column(name = "KEY_")
    private String key;

    /**
     * 配置值
     */
    @Column(name = "VALUE_")
    private String value;

    /**
     * 是否有效
     */
    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    /**
     * 额外字段1
     */
    @Column(name = "EX_VALUE1")
    private String exValue1;

    /**
     * 额外字段2
     */
    @Column(name = "EX_VALUE2")
    private String exValue2;

    /**
     * 额外字段3
     */
    @Column(name = "EX_VALUE3")
    private String exValue3;

    /**
     * 修改快照
     */
    @Column(name = "SNAPSHOT_")
    private String snapshot;
    
    /**
     * 描述
     */
    @Column(name = "DESCRIPTION_")
    private String description;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "UPDATE_TIME")
    private Date updateTime;
    
    /**
     * 是否加密
     */
    @Column(name = "IS_ENCODE")
    private Boolean isEncode;

    /**
     * @return ID
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取前缀，也可以做为分组
     *
     * @return PRE_FIX_KEY - 前缀，也可以做为分组
     */
    public String getPreFixKey() {
        return preFixKey;
    }

    /**
     * 设置前缀，也可以做为分组
     *
     * @param preFixKey 前缀，也可以做为分组
     */
    public void setPreFixKey(String preFixKey) {
        this.preFixKey = preFixKey;
    }

    /**
     * 获取配置键值
     *
     * @return KEY - 配置键值
     */
    public String getKey() {
        return key;
    }

    /**
     * 设置配置键值
     *
     * @param key 配置键值
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 获取配置值
     *
     * @return VALUE - 配置值
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置配置值
     *
     * @param value 配置值
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 获取是否有效
     *
     * @return IS_ACTIVE - 是否有效
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     * 设置是否有效
     *
     * @param isActive 是否有效
     */
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * 获取额外字段1
     *
     * @return EX_VALUE1 - 额外字段1
     */
    public String getExValue1() {
        return exValue1;
    }

    /**
     * 设置额外字段1
     *
     * @param exValue1 额外字段1
     */
    public void setExValue1(String exValue1) {
        this.exValue1 = exValue1;
    }

    /**
     * 获取额外字段2
     *
     * @return EX_VALUE2 - 额外字段2
     */
    public String getExValue2() {
        return exValue2;
    }

    /**
     * 设置额外字段2
     *
     * @param exValue2 额外字段2
     */
    public void setExValue2(String exValue2) {
        this.exValue2 = exValue2;
    }

    /**
     * 获取额外字段3
     *
     * @return EX_VALUE3 - 额外字段3
     */
    public String getExValue3() {
        return exValue3;
    }

    /**
     * 设置额外字段3
     *
     * @param exValue3 额外字段3
     */
    public void setExValue3(String exValue3) {
        this.exValue3 = exValue3;
    }

    /**
     * 获取修改快照
     *
     * @return SNAPSHOT - 修改快照
     */
    public String getSnapshot() {
        return snapshot;
    }

    /**
     * 设置修改快照
     *
     * @param snapshot 修改快照
     */
    public void setSnapshot(String snapshot) {
        this.snapshot = snapshot;
    }

    /**
     * @return CREATE_TIME
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return UPDATE_TIME
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
	/**
	 * @return the isEncode
	 */
	public Boolean getIsEncode() {
		return isEncode;
	}

	/**
	 * @param isEncode the isEncode to set
	 */
	public void setIsEncode(Boolean isEncode) {
		this.isEncode = isEncode;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SystemConfigDO [id=" + id + ", preFixKey=" + preFixKey + ", key=" + key + ", value=" + value
				+ ", isActive=" + isActive + ", exValue1=" + exValue1 + ", exValue2=" + exValue2 + ", exValue3="
				+ exValue3 + ", snapshot=" + snapshot + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ "]";
	}
}