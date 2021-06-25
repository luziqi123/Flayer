package com.dzf.http;



/**
 * @ClassName URLHttp
 * @Author dingmingzhong
 * @Date 2020-05-26
 * @Description:
 */
public class URLHttp {

    //协议地址
    public static String REGIESTXY = "https://tg.dazhangfang.com/qcr-magreement.html";
    public static String YINSIXY = "https://tg.dazhangfang.com/qcr-mprotocol.html";

    /********************************用户相关接口*************************************/
    //版本更新检查
    public static String CHECK = "/app/version/check";
    //短信登录接口
    public static String LOGINURl = "/app/puser/loginregist";
    //获取短信验证码
    public static String LOGINCODE = "/user/mobile/send";
    //退出登录
    public static String LOGINEXIT = "/app/puser/logout";
    ///获取用户信息
    public static String GETUSERINFO = "/qcr/org/app/login/getUserInfo";
    //更新自由人手机号
    public static String UPDATE_PHONE = "/qcr/org/wxapi/updatePhone";
    //更新自由人手机号 短信验证码
    public static String UPDATE_PHONE_CODE = "/qcr/org/wxapi/sendUpdatePhoneCode";
    /***********************************实名认证相关接口*/
    //上传ocr结果
    public static String REALNAMEUPLOADOCRRESULT = "/external/realresult/upload/result";
    //是否需要ocr
    public static String REALNAMEISDO = "/app/realname/isdo";
    //获取OCR授权
    public static String REALNAMEOCRSIGN = "/app/realname/ocrsign";
    //获取腾讯实名授权
    public static String REALNAMEFACEID = "/app/realname/faceid";
    //获取认证结果
    public static String REALNAMERESULT = "/app/realname/result";

    /***********************************企快快认证相关接口*/
    //身份证OCRd大账房api
    public static String APIOCRIDCARD= "/app/realname/ocr/idcard";
    //人脸核身获取4位验证码大账房api
    public static String APIFACEGENCODE= "/app/realname/gencode";
    //人脸核身大账房api
    public static String APIFACEVALID= "/app/realname/face/valid";
    //企快快上传意愿视频
    public static String QCRSAVEENTREPRNEUERVIDEO= "/qcr/org/wxapi/saveEntreprneuerVideo";

    //获取认证渠道
    public static String APIOCRCHANNEL = "/app/realname/channel";
    //企快快用户待签署文书详情
    public static String QCRDOCUMENT= "/qcr/org/wxapi/getNoSignDocument";
    //企快快用户待签署文书列表
    public static String QCRENTREPRENEURNOSIGNDOCSVO= "/qcr/org/wxapi/getEntrepreneurNoSignDocsVO";
    //企快快用户待签署文书详情
    public static String QCRUPDATESIGN= "/qcr/org/wxapi/updateSign";

}
