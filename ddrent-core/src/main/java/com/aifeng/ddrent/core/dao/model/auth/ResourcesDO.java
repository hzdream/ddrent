package com.aifeng.ddrent.core.dao.model.auth;

import java.util.Date;
import javax.persistence.*;

import com.aifeng.ddrent.core.dao.model.BaseDOI;

@Table(name = "ddrent_resources")
public class ResourcesDO implements BaseDOI {
    /**
     * 主键
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 资源名称
     */
    @Column(name = "RES_NAME")
    private String resName;

    /**
     * 资源等级
     */
    @Column(name = "RES_LEVEL")
    private Integer resLevel;

    /**
     * 前置资源id
     */
    @Column(name = "PRE_RES_ID")
    private Long preResId;

    /**
     * uri资源链接
     */
    @Column(name = "RES_URI")
    private String resUri;

    /**
     * 资源图标
     */
    @Column(name = "RES_ICON")
    private String resIcon;

    /**
     * 资源排序编号
     */
    @Column(name = "RES_SORT_CODE")
    private Integer resSortCode;

    /**
     * 资源类型，0：通用型、1：网站（直接登录型）、2：第三方认证型（微信企业号）
     */
    @Column(name = "RES_TYPE")
    private Integer resType;

    /**
     * 是否激活
     */
    @Column(name = "IS_ACTIVE")
    private Integer isActive;

    /**
     * 资源层级信息，上下层级间以“_”分隔
     */
    @Column(name = "RES_STRUCTURE")
    private String resStructure;

    /**
     * 资源类型 1.菜单 2.操作
     */
    @Column(name = "TYPE_")
    private Integer type;

    /**
     * 展示类型：1.显示 2.隐藏
     */
    @Column(name = "DISPLAY_TYPE")
    private Integer displayType;

    /**
     *  0:代表UNKNOW; 1:代表GET; 2:代表POST; 3:代表PUT; 4:代表PATCH; 5:代表DELETE; 6:代表HEAD; 7:代表OPTIONS; 8:代表TRACE;
     */
    @Column(name = "METHOD_TYPE")
    private Integer methodType;

    /**
     * 0:常规，1:正则
     */
    @Column(name = "RES_URI_TYPE")
    private Integer resUriType;

    /**
     * 资源使用类型COMMON.所有人可用、SPECIAL.特定人可用
     */
    @Column(name = "RES_USE_TYPE")
    private String resUseType;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    /**
     * 获取主键
     *
     * @return ID - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取资源名称
     *
     * @return RES_NAME - 资源名称
     */
    public String getResName() {
        return resName;
    }

    /**
     * 设置资源名称
     *
     * @param resName 资源名称
     */
    public void setResName(String resName) {
        this.resName = resName;
    }

    /**
     * 获取资源等级
     *
     * @return RES_LEVEL - 资源等级
     */
    public Integer getResLevel() {
        return resLevel;
    }

    /**
     * 设置资源等级
     *
     * @param resLevel 资源等级
     */
    public void setResLevel(Integer resLevel) {
        this.resLevel = resLevel;
    }

    /**
     * 获取前置资源id
     *
     * @return PRE_RES_ID - 前置资源id
     */
    public Long getPreResId() {
        return preResId;
    }

    /**
     * 设置前置资源id
     *
     * @param preResId 前置资源id
     */
    public void setPreResId(Long preResId) {
        this.preResId = preResId;
    }

    /**
     * 获取uri资源链接
     *
     * @return RES_URI - uri资源链接
     */
    public String getResUri() {
        return resUri;
    }

    /**
     * 设置uri资源链接
     *
     * @param resUri uri资源链接
     */
    public void setResUri(String resUri) {
        this.resUri = resUri;
    }

    /**
     * 获取资源图标
     *
     * @return RES_ICON - 资源图标
     */
    public String getResIcon() {
        return resIcon;
    }

    /**
     * 设置资源图标
     *
     * @param resIcon 资源图标
     */
    public void setResIcon(String resIcon) {
        this.resIcon = resIcon;
    }

    /**
     * 获取资源排序编号
     *
     * @return RES_SORT_CODE - 资源排序编号
     */
    public Integer getResSortCode() {
        return resSortCode;
    }

    /**
     * 设置资源排序编号
     *
     * @param resSortCode 资源排序编号
     */
    public void setResSortCode(Integer resSortCode) {
        this.resSortCode = resSortCode;
    }

    /**
     * 获取资源类型，0：通用型、1：网站（直接登录型）、2：第三方认证型（微信企业号）
     *
     * @return RES_TYPE - 资源类型，0：通用型、1：网站（直接登录型）、2：第三方认证型（微信企业号）
     */
    public Integer getResType() {
        return resType;
    }

    /**
     * 设置资源类型，0：通用型、1：网站（直接登录型）、2：第三方认证型（微信企业号）
     *
     * @param resType 资源类型，0：通用型、1：网站（直接登录型）、2：第三方认证型（微信企业号）
     */
    public void setResType(Integer resType) {
        this.resType = resType;
    }

    /**
     * 获取是否激活
     *
     * @return IS_ACTIVE - 是否激活
     */
    public Integer getIsActive() {
        return isActive;
    }

    /**
     * 设置是否激活
     *
     * @param isActive 是否激活
     */
    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    /**
     * 获取资源层级信息，上下层级间以“_”分隔
     *
     * @return RES_STRUCTURE - 资源层级信息，上下层级间以“_”分隔
     */
    public String getResStructure() {
        return resStructure;
    }

    /**
     * 设置资源层级信息，上下层级间以“_”分隔
     *
     * @param resStructure 资源层级信息，上下层级间以“_”分隔
     */
    public void setResStructure(String resStructure) {
        this.resStructure = resStructure;
    }

    /**
     * 获取资源类型 1.菜单 2.操作
     *
     * @return TYPE - 资源类型 1.菜单 2.操作
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置资源类型 1.菜单 2.操作
     *
     * @param type 资源类型 1.菜单 2.操作
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取展示类型：1.显示 2.隐藏
     *
     * @return DISPLAY_TYPE - 展示类型：1.显示 2.隐藏
     */
    public Integer getDisplayType() {
        return displayType;
    }

    /**
     * 设置展示类型：1.显示 2.隐藏
     *
     * @param displayType 展示类型：1.显示 2.隐藏
     */
    public void setDisplayType(Integer displayType) {
        this.displayType = displayType;
    }

    /**
     * 获取 0:代表UNKNOW; 1:代表GET; 2:代表POST; 3:代表PUT; 4:代表PATCH; 5:代表DELETE; 6:代表HEAD; 7:代表OPTIONS; 8:代表TRACE;
     *
     * @return METHOD_TYPE -  0:代表UNKNOW; 1:代表GET; 2:代表POST; 3:代表PUT; 4:代表PATCH; 5:代表DELETE; 6:代表HEAD; 7:代表OPTIONS; 8:代表TRACE;
     */
    public Integer getMethodType() {
        return methodType;
    }

    /**
     * 设置 0:代表UNKNOW; 1:代表GET; 2:代表POST; 3:代表PUT; 4:代表PATCH; 5:代表DELETE; 6:代表HEAD; 7:代表OPTIONS; 8:代表TRACE;
     *
     * @param methodType  0:代表UNKNOW; 1:代表GET; 2:代表POST; 3:代表PUT; 4:代表PATCH; 5:代表DELETE; 6:代表HEAD; 7:代表OPTIONS; 8:代表TRACE;
     */
    public void setMethodType(Integer methodType) {
        this.methodType = methodType;
    }

    /**
     * 获取0:常规，1:正则
     *
     * @return RES_URI_TYPE - 0:常规，1:正则
     */
    public Integer getResUriType() {
        return resUriType;
    }

    /**
     * 设置0:常规，1:正则
     *
     * @param resUriType 0:常规，1:正则
     */
    public void setResUriType(Integer resUriType) {
        this.resUriType = resUriType;
    }

    /**
     * 获取资源使用类型COMMON.所有人可用、SPECIAL.特定人可用
     *
     * @return RES_USE_TYPE - 资源使用类型COMMON.所有人可用、SPECIAL.特定人可用
     */
    public String getResUseType() {
        return resUseType;
    }

    /**
     * 设置资源使用类型COMMON.所有人可用、SPECIAL.特定人可用
     *
     * @param resUseType 资源使用类型COMMON.所有人可用、SPECIAL.特定人可用
     */
    public void setResUseType(String resUseType) {
        this.resUseType = resUseType;
    }

    /**
     * 获取创建时间
     *
     * @return CREATE_TIME - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return UPDATE_TIME - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}