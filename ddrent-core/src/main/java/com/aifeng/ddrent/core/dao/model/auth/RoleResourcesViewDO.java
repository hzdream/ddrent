/**
 * Copyright © 2018 aifeng club All rights reserved.
 *
 * @Package: com.aifeng.ddrent.core.dao.model.auth
 * @author imart·deng
 * @date 创建时间：2018/10/19 11:38
 * @version 1.0
 */
package com.aifeng.ddrent.core.dao.model.auth;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @ClassName: RoleResourcesViewDO
 * @Description: 角色资源视图
 * @author: imart·deng
 * @date: 2018/10/19 11:38 
 *
 */
public class RoleResourcesViewDO {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 角色编号
     */
    @Column(name = "ROLE_ID")
    private Long roleId;

    /**
     * 用户编号
     */
    @Column(name = "RES_ID")
    private Long resId;

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

    //Role 信息
    /**
     * 角色名字
     */
    @Column(name = "ROLE_NAME")
    private String roleName;

    /**
     * 角色登记0、超级管理员、1租客、2房东、经纪人
     */
    @Column(name = "ROLE_LEVEL")
    private Integer roleLevel;

    /**
     * 父级角色
     */
    @Column(name = "PRE_ROLE")
    private Long preRole;

    /**
     * 角色结构链
     */
    @Column(name = "ROLE_STRUCTURE")
    private String roleStructure;

    @Column(name = "ROLE_CODE")
    private String roleCode;

    //资源数据
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getResId() {
        return resId;
    }

    public void setResId(Long resId) {
        this.resId = resId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(Integer roleLevel) {
        this.roleLevel = roleLevel;
    }

    public Long getPreRole() {
        return preRole;
    }

    public void setPreRole(Long preRole) {
        this.preRole = preRole;
    }

    public String getRoleStructure() {
        return roleStructure;
    }

    public void setRoleStructure(String roleStructure) {
        this.roleStructure = roleStructure;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public Integer getResLevel() {
        return resLevel;
    }

    public void setResLevel(Integer resLevel) {
        this.resLevel = resLevel;
    }

    public Long getPreResId() {
        return preResId;
    }

    public void setPreResId(Long preResId) {
        this.preResId = preResId;
    }

    public String getResUri() {
        return resUri;
    }

    public void setResUri(String resUri) {
        this.resUri = resUri;
    }

    public String getResIcon() {
        return resIcon;
    }

    public void setResIcon(String resIcon) {
        this.resIcon = resIcon;
    }

    public Integer getResSortCode() {
        return resSortCode;
    }

    public void setResSortCode(Integer resSortCode) {
        this.resSortCode = resSortCode;
    }

    public Integer getResType() {
        return resType;
    }

    public void setResType(Integer resType) {
        this.resType = resType;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public String getResStructure() {
        return resStructure;
    }

    public void setResStructure(String resStructure) {
        this.resStructure = resStructure;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getDisplayType() {
        return displayType;
    }

    public void setDisplayType(Integer displayType) {
        this.displayType = displayType;
    }

    public Integer getMethodType() {
        return methodType;
    }

    public void setMethodType(Integer methodType) {
        this.methodType = methodType;
    }

    public Integer getResUriType() {
        return resUriType;
    }

    public void setResUriType(Integer resUriType) {
        this.resUriType = resUriType;
    }

    public String getResUseType() {
        return resUseType;
    }

    public void setResUseType(String resUseType) {
        this.resUseType = resUseType;
    }
}
