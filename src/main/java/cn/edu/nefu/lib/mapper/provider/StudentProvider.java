package cn.edu.nefu.lib.mapper.provider;

import cn.edu.nefu.lib.domain.Student;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

/**
 * @Classname StudentProvider
 * @Description TODO
 * @auther daijiankun laptop
 * @create 2019-09-04 8:52 PM
 */
public class StudentProvider {

    public String selectByCondition(final Student student) {
        return new SQL() {
            {
                SELECT("studentid, studentno, name, password, type, salt, token");
                FROM("student");
                if (0 != student.getStudentId()) {
                    WHERE("studentid = #{studentId}");
                }
                if (null != student.getStudentNo()) {
                    WHERE("studentno = #{studentNo}");
                }
                if (null != student.getName()) {
                    WHERE("name = #{name}");
                }
                if (null != student.getPassword()) {
                    WHERE("password = #{password}");
                }
                if (null != student.getType()) {
                    WHERE("type = #{type}");
                }
                if (null != student.getSalt()) {
                    WHERE("salt = #{salt}");
                }
                if (null != student.getToken()) {
                    WHERE("token = #{token}");
                }
            }
        }.toString();
    }
}
