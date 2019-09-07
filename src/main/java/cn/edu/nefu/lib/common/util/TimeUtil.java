package cn.edu.nefu.lib.common.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @Classname TimeUtil
 * @Description TODO
 * @auther daijiankun laptop
 * @create 2019-09-05 12:41 PM
 */
public class TimeUtil {

    public static String getCurrentTime() {
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of(ZoneId.SHORT_IDS.get("CTT")));
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
