package cn.edu.nefu.lib.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Classname RedisService
 * @Description TODO
 * @auther daijiankun laptop
 * @create 2019-09-05 1:38 PM
 */
@Service
public class RedisService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     *
     * @param key
     * @param start
     * @param end
     * @return
     */

    public List<String> getList(String key, long start, long end) {
        try {
            return stringRedisTemplate.opsForList().range(key, 0, -1);
        } catch (Exception e) {
            logger.info("getList" + e.getMessage());
            return null;
        }
    }

    public Long getListSize(String key) {
        try {
            return stringRedisTemplate.opsForList().size(key);
        } catch (Exception e) {
            logger.info("add" + e.getMessage());
            return null;
        }
    }

    public boolean pushValue(String key, String value) {
        try {
            stringRedisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            logger.info("pushValue" + e.getMessage());
            return false;
        }

    }

    public Set<String> getFloor(int floor) {
        Set<String> values = new HashSet<>();
        try {
            values = stringRedisTemplate.keys(floor + "_*");
        } catch (Exception e) {
            logger.info("pushValue" + e.getMessage());
        }
        return values;
    }

    public boolean pushList(String key, List<String> list) {
        try {
            stringRedisTemplate.opsForList().rightPushAll(key, list);
            return true;
        } catch (Exception e) {
            logger.info("pushList" + e.getMessage());
            return false;
        }

    }

    public String popValue(String key) {
        try {
            return stringRedisTemplate.opsForList().leftPop(key);
        } catch (Exception e) {
            logger.info("popValue" + e.getMessage());
            return null;
        }
    }

    public boolean removeListValue(String key, String value) {
        try {
            stringRedisTemplate.opsForList().remove(key, 1, value);
            return true;
        } catch (Exception e) {
            logger.info("removeListValue" + e.getMessage());
            return false;
        }
    }

    public long dec(String key, long number) {
        try {
            Long l = stringRedisTemplate.opsForValue().increment(key, -number);
            return l;
        } catch (Exception e) {
            logger.info("dec" + e.getMessage());
            return 0;
        }
    }

    public boolean set(String key, String value) {
        try {
            stringRedisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            logger.info("set" + e.getMessage());
            return false;
        }
    }

    public String get(String key) {
        try {
            return stringRedisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            logger.info("get" + e.getMessage());
            return null;
        }
    }

    public long inc(String key, long number) {
        try {
            Long l = stringRedisTemplate.opsForValue().increment(key, number);
            return l;
        } catch (Exception e) {
            logger.info("inc" + e.getMessage());
            return 0;
        }
    }

    public boolean add(String key, String value) {
        try {
            stringRedisTemplate.opsForSet().add(key, value);
            return true;
        } catch (Exception e) {
            logger.info("add" + e.getMessage());
            return false;
        }
    }

    public boolean isMember(String key, String value) {
        Boolean rt = stringRedisTemplate.opsForSet().isMember(key, value);

        if (rt == null) {
            return false;
        } else {
            return rt;
        }

    }

    public boolean remove(String key) {
        try {
            stringRedisTemplate.delete(key);
            return true;
        } catch (Exception e) {
            logger.info("remove" + e.getMessage());
            return false;
        }
    }

    public boolean putHash(String hash, Object hashKey, Object hashValue) {
        try {
            stringRedisTemplate.opsForHash().put(hash, hashKey, hashValue);
            return true;
        } catch (Exception e) {
            logger.info("putHash" + e.getMessage());
            return false;
        }
    }

    public Object getHash(String hash, Object hashKey) {
        try {
            return stringRedisTemplate.opsForHash().get(hash, hashKey);
        } catch (Exception e) {
            logger.info("getHash" + e.getMessage());
            return null;
        }
    }

}
