package cn.edu.nefu.lib.rabbitmq;

import cn.edu.nefu.lib.common.ErrorMessage;
import cn.edu.nefu.lib.common.LibException;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Classname MQSender
 * @Description TODO
 * @auther daijiankun laptop
 * @create 2019-09-06 9:53 PM
 */
@Service
public class MQSender {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AmqpTemplate amqpTemplate;

    /**
     * 发送安排具体位置的消息
     * @param message
     */
    public void sendArrangeMessage(ArrangeMessage message) throws LibException {
        logger.info("Send Message: " + message);
        try {
            String realMessage = JSON.toJSONString(message);
            amqpTemplate.convertAndSend(MQConfig.QUEUE, realMessage);
        } catch (Exception e) {
            throw new LibException(ErrorMessage.MQ_ERROR);
        }
    }
}
