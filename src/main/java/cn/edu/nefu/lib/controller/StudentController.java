package cn.edu.nefu.lib.controller;

import cn.edu.nefu.lib.common.ErrorMessage;
import cn.edu.nefu.lib.common.LibException;
import cn.edu.nefu.lib.common.RestData;
import cn.edu.nefu.lib.domain.CabinetColumn;
import cn.edu.nefu.lib.domain.Student;
import cn.edu.nefu.lib.redis.RedisService;
import cn.edu.nefu.lib.service.BookCaseService;
import cn.edu.nefu.lib.service.ColumnService;
import cn.edu.nefu.lib.service.ReserveService;
import cn.edu.nefu.lib.service.StudentService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname StudentController
 * @Description TODO
 * @auther daijiankun laptop
 * @create 2019-09-01 5:12 PM
 */

/**
 * idea:
 *  本项目以每列的柜子为单位，所以怎么给抢到同列柜子的同学分配具体的柜子序号是个问题。
 *  想法是再建立一张表，该表有2325行，属性为 书包柜编号，区域（几层几列），状态，使用学生学号，使用学生姓名。
 *  当并发抢柜子时，只访问Column表，给每个抢到柜子的同学创建一条请求，加入到消息队列中，该请求的作用是分配具体编号。
 *  理由是：学生已经抢到柜子，必然有书包柜使用，所以不会继续频繁的点击，等到流量高峰过后，每个人会自然的查到具体的位置信息。
 *  附加：要给学生type加一个要素为“已抢到”，当该学生抢到柜子拦截其继续抢柜子的请求。
 *  评价：满足业务需求，提高用户体验度，降低系统的压力。
 */

@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
@RequestMapping("/student")
public class StudentController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StudentService studentService;

    @Autowired
    ColumnService columnService;

    @Autowired
    BookCaseService bookCaseService;

    @Autowired
    ReserveService reserveService;

    @Autowired
    RedisService redisService;



    @RequestMapping(value = "/info/{studentId}", method = RequestMethod.GET)
    public RestData getLocation(@PathVariable(value = "studentId") String studentId) {
        logger.info("GET getLocation : studentId=" + studentId);

        Student student = new Student();
        student.setStudentId(Integer.parseInt(studentId));

        try {
            Map<String, Object> data = bookCaseService.getLocationByStudentId(student);
            return new RestData(data);
        } catch (LibException e) {
            return new RestData(1, e.getMessage());
        }
    }

    @RequestMapping(value = "/num/{floor}", method = RequestMethod.GET)
    public RestData getNum(@PathVariable(value = "floor") String floor) {
        logger.info("GET getNum");
        /*
        此部分应该从redis中获取，避免MySQL与redis的不一致出现
         */
        List<Map<String, Object>> data = columnService.getColumnCountFromRedis(Integer.parseInt(floor));

        return new RestData(data);
    }

    @RequestMapping(value = "/time", method = RequestMethod.GET)
    public RestData getStartTime() {
        logger.info("GET getStartTime");

        Map<String, Object> data = reserveService.getAllTime();
        return new RestData(data);
    }

    @RequestMapping(value = "/reserve", method = RequestMethod.POST)
    public RestData reserve(@RequestBody CabinetColumn cabinetColumn, HttpServletRequest request) {
        logger.info("POST reserve");
        Student student = new Student(request.getHeader("token"));
        try {
             return new RestData(columnService.postReserve(cabinetColumn, student));
        } catch (LibException e) {
            return new RestData(e.getMessage());
        }
    }
}
