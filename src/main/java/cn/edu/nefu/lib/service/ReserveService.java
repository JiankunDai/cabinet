package cn.edu.nefu.lib.service;

import cn.edu.nefu.lib.common.RestData;
import cn.edu.nefu.lib.common.util.TimeUtil;
import cn.edu.nefu.lib.domain.CabinetColumn;
import cn.edu.nefu.lib.domain.Config;
import cn.edu.nefu.lib.domain.Student;
import cn.edu.nefu.lib.domain.vo.TimeVo;
import cn.edu.nefu.lib.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname ReserveService
 * @Description TODO
 * @auther daijiankun laptop
 * @create 2019-08-09 5:02 PM
 */
@Service
public class ReserveService {

    @Autowired
    ColumnService columnService;

    @Autowired
    RedisService redisService;

    @Autowired
    ConfigService configService;


    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void miaosha(Student student, CabinetColumn cabinetColumn) {
        //1.减少库存,即更新库存
        //cabinetService.reduceStock(cabinetColumn);
        //2.保存记录到订单里
        //Order order = orderService.createOrder(student, cabinetColumn);
        //return order;
    }

    /**
     * 查询开放时间，结束时间，系统当前时间
     * @return map
     */
    public Map<String, Object> getAllTime() {
        String nowTime = TimeUtil.getCurrentTime();

        Map<String, Object> rtv = new HashMap<>(3);
        rtv.put("startTime", redisService.get("startTime"));
        rtv.put("nowTime", nowTime);
        rtv.put("endTime", redisService.get("endTime"));
        return rtv;
    }

    public Map<String, Object> getReservationTime() {
        Map<String, Object> rtv = new HashMap<>(2);
        rtv.put("startTime", configService.getStartTime());
        rtv.put("endTime", configService.getEndTime());

        return rtv;
    }


    public RestData putReservationTime(TimeVo timeVO) {
        Config config = new Config();
        config.setConfigKey("startTime");
        config.setConfigValue(timeVO.getStartTime());
        Config config1 = new Config();
        config1.setConfigKey("endTime");
        config1.setConfigValue(timeVO.getEndTime());



        configService.update(config);
        configService.update(config1);

        return new RestData(0, "修改成功");

        /*
        if (0 < configMapper.updateOpenTime(config) * configMapper.updateOpenTime(config1)) {
            return new RestData(0, "修改成功");
        } else {
            return new RestData(1, "修改失败");
        }
         */
    }


}
