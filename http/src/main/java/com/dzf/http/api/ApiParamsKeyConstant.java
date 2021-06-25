package com.dzf.http.api;

/**
 * @ClassName ApiParamsKeyConstant
 * @Author dingmingzhong
 * @Date 2020-04-14
 * @Description:
 */
public interface ApiParamsKeyConstant {

    String PkUserRegister = "pk_userregister";
    String ReMainNum = "remainnum";
    String VPhone= "vphone";
    String SucNum = "sucnum";

    /**
     * api接口参数：通用接口参数：接口操作码主键
     */
    public static final String API_PARAMS_OPERATE = "operate";

    /**
     * api接口参数：做账进度列表提醒接口参数：客户id
     */
    public static final String API_PARAMS_ID = "id";
    /**
     * api接口参数：做账进度列表提醒接口参数：提醒类型
     */
    public static final String API_PARAMS_REMIND_QRYTYPE = "qrytype";

    /**
     * api接口参数：做账进度详情 客户id
     */
    public static final String API_PARAMS_REMIND_CORPK_ID = "corpk_id";

    /**
     * api接口参数：做账进度详情：要提醒客户年份做账
     */
    public static final String API_PARAMS_REMIND_YEAR = "year";
    /**
     * api接口参数：做账进度详情：要提醒客户月份做账
     */
    public static final String API_PARAMS_REMIND_MONTH = "month";

    /**
     * api接口参数：收费管理详情，收费主键
     */
    public static final String API_PARAMS_CHARGE_DETAIL_ID = "charge_id";

    /**
     * api接口参数：收费管理详情，收费主键
     */
    public static final String API_PARAMS_CHARGE_DETAIL_BTYPE = "btype";

    /**
     * api接口参数：财务处理：票据id
     */
    public static final String API_PARAMS_FINANCIL_GROUPKEY = "groupkey";
    /**
     * api接口参数：财务处理:票据详情中存在问题的图片id组（格式：001,002）
     */
    public static final String API_PARAMS_FINANCIL_DETAIL_LIBS = "libs";
    /**
     * api接口参数：财务处理:票据详情中存在问题的审批说明
     */
    public static final String API_PARAMS_FINANCIL_DETAIL_MESSAGE = "message";
    /**
     * api接口参数：财务处理:票据列表最后一条时间
     */
    public static final String API_PARAMS_FINANCIL_LIST_TIME = "l_date";
    /**
     * api接口参数：财务处理:票据列表每页显示多少条
     */
    public static final String API_PARAMS_FINANCIL_LIST_ROWS = "rows";

    /**
     * 转交资料id，中间逗号隔开
     */
    public static final String API_PARAMS_DATUM_FILEIDS = "fileids";
    /**
     * api接口参数：财务处理:筛选条件
     */
    public static final String API_PARAMS_FINANCIL_LIST_IMG_STATE = "img_state";
    /**
     * api接口参数：财务处理:搜索客户关键字
     */
    public static final String API_PARAMS_FINANCIL_LIST_UNAME = "uname";
}
