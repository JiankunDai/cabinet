package cn.edu.nefu.lib.common;

/**
 * @Classname CodeMsg
 * @Description TODO
 * @auther daijiankun laptop
 * @create 2019-08-06 6:29 PM
 */
public class CodeMsg {

    public static final CodeMsg MOBILE_NOTEXIST = new CodeMsg(50012, "ceshi");
    public static final CodeMsg ACCESS_LIMIT = new CodeMsg(23432, "ceshi");
    public static final CodeMsg MIAOSHA_FAIL = new CodeMsg(23434, "ceshi");
    public static final CodeMsg MIAOSHA_OVER_ERROR = new CodeMsg(32434, "ceshi") ;
    public static final CodeMsg REPEATE_MIAOSHA = new CodeMsg(32434, "ceshi");
    public static final CodeMsg ORDER_NOT_EXIST = new CodeMsg(32434, "ceshi");
    private int code;
    private String msg;

    public CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public CodeMsg fillArgs(Object...args) {//变参
        int code=this.code;
        //message是填充了参数的message
        String message=String.format(this.msg, args);
        return new CodeMsg(code,message);
    }

    @Override
    public String toString() {
        return "CodeMsg{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }

    public static CodeMsg SUCCESS=new CodeMsg(0,"success");
    public static CodeMsg BIND_ERROR=new CodeMsg(500101,"参数校验异常:%s");
    public static CodeMsg REQUEST_ILLEAGAL=new CodeMsg(500102,"非法请求!");

    public static CodeMsg APPLY_FAIL=new CodeMsg(500103,"预约失败!");


    public static CodeMsg SESSION_ERROR=new CodeMsg(500210,"session失效!");
    public static CodeMsg PASSWORD_EMPTY=new CodeMsg(500211,"密码不能为空!");
    public static CodeMsg STU_NO_EMPTY=new CodeMsg(500212,"学号不能为空!");
    public static CodeMsg STU_NO_ERROR=new CodeMsg(500213,"学号格式错误!");
    public static CodeMsg STU_NO_NOTEXIST=new CodeMsg(500214,"学号不存在!");
    public static CodeMsg PASSWORD_ERROR=new CodeMsg(500215,"密码错误!");

    public static CodeMsg REPEATE_APPLY = new CodeMsg(500110, "不能重复预约书包柜");
    public static CodeMsg CABINET_OVER_ERROR=new CodeMsg(500500,"柜子预约结束，没有更多的书包柜!");


}
