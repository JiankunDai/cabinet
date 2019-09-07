package cn.edu.nefu.lib.service;

import cn.edu.nefu.lib.mapper.RecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Classname RecordService
 * @Description TODO
 * @auther daijiankun laptop
 * @create 2019-09-06 5:09 PM
 */
@Service
public class RecordService {

    @Autowired
    RecordMapper recordMapper;

    public void saveRecord(int studentId, int column, int floor) {
        recordMapper.saveRecord(studentId, column, floor);
    }
}
