package tonels.config;

/**
 * oes-live 全局响应码
 */
public enum CodeEnum {
    
    UNKNOW_ERROR("-1", "未知错误"),
    OK("0", "成功"),
    FAILURE("1", "失败"),
    INVALID_PARAMS("2", "请求参数无效"),
    UNSUPPORTED_URI("3", "未知URI"),
    UNQUANXIAN("403", "无权限操作"),
    UNLOGIN("401", "账号或密码不正确"),
    PARAMS_ERROR("1000","请求参数错误"),
    DATABASE_ERROR("2002","数据库入库异常"),
    DELETE_ERROR("2003", "删除失败"),
    BATCH_DELETE_ERROR("2004", "未勾选，请选中后删除"),
    JSON_ERROR("2010", "json转换异常"),
    PARSE_DATETIME_ERROR("2011", "时间格式转换错误"),
    

    PARAMS_EMPTY("2101","请求参数为空"),
    ISEXIST("2103","信息已存在"),
    ISNULL("2104","信息不存在"),
    NOT_NUMBER("2105","必须为数字"),
    PHOTO_MISS("2106","图片上传失败"),
    PHOTO_SIZE_ERROR("2108","图片大小错误"),
    PHOTO_FILE_ERROR("2109","图片文件错误"),
    PHOTO_SPEC_ERROR("2110","图片规格错误"),
    CAPTCHA_SEND_ERROR("2107","短信验证码发送失败"),
    
    LOGIN_ERROR("2200", "账号或密码不正确"),
    USER_FORBID("2201", "用户被禁用"),
    INVALID_TOKEN("2202", "用户认证失败，请重新登录"),
    EXPIRED_TOKEN("2203", "Token已过期，请重新登录"),
    ISNULL_TOKEN("2204", "无效token，请重新登录"),

    PROJECT_NAME_EXIST("2300", "项目名称已经被使用"),
    CONTRACT_NAME_EXIST("2301", "合同名称已经被使用"),
    CONTRACT_CODE_EXIST("2302", "合同编号已经被使用"),
    PROJECTID_NOT_EXIST("2303", "项目id对应的项目信息不存在"),
    DEPARTMENTID_NOT_EXIST("2304", "部门id对应的部门信息不存在"),
    GROUP_NAME_EXIST("2305", "项目分组名称已经存在"),
    PROJECT_USER_EXIST("2306", "项目成员已经存在"),
    CONTRACTID_NOT_EXIST("2307", "合同id对应的合同信息不存在"),
    PROJECT_DEPARTMENT_EXIST("2308", "部门信息在该项目下已经存在"),
    GROUP_TASK_EXECUTE("2309", "项目分组下有任务，无法删除"),
    PROJECT_USER_NOT_DELETE("2310", "项目下至少有一个项目成员"),

    DEPARTMENT_PARENT_ID_NOT__EXIST("2400","部门父id不能为空"),
    DEPARTMENT_NAME_NOT__EXIST("2401","部门名称不能为空"),
    DEPARTMENT_NAME_REPEAT("2402","部门名称不能重复"),
    DEPARTMENT_ID_NOT__EXIST("2403","部门id不能为空"),


    REPEATED_ADD("3202", "重复添加"),
    USER_NOT_FOUND("3203","用户不存在"),
    USER_PASSWD_ERROR("3204","用户密码不正确"),
    USER_CODE_EXIST("3206","工号已被使用"),
    ROLE_REPEATED("3207","角色名称重复"),
    USER_DEPARTMENT_NAME_ERROR("3208","部门名称错误"),
    RESOURCE_REPEATED("3209","角色名称重复"),
    ROLE_NOT_FOUND("3210","角色不存在"),
    ROLE_PARAMS_ERROR("3211","角色参数错误"),

    P_RESOURCE_NOT_EXIST("3301","父级资源不存在"),
    RESOURCE_PARAM_ERROR("3300","资源参数错误"),



    DEPARTMENT_PAR_ID_NOT_FOUND("4000","该父部门不存在"),
    DEPARTMENT_NOT_FOUND("4000","该父部门id不能为空"),
    IMPORT_ERROR("4001", "Excel导入异常！"),
    EXPORT_ERROR("4002", "Excel导出异常！"),
    CUSTON_ERROR("9999", " 其它，自定义错误");

    private String code;
    private String msg;

    private CodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
