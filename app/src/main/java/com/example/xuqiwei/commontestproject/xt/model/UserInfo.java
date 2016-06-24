package com.example.xuqiwei.commontestproject.xt.model;

import java.util.List;

/**
 * Created by xuqiwei on 16-6-23.
 */
public class UserInfo {

    /**
     * IsReginCenter : false
     * HospitalName : 苏州大学附属第二医院
     * LoginName : fe0a591
     * HospitalId : 6
     * IsInnerUser : false
     * UserName : FE0A591
     * UserId : FE0A591
     * AccessFuncInfoList : [{"Url":"监护信息","PermissionId":"PHEP_13","PermissionName":"监护信息"},{"Url":"调度统计","PermissionId":"PHEP_16","PermissionName":"调度统计"},{"Url":"急救资源发布","PermissionId":"PHEP_17","PermissionName":"急救资源发布"},{"Url":"急救人员","PermissionId":"PHEP_24","PermissionName":"急救人员"},{"Url":"大屏公告","PermissionId":"PHEP_12","PermissionName":"大屏公告"},{"Url":"心电图","PermissionId":"PHEP_14","PermissionName":"心电图"},{"Url":"健康档案","PermissionId":"PHEP_20","PermissionName":"健康档案"},{"Url":"多方视频通话","PermissionId":"PHEP_15","PermissionName":"多方视频通话"},{"Url":"急救资源预警","PermissionId":"PHEP_18","PermissionName":"急救资源预警"},{"Url":"医院管理","PermissionId":"PHEP_23","PermissionName":"医院管理"},{"Url":"车辆管理","PermissionId":"PHEP_22","PermissionName":"车辆管理"},{"Url":"急救患者统计","PermissionId":"PHEP_42","PermissionName":"急救患者统计"},{"Url":"患者管理","PermissionId":"PHEP_52","PermissionName":"患者管理"},{"Url":"移动急救病历","PermissionId":"PHEP_80","PermissionName":"移动急救病历"},{"Url":"病历管理","PermissionId":"PHEP_19","PermissionName":"病历管理"},{"Url":"急救任务","PermissionId":"PHEP_5","PermissionName":"急救任务"},{"Url":"统计报表","PermissionId":"PHEP_4","PermissionName":"统计报表"},{"Url":"出车情况统计","PermissionId":"PHEP_41","PermissionName":"出车情况统计"},{"Url":"腕带管理","PermissionId":"PHEP_25","PermissionName":"腕带管理"},{"Url":"业务日志","PermissionId":"PHEP_32","PermissionName":"业务日志"},{"Url":"专家辅助诊断","PermissionId":"PHEP_81","PermissionName":"专家辅助诊断"},{"Url":"车载终端","PermissionId":"PHEP_0","PermissionName":"车载终端"},{"Url":"指挥中心","PermissionId":"PHEP_1","PermissionName":"指挥中心"},{"Url":"急救资源","PermissionId":"PHEP_2","PermissionName":"急救资源"},{"Url":"设备管理","PermissionId":"PHEP_21","PermissionName":"设备管理"},{"Url":"基础设置","PermissionId":"PHEP_3","PermissionName":"基础设置"},{"Url":"药品管理","PermissionId":"PHEP_31","PermissionName":"药品管理"},{"Url":"指挥调度","PermissionId":"PHEP_11","PermissionName":"指挥调度"}]
     * DepartmentId : null
     * ApplicationId : PreHospitalCare
     */

    private boolean IsReginCenter;
    private String HospitalName;
    private String LoginName;
    private String HospitalId;
    private boolean IsInnerUser;
    private String UserName;
    private String UserId;
    private Object DepartmentId;
    private String ApplicationId;
    /**
     * Url : 监护信息
     * PermissionId : PHEP_13
     * PermissionName : 监护信息
     */

    private List<AccessFuncInfoListBean> AccessFuncInfoList;

    public boolean isIsReginCenter() {
        return IsReginCenter;
    }

    public void setIsReginCenter(boolean IsReginCenter) {
        this.IsReginCenter = IsReginCenter;
    }

    public String getHospitalName() {
        return HospitalName;
    }

    public void setHospitalName(String HospitalName) {
        this.HospitalName = HospitalName;
    }

    public String getLoginName() {
        return LoginName;
    }

    public void setLoginName(String LoginName) {
        this.LoginName = LoginName;
    }

    public String getHospitalId() {
        return HospitalId;
    }

    public void setHospitalId(String HospitalId) {
        this.HospitalId = HospitalId;
    }

    public boolean isIsInnerUser() {
        return IsInnerUser;
    }

    public void setIsInnerUser(boolean IsInnerUser) {
        this.IsInnerUser = IsInnerUser;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public Object getDepartmentId() {
        return DepartmentId;
    }

    public void setDepartmentId(Object DepartmentId) {
        this.DepartmentId = DepartmentId;
    }

    public String getApplicationId() {
        return ApplicationId;
    }

    public void setApplicationId(String ApplicationId) {
        this.ApplicationId = ApplicationId;
    }

    public List<AccessFuncInfoListBean> getAccessFuncInfoList() {
        return AccessFuncInfoList;
    }

    public void setAccessFuncInfoList(List<AccessFuncInfoListBean> AccessFuncInfoList) {
        this.AccessFuncInfoList = AccessFuncInfoList;
    }

    public static class AccessFuncInfoListBean {
        private String Url;
        private String PermissionId;
        private String PermissionName;

        public String getUrl() {
            return Url;
        }

        public void setUrl(String Url) {
            this.Url = Url;
        }

        public String getPermissionId() {
            return PermissionId;
        }

        public void setPermissionId(String PermissionId) {
            this.PermissionId = PermissionId;
        }

        public String getPermissionName() {
            return PermissionName;
        }

        public void setPermissionName(String PermissionName) {
            this.PermissionName = PermissionName;
        }
    }
}
