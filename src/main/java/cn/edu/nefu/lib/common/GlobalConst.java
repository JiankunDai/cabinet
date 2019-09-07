package cn.edu.nefu.lib.common;

/**
 * @Classname GlobalConst
 * @Description TODO
 * @auther daijiankun laptop
 * @create 2019-09-06 4:25 PM
 */
public class GlobalConst {

    // BookCase.status
    /**
     * 0 空闲
     */
    public static final int BC_ENABLE = 0;

    /**
     * 1 被使用
     */
    public static final int BC_DISENABLE = 1;

    /**
     * 2 预留
     */
    public static final int BC_PREORDER = 2;

    // Config.configKey
    /**
     * 0 学生
     */
    public static final int USER_STUDENT = 0;

    /**
     * 1 管理员
     */
    public static final int USER_ADMIN = 1;

    /**
     * 3 查
     */
    public static final int USER_OTHER_ADMIN = 3;

    /**
     * 2 黑名单
     */
    public static final int USER_DISABLE = 2;

    /**
     * 上传文件路径
     */
    public static final String UPLOAD_PATH = "/tmp/";
}