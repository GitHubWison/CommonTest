package com.example.commonlibrary.tools;

/**
 * Created by Nangy-Office on 2016/3/25.
 */
public class SPreference {
    public static class Login{
        public static String sp_name = "Login";
        public static String domain = "domain";
        /**
         * 车辆的名称
         */
        public static String carName = "CARNAME";
        /**
         * udp端口
         */
        public static String udpname = "UDPNAME";
        /**
         * 心电图地址
         */
        public static String ecgaddress = "ecgaddress";
        /**
         * 监护仪地址
         */
        public static String jhyaddress = "jhyaddress";
        /*
        推送地址
         */
        public static String tuisongaddress="tuisongaddress";
        /*
        推送端口
         */
        public static String tuisongport="tuisongport";
        /**
         * 用户信息
         */
        public static String userinfo = "USER_INFO";
        /**
         * 用户输入的用户名和密码
         */
        public static String userNameAndPwd = "USERNAMEPWD";
        /**
         * 是否记住用户名密码
         */
        public static String isRememberUserPass = "REMB_USER_PASS";


    }
    public static class First{
        public static String sp_name = "First";
        /**
         * 第一次安装程序
         */
        public static String isFirstInstall = "FIRST_INSTALL";
    }
    public static class Dimens{
        public static String sp_name = "Dimens";
        /**
         * actionbar的高度
         */
        public static String actionHeight = "ACTION_HEIGHT";
    }
    /**
     * 病患相关
     */
    public static class Patient{
        public static String sp_name = "Patient";
//        /**
//         * 病患的messionid
//         */
//        public static  String MessionId = "MESSION_ID";
//        /**
//         * 病患的patientId
//         */
//        public static String PatientId = "PATIENT_ID";
        public static String PatientInfo = "PATIENT_INFO";
        public static String PatientECG = "PatientECG";
        public static String canrecordedit = "canrecordedit";
    }
    public static class TaskList
    {
        public static String sp_name = "TaskList";
        public static String shouldShowTips = "SHOULDSHOW_TIPS";
        public static String mission = "mission";
        public static String isCurrentMissions = "isCurrentMissions";//yes:是当前任务；其他(no)：是历史任务
    }
    /*
    ECG相关
     */
    public static class ECGInfo
    {
        public static String sp_name = "ECGInfo";
        public static String ecgflag = "ecgflag";
        public static String ecginfo_json = "ecginfo_json";
    }
    public static class DictionaryItems
    {
        public static String sp_name = "DictionaryItems";
        public static String allDictionaryItems_json = "allDictionaryItems_json";
        public static String hospitalInfo_json = "hospitalInfo_json";
        /*
        救治人员
         */
        public static String rescuerpersons_json = "rescuerpersons_json";
        /**
         * 事件类型
         */
        public static String incident_json = "incident_json";
        /**
         * 腕带
         */
        public static String tagdict_json = "tagdict_json";
        /*
        治疗措施
         */
        public static String zlcs_json = "zlcs_json";
        /*
        病情判断
         */
        public static String bqpd_json = "bqpd_json";
        /*
        药品列表
         */
        public static String drugslist_json = "drugslist_json";

    }




}
