package life.majiang.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND(2001, "你找的问题不在了，要不要换个试试"),
    TARGET_PARAM_NOT_FOUND(2002, "未选中任何问题或评论进行回复"),
    NO_LOGIN(2002, "未登录,不能进行评论，请先登录"),
    SYS_ERROR(2003, "服务器冒烟了，要不然你稍后试试！！！");

    private Integer code;
    private String message;

    @Override
    public String getMessage() {
        return message;
    }


    @Override
    public Integer getCode() {
        return code;
    }

    CustomizeErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
