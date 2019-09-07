package cn.edu.nefu.lib.domain;

import java.io.Serializable;

/**
 * @Classname Student
 * @Description 学生实体类
 * @auther daijiankun laptop
 * @create 2019-08-06 2:27 PM
 */
public class Student implements Serializable {

    private int studentId;
    /**
     * 学号 or 账号
     */
    private String studentNo;
    /**
     * 姓名
     */
    private String name;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户类型 0:未抢到的学生 1：已抢到的学生 2:老师 3:黑名单
     */
    private Integer type;

    /**
     * 盐
     */
    private String salt;

    /**
     * token
     */
    private String token;

    public Student() {
    }

    public Student(String token) {
        this.token = token;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentNo='" + studentNo + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                ", salt='" + salt + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
