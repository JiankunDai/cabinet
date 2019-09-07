package cn.edu.nefu.lib.domain.vo;

public class UserVo {

    /**
     * 学号
     */
    private String studentId;

    /**
     * 验证码
     */
    private String verifyCode;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
