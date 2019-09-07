package cn.edu.nefu.lib.service;

import cn.edu.nefu.lib.domain.Config;
import cn.edu.nefu.lib.mapper.ConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Classname ConfigService
 * @Description TODO
 * @auther daijiankun laptop
 * @create 2019-09-05 2:08 PM
 */
@Service
public class ConfigService {

    @Autowired
    ConfigMapper configMapper;


    public Object getStartTime() {
        return configMapper.getStartTime();
    }

    public Object getEndTime() {
        return configMapper.getEndTime();
    }

    public void update(Config config) {
        configMapper.update(config);
    }
}
