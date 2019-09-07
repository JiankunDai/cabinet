package cn.edu.nefu.lib.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname MQConfig
 * @Description TODO
 * @auther daijiankun laptop
 * @create 2019-09-06 9:53 PM
 */
@Configuration
public class MQConfig {
    public static final String QUEUE = "queue";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE, true);
    }
}
