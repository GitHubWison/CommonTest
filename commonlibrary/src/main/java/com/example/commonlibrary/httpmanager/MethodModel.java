package com.example.commonlibrary.httpmanager;

import android.content.Context;

import com.example.commonlibrary.staticstring.CacheName;
import com.example.commonlibrary.tools.SPreference;
import com.example.commonlibrary.tools.Tool;

/**
 * Created by xuqiwei-Office on 2016/3/24.
 * 网络请求的方法名
 */
public class MethodModel {

    /**
     * 检查更新用于测试
     */
    public static String checkForUpdate = "http://192.168.1.230:8888/version.xml";
    /*
    错误日志提交方法
     */
    public static String upLoadLog ="/api/Public/Log";

    public static String testConnection = "/api/TestNet/Get";
    public static String login = "/api/Public/CheckUserIsValid";
    /**
     * 拉取任务列表
     */
    public static String getTaskList = "/api/ChestEvaluate/GetCurrentMissions";
    public static String auth = "Mdsd.Phep.Api:mdsd.phep.api.2005$";

    /**
     * 拉取时间点记录状况
     */
    public static String getMission = "/api/ChestEvaluate/GetMissionById";

    /**
     *判断应该调到那个页面
     */
    public static String getWhereToJump = "/api/ChestEvaluate/GetTimePointByPatientId";


    public static String updateMission = "/api/ChestEvaluate/UpdateMission";

    /**
     * 判断是否有胸痛评估信息
     */
    public static String getChestEvaluateByPatientId = "/api/ChestEvaluate/GetChestEvaluateByPatientId";
    /**
     * 新增胸痛评估信息
     */
    public static String addChestEvaluate = "/api/ChestEvaluate/AddChestEvaluate";

    /**
     * 更新胸痛评估状态
     */
    public static String updateChestEvaluate = "/api/ChestEvaluate/UpdateChestEvaluate";

    /**
     * 查询首次给药
     */
    public static String getMedicalOrderByPatientId = "/api/ChestEvaluate/GetMedicalOrderByPatientId";
    /**
     * 添加首次给药
     */
    public static String addMedicalOrderByPatientId = "/api/ChestEvaluate/AddMedicalOrderByPatientId";
    /**
     * 更新首次给药
     */
    public static String updateMedicalOrderByPatientId = "/api/ChestEvaluate/UpdateMedicalOrderByPatientId";
    /**
     * 获取网络的时间
     */
    public static String getCurrentTime = "/api/Public/GetCurrentTime";

    //修改病历
    /**
     * 拉取spinner下拉列表的数据
     */
    public static String getalldictionaryItems ="/api/mobiledictionarysitems/getalldictionaryItems";

    /**
     * 医院信息
     */
    public static String getuseofhospitals = "/api/mobilehospital/getuseofhospitals";
    /**
     * 急救人员
     */
    public static String getrescuersbyhospitalid ="/api/mobilerescuers/getrescuersbyhospitalid";
    /**
     * 根据病人编号获取患者治疗措施信息
     */
    public static String gettherapeuticmethodbypatientid = "/api/mobiletherapeuticmethod/gettherapeuticmethodbypatientid";

    /**
     * 事件信息api/mobileincident/get
     */
    public static String getmobileincident ="/api/mobileincident/get";

    /**
     * 获取任务信息
     */
    public static String getmissionbyId ="/api/mobilemission/getmissionbyId";
    /*
    根据任务ID获取患者信息
     */
    public static String getpatientbymissionid = "/api/mobilepatient/getpatientbymissionid";

    /*
    新的获取任务列表
     */
    public static String getMissionbyvehicleid = "/api/mobilemission/getMissionbyvehicleid";
    /*
    获取腕带的字典
     */
//    public static String gettagdictbyhospitalId = "/api/mobiletagdict/gettagdictbyhospitalId";aa
    /*
    腕带的字典v2
     */
    public static String gettagdictbyhospitalIdv2 ="/api/mobiletagdict/get";

//    public static String auth = "alkdsjfaskdjlf";
    /**
     * 根据病人编号获取患者病历信息
     */
    public static String getmedicalrecordbypatientid = "/api/mobilemedicalrecord/getmedicalrecordbypatientid";

    /**
     * 通过患者编号获取胸痛信息
     */
    public static String getchestevaluatebypatientid = "/api/mobilechestevaluate/getchestevaluatebypatientid";

    /**
     * 根据病人编号获取患者一般检查信息
     */
    public static String getgeneralinspectionbypatientid ="/api/mobilegeneralinspection/getgeneralinspectionbypatientid";

    /**
     * 通过患者编号获取用药信息
     */
    public static String getallemergencybypatientid ="/api/mobileemergencyorders/getallemergencybypatientid";
    /**
     * 获得药品名称字典
     */
    public static String getdrugs ="/api/mobiledictionarysitems/getdrugs";
    /**
     * 上传保存接口
     */
    public static String postdata ="/api/mobilempmgtc/postdata";
    /**
     * 添加药品
     */
    public static String addMobileEmergencyorders = "/api/mobileemergencyorders/add";
    /*
    删除药品
     */
    public static String deleteMobileEmergencyorders= "/api/mobileemergencyorders/delete";
    /**
     * 根据ID获取交接单数据
     */
    public static String getmobileshiftrecord = "/api/mobileshiftrecord/get";
    public static String postmobileshiftrecord ="/api/mobileshiftrecord/post";
    /*
    设置顶部的出车状态
     */
    public static String setmissionstatusbyid ="/api/mobilemission/setmissionstatusbyid";
    /**
     * 获取出车状态
     *   判断当前任务是否存在
     */
    public static String getmissionstatusbyid ="/api/mobilemission/getmissionstatusbyid";
    /**
     * 获取历史任务
     */
//    public static String getHistoryMission  = "/api/mobilemission/history";
    public static String getHisotryMissionsByConditions ="/api/mobilemission/gethisotrymissionsbyconditions";
    /*
    检测病历填写的完整性
     */
    public static String validatedata = "/api/patient/validatedata";
    /*
    传递心电图：
    注意：心电图传递地址和一般的地址不同
     */
    public static String UploadECGData ="/api/ECGService/UploadECGData";
    /*
    根据患者ID获取患者卒中信息
     */
    public static String getstrokeinfo = "/api/mobilewoundandstroke/getstrokeinfo";
    /*
    提交患者卒中数据
     */
    public static String poststrokeinfo ="/api/mobilewoundandstroke/poststrokeinfo";
    /*
    根据患者ID获取患者创伤信息
     */
    public static String getwoundinfo ="/api/mobilewoundandstroke/getwoundinfo";
//    public static String getmissionstatusbyid = "/api/mobilemission/getmissionstatusbyid";
    public static String postwoundinfo = "/api/mobilewoundandstroke/postwoundinfo";
    /*
    获得用户的健康档案
     */
    public static String getInfoByIdCard  ="/api/BSoftHR/GetInfoByIdCard";
    /*
    判断是否病历可编辑
    返回0为可编辑，
    返回1为不可编辑
     */
    public static String canrecordedit ="/api/mission/canrecordedit";

    public static String spellMethod(String method, String methodParam)
    {
        return method+"/"+methodParam;
    }
    /**
     * 返回一个url
     * @param method ：方法名
     * @return 返回一个url
     */
    public static String spellURL(String doMain, String method)
    {
        String urlString = "";
//        String doMain = DoMainModel.getInstance().getDoMain();
        if (doMain != null)
        {
            urlString += doMain;
        }else
        {
            //abcLog.d("error", "DoMainModel中的domain为空");
        }
//        开始拼接方法名
        urlString += method;
        return urlString;

    }
    public static String spellURLWithOutDomain(Context context,String method)
    {
//        String doMain = Tool.getSp(context, SPreference.Login.sp_name, SPreference.Login.domain);
        String doMain = Tool.getStringCache(context, CacheName.domain_String);
        String urlString = "";
//        String doMain = DoMainModel.getInstance().getDoMain();
        if (doMain != null)
        {
            urlString += doMain;
        }else
        {
            //abcLog.d("error", "DoMainModel中的domain为空");
        }
//        开始拼接方法名
        urlString += method;
        return urlString;
    }

}


