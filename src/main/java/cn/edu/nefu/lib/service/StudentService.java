package cn.edu.nefu.lib.service;

import cn.edu.nefu.lib.common.LibException;
import cn.edu.nefu.lib.common.util.TokenUtil;
import cn.edu.nefu.lib.domain.Student;
import cn.edu.nefu.lib.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.edu.nefu.lib.common.GlobalConst.USER_DISABLE;

/**
 * @Classname StudentService
 * @Description TODO
 * @auther daijiankun laptop
 * @create 2019-08-09 5:01 PM
 */
@Service
public class StudentService {

    @Autowired
    StudentMapper studentMapper;

    /**
     * 根据学生ID记录该同学已完成柜子预约，拒绝下一次预约请求
     * @param student
     */
    public void complete(Student student) {
        studentMapper.complete(student);
    }


    public Map<String, Object> postLogin(Student student) throws LibException {
        Map<String, Object> rtv = new HashMap<>();
        List<Student> students = studentMapper.selectByCondition(student);
        if (null != students && 1 == students.size()) {
            student = students.get(0);
            int type = student.getType();
            if (USER_DISABLE == type) {
                throw new LibException("该同学已被禁用!");
            } else {
                student.setToken(TokenUtil.getToken());
                if (0 < studentMapper.updateTokenByStudentId(student)) {
                    student = students.get(0);
                    rtv.put("systemId", student.getStudentId());
                    rtv.put("token", student.getToken());
                    rtv.put("type", student.getType());
                }
            }
        } else {
            throw new LibException("学号或密码不正确!");
        }
        return rtv;
    }

    public List<Student> selectByCondition(Student student) {
        return studentMapper.selectByCondition(student);
    }
}
