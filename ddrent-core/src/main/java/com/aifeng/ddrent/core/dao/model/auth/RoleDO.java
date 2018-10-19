package com.aifeng.ddrent.core.dao.model.auth;

import java.util.Date;
import javax.persistence.*;

import com.aifeng.ddrent.core.dao.model.BaseDOI;

@Table(name = "ddrent_role")
public class RoleDO implements BaseDOI {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "UPDATE_TIME")
    private Date updateTime;
    
    @Column(name = "ROLE_CODE")
    private String roleCode;
    
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
     * 获取角色名字
     *
     * @return ROLE_NAME - 角色名字
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色名字
     *
     * @param roleName 角色名字
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(Integer roleLevel) {
        this.roleLevel = roleLevel;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    /**
     * 获取父级角色
     *
     * @return PRE_ROLE - 父级角色
     */
    public Long getPreRole() {
        return preRole;
    }

    /**
     * 设置父级角色
     *
     * @param preRole 父级角色
     */
    public void setPreRole(Long preRole) {
        this.preRole = preRole;
    }

    /**
     * 获取角色结构链
     *
     * @return ROLE_STRUCTURE - 角色结构链
     */
    public String getRoleStructure() {
        return roleStructure;
    }

    /**
     * 设置角色结构链
     *
     * @param roleStructure 角色结构链
     */
    public void setRoleStructure(String roleStructure) {
        this.roleStructure = roleStructure;
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
}