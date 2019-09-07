package cn.edu.nefu.lib.domain;

public class BookCase {

    /**
     * 书包柜主键
     */
    private Integer systemId;

    /**
     * 柜子位置 2_1 2楼1区域 || 3_2 3楼2区域
     */
    private String location;

    /**
     * 柜子编号
     */
    private Integer number;

    /**
     * 拥有者ID
     */
    private Integer studentId;

    /**
     * 柜子当前状态 0 开放 1 占用或预留
     */
    private Integer status;

    public Integer getSystemId() {
        return systemId;
    }

    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BookCase{" +
                "systemId=" + systemId +
                ", location='" + location + '\'' +
                ", number=" + number +
                ", studentId=" + studentId +
                ", status=" + status +
                '}';
    }
}
