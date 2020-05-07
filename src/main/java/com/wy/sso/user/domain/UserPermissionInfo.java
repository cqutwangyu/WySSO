package com.wy.sso.user.domain;

/**
 * @author wangyu
 * @title: UserPermissionInfo
 * @projectName WyUpms
 * @description: TODO
 * @date 2020/5/6 16:39
 */
public class UserPermissionInfo {
    private Integer flowId;
    private Integer userId;
    private Integer roleId;
    private Integer sysId;
    private Integer depId;
    private Integer menuId;
    private Integer menuParentId;
    private Boolean query;
    private Boolean edit;
    private Boolean export;
    private Boolean impower;

    public Integer getFlowId() {
        return flowId;
    }

    public void setFlowId(Integer flowId) {
        this.flowId = flowId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getSysId() {
        return sysId;
    }

    public void setSysId(Integer sysId) {
        this.sysId = sysId;
    }

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getMenuParentId() {
        return menuParentId;
    }

    public void setMenuParentId(Integer menuParentId) {
        this.menuParentId = menuParentId;
    }

    public Boolean getQuery() {
        return query;
    }

    public void setQuery(Boolean query) {
        this.query = query;
    }

    public Boolean getEdit() {
        return edit;
    }

    public void setEdit(Boolean edit) {
        this.edit = edit;
    }

    public Boolean getExport() {
        return export;
    }

    public void setExport(Boolean export) {
        this.export = export;
    }

    public Boolean getImpower() {
        return impower;
    }

    public void setImpower(Boolean impower) {
        this.impower = impower;
    }
}
