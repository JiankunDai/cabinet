package cn.edu.nefu.lib.service;

import cn.edu.nefu.lib.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Classname AdminService
 * @Description TODO
 * @auther daijiankun laptop
 * @create 2019-09-05 3:59 PM
 */
@Service
public class AdminService {

    @Autowired
    ReserveService reserveService;

    @Autowired
    RedisService redisService;

    public void saveTime() {
        Map<String, Object> map = reserveService.getReservationTime();

        redisService.set("startTime", map.get("startTime").toString());
        redisService.set("endTime", map.get("endTime").toString());

    }
}
