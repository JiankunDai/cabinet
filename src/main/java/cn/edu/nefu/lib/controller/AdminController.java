package cn.edu.nefu.lib.controller;

import cn.edu.nefu.lib.common.LibException;
import cn.edu.nefu.lib.common.RestData;
import cn.edu.nefu.lib.common.util.JsonUtil;
import cn.edu.nefu.lib.domain.Student;
import cn.edu.nefu.lib.domain.vo.TimeVo;
import cn.edu.nefu.lib.redis.RedisService;
import cn.edu.nefu.lib.service.AdminService;
import cn.edu.nefu.lib.service.BookCaseService;
import cn.edu.nefu.lib.service.ColumnService;
import cn.edu.nefu.lib.service.ReserveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.Request;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @Classname MainController
 * @Description TODO
 * @auther daijiankun laptop
 * @create 2019-08-11 12:08 AM
 */
@RestController
@RequestMapping("/admin")
public class AdminController implements InitializingBean {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ColumnService columnService;

    @Autowired
    RedisService redisService;

    @Autowired
    ReserveService reserveService;

    @Autowired
    AdminService adminService;

    /**
     * 启动系统时预设置redis
     *
     * InitializingBean接口为bean提供了初始化方法的方式，
     * 它只包括afterPropertiesSet方法，
     * 凡是继承该接口的类，在初始化bean的时候都会执行该方法。
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        adminService.saveTime();
        columnService.saveCountToRedis();
    }

    @RequestMapping(value = "/put-time", method = RequestMethod.POST)
    public RestData putReservationTime(@RequestBody TimeVo timeVO, HttpServletRequest request) {
        logger.info("PUT putReservationTime" + JsonUtil.getJsonString(timeVO));

        return reserveService.putReservationTime(timeVO);
    }

    @RequestMapping(value = "/get-time", method = RequestMethod.GET)
    public RestData getReservationTime() {
        logger.info("GET ReservationTime");

        Map<String, Object> data = reserveService.getReservationTime();
        return new RestData(data);
    }

    /**
     * redis预设置
     * @return
     */
    @RequestMapping(value = "/pre", method = RequestMethod.GET)
    public RestData pre() {

        /**
         * 开始时间和结束时间存入redis
         */
        adminService.saveTime();
        /**
         * 柜子位置和数量存入redis
         */
        columnService.saveCountToRedis();

        return new RestData(0, "redis预设置成功");
    }
}
