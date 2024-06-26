package jmu.lsk.enums;


public enum AppHttpCodeEnum {
    // 成功
    SUCCESS(200,"操作成功"),
    // 登录
    NEED_LOGIN(401,"需要登录后操作"),
    NO_OPERATOR_AUTH(403,"无权限操作"),
    SYSTEM_ERROR(500,"出现错误"),
    USERNAME_EXIST(501,"用户名已存在"),
    PHONENUMBER_EXIST(502,"手机号已存在"), EMAIL_EXIST(503, "邮箱已存在"),
    REQUIRE_USERNAME(504, "必需填写用户名"),
    LOGIN_ERROR(505,"用户名或密码错误"),
    FILE_TYPE_ERROR(507,"文件类型错误"),
    UPLOAD_ERROR(409,"上传失败"),
    ERROR_PARAM(508, "无效参数"),
    NICKNAME_EXIST(501,"昵称已存在"),
    CONTENT_NOT_NULL(506,"评论不能为空"),
    TAG_EXIST(501,"标签名已存在" ),
    ERROR_PARENTMENU(509, "上级菜单不能选择自己"),
    ERROR_EXISTCHLIDREN(509,"存在子菜单不能删除" ),
    ERROR_OPERAION(500, "不能删除当前yonghu");
    int code;
    String msg;

    AppHttpCodeEnum(int code, String errorMessage){
        this.code = code;
        this.msg = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}