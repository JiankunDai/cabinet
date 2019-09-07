package cn.edu.nefu.lib.service;

import cn.edu.nefu.lib.common.ErrorMessage;
import cn.edu.nefu.lib.common.LibException;
import cn.edu.nefu.lib.domain.CabinetColumn;
import cn.edu.nefu.lib.domain.Student;
import cn.edu.nefu.lib.mapper.ColumnMapper;
import cn.edu.nefu.lib.rabbitmq.ArrangeMessage;
import cn.edu.nefu.lib.rabbitmq.MQSender;
import cn.edu.nefu.lib.redis.RedisService;
import com.sun.deploy.panel.AndOrRadioPropertyGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Classname ColumnService
 * @Description TODO
 * @auther daijiankun laptop
 * @create 2019-08-09 4:50 PM
 */
@Service
public class ColumnService {

    @Autowired
    ColumnMapper columnMapper;

    @Autowired
    RedisService redisService;

    @Autowired
    StudentService studentService;

    @Autowired
    RecordService recordService;

    @Autowired
    BookCaseService bookCaseService;

    /**
     * 根据楼层得到该层楼柜子的数量和位置
     * @param floor
     * @return
     */
    public List<Map<String, Object>> getColumnCountFromRedis(int floor) {
        List<Map<String, Object>> rtv = new ArrayList<>();

        Set<String> keys = redisService.getFloor(floor);
        for (String key : keys) {
            Map<String, Object> map = new HashMap<>();
            String value = redisService.get(key);
            map.put("location", key);
            map.put("count", value);
            rtv.add(map);
        }

        return rtv;
    }

    /**
     * 核心方法
     *
     * 功能：预约柜子
     *
     * 业务逻辑：
     *
     * 1.根据cabinetColumn确定柜子的楼层和列数
     * 若楼层和列数为100，100 则表示该同学想在二楼、三楼中随机选择一个柜子
     * 调用maxNum函数，遍历所有列，把剩余柜子数最多的一列给他
     * 若均为0，则提示全部柜子已抢完
     * 2.在redis中查看当前列是否还有柜子(count>0)
     * 有：在redis的map里记录该学生id和列id
     * 无：提示所选列柜子已抢完，提示：请刷新页面重新选择柜子
     *
     * 能不能让redis预减库存？能
     *
     * @param cabinetColumn 确定抢的是哪一列柜子
     * @param student 确定是哪个学生在抢柜子
     * @return
     */
    @Transactional
    public Map<String, Object> postReserve(CabinetColumn cabinetColumn, Student student) throws LibException {
        Map<String, Object> rtv = new HashMap<>();

        int floor = cabinetColumn.getFloor();
        int column = cabinetColumn.getCol();
        String position = floor + "_" + column;

        List<Student> students = studentService.selectByCondition(student);
        if (students != null && students.size() == 1) {
            student = students.get(0);
        } else {
            throw new LibException("出现了很奇怪的错误,数据库找不到你的信息");
        }

        if (student.getType() != 0) {
            throw new LibException(ErrorMessage.COMPLETED_OR_LIMITED);
        }

        synchronized (this) {
            long count = redisService.dec(position, 1);

            if (count >= 0) {
                //redisService.dec(position, 1);
                /**
                 * 记录学生ID和柜子的列数到MySQL数据库
                 */
                recordService.saveRecord(student.getStudentId(), column, floor);
                /**
                 * 修改学生的标记为1，表示该同学已抢到柜子
                 */
                studentService.complete(student);

                rtv.put("studentId", student.getStudentId());
                rtv.put("studentNo", student.getStudentNo());
                rtv.put("position", position);

                bookCaseService.sendArrageMessage(student, position);
            } else {
                throw new LibException(ErrorMessage.COLUMN_EMPTY);
            }
        }

        return rtv;
    }

    /**
     * 将位置信息和数量存入redis中，键为位置，值为数量
     */
    public void saveCountToRedis() {
        List<CabinetColumn> list = columnMapper.getAll();
        for (CabinetColumn column : list) {
            redisService.set(column.getFloor() + "_" + column.getCol(), String.valueOf(column.getCount()));
        }
    }
}

