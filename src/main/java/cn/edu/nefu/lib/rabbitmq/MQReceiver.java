package cn.edu.nefu.lib.rabbitmq;

import cn.edu.nefu.lib.domain.Student;
import cn.edu.nefu.lib.service.BookCaseService;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Classname MQReceiver
 * @Description TODO
 * @auther daijiankun laptop
 * @create 2019-09-06 9:53 PM
 */
@Service
public class MQReceiver {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    BookCaseService bookCaseService;

    @RabbitListener(queues = MQConfig.QUEUE)
    public void receive(String stringMessage) {
        logger.info("receive message: " +stringMessage);
        ArrangeMessage message = JSON.toJavaObject(JSON.parseObject(stringMessage), ArrangeMessage.class);
        Student student = message.getStudent();
        String position = message.getPosition();

        /**
         * 能发送到此处的请求较小，可访问bookcase表进行柜子具体位置的安排
         */
        bookCaseService.arrange(student, position);
    }
}
