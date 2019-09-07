package cn.edu.nefu.lib.rabbitmq;

import cn.edu.nefu.lib.domain.Student;

import java.io.Serializable;

/**
 * @Classname ArrangeMessage
 * @Description TODO
 * @auther daijiankun laptop
 * @create 2019-09-06 9:54 PM
 */
public class ArrangeMessage implements Serializable {

    private Student student;
    private String position;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "ArrangeMessage{" +
                "student=" + student +
                ", position='" + position + '\'' +
                '}';
    }
}
