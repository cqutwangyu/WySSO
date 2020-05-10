package com.wy.sso.user.domain;

import java.io.Serializable;

/**
 * @author wangyu
 * @title: RoleInfo
 * @projectName WyUpms
 * @description: TODO
 * @date 2020/4/21 14:38
 */
public class RoleInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String sysName;
    //db
    private Integer flowId;
    private Integer sysId;
    private Integer depId;
    private String roleName;
    private String createTime;

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public Integer getFlowId() {
        return flowId;
    }

    public void setFlowId(Integer flowId) {
        this.flowId = flowId;
    }

    public Integer getSysId() {
        return sysId;
    }

    public void setSysId(Integer sysId) {
        this.sysId = sysId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "RoleInfo{" +
                "flowId=" + flowId +
                ", sysId=" + sysId +
                ", roleName='" + roleName + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
