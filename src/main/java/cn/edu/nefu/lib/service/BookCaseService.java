package cn.edu.nefu.lib.service;

import cn.edu.nefu.lib.common.ErrorMessage;
import cn.edu.nefu.lib.common.LibException;
import cn.edu.nefu.lib.domain.BookCase;
import cn.edu.nefu.lib.domain.Student;
import cn.edu.nefu.lib.mapper.BookCaseMapper;
import cn.edu.nefu.lib.mapper.StudentMapper;
import cn.edu.nefu.lib.rabbitmq.ArrangeMessage;
import cn.edu.nefu.lib.rabbitmq.MQSender;
import cn.edu.nefu.lib.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname BookCaseService
 * @Description TODO
 * @auther daijiankun laptop
 * @create 2019-09-04 8:12 PM
 */
@Service
public class BookCaseService  {

    @Autowired
    BookCaseMapper bookCaseMapper;

    @Autowired
    StudentService studentService;

    @Autowired
    RedisService redisService;

    @Autowired
    MQSender sender;

    public Map<String, Object> sendArrageMessage(Student student, String position) throws LibException {
        Map<String, Object> rtv = new HashMap<>();
        /**
         * 消息中间件
         */
        ArrangeMessage message = new ArrangeMessage();
        message.setStudent(student);
        message.setPosition(position);
        sender.sendArrangeMessage(message);

        return rtv;
    }

    /**
     * 根据学生的ID获取柜子的具体位置
     * @param student
     * @return
     * @throws LibException
     */
    public Map<String, Object> getLocationByStudentId(Student student) throws LibException {
        Map<String, Object> rtv = null;
        List<Student> list = studentService.selectByCondition(student);
        Student student1 = list.get(0);
        System.out.println("student: " + student1);

        if (null != student1) {
            List<BookCase> bookCases = bookCaseMapper.selectByStudentId(student1);
            BookCase bookCase = null;
            if (null != bookCases && 1 == bookCases.size()) {
                bookCase = bookCases.get(0);
            } else {
                throw new LibException(ErrorMessage.MORE_THAN_ONE);
            }

            if (null != bookCase) {
                rtv = new HashMap<>(2);
                rtv.put("location", bookCase.getLocation());
                rtv.put("number", bookCase.getNumber());
            } else {
                throw new LibException(ErrorMessage.INFO_NOT_EXSITS);
            }
        }

        return rtv;
    }

    public synchronized Map<String, Object> arrange(Student student, String position) {
        Map<String, Object> rtv = new HashMap<>();
        BookCase bookCase = null;
        List<BookCase> bookCases = bookCaseMapper.selectByLocation(position);

        if (bookCases != null) {
            for (int i = 0; i < bookCases.size(); i++) {
                if (bookCases.get(i).getStatus() == 0) {
                    bookCase = bookCases.get(i);
                }
            }
        }

        Boolean bool = bookCaseMapper.updateOwnerAndState(bookCase, student);
        System.out.println(bool);

        rtv.put("systemId", bookCase.getSystemId());
        rtv.put("location", bookCase.getLocation());
        rtv.put("number", bookCase.getNumber());
        rtv.put("studentId", bookCase.getStudentId());

        return rtv;
    }
}
