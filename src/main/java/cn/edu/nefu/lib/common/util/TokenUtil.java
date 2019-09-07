package cn.edu.nefu.lib.common.util;

import cn.edu.nefu.lib.domain.Student;
import cn.edu.nefu.lib.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@Component
public class TokenUtil {

    private static StudentMapper studentMapper;

    public static Student getStudentByToken(HttpServletRequest request) {
        Student student = null;
        String token = request.getHeader("token");
        if (null != token) {
            Student studentCondition = new Student();
            studentCondition.setToken(token);
            List<Student> users = studentMapper.selectByCondition(studentCondition);
            if (1 == users.size()) {
                student = users.get(0);
            }
        }
        return student;
    }

    public static String getPassword() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    public static String getToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    @Autowired
    public void setStudentMapper(StudentMapper studentMapper) {
        TokenUtil.studentMapper = studentMapper;
    }
}
