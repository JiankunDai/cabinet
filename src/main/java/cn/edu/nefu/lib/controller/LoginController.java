package cn.edu.nefu.lib.controller;

import cn.edu.nefu.lib.common.ErrorMessage;
import cn.edu.nefu.lib.common.LibException;
import cn.edu.nefu.lib.common.RestData;
import cn.edu.nefu.lib.common.util.JsonUtil;
import cn.edu.nefu.lib.domain.Student;
import cn.edu.nefu.lib.mapper.StudentMapper;
import cn.edu.nefu.lib.redis.RedisService;
import cn.edu.nefu.lib.service.StudentService;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * @author : Jimi
 * @date : 2018/10/27
 * @since : Java 8
 */
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("api")
@RestController
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StudentService studentService;

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    RedisService redisService;

    @Autowired
    DefaultKaptcha defaultKaptcha;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public RestData postLogin(@RequestBody Student student) {
        logger.info("POST postLogin : " + JsonUtil.getJsonString(student));

        if (0 == student.getStudentId() || 1 > student.getStudentNo().length() ||
                null == student.getPassword() || 6 != student.getPassword().length()) {
            return new RestData(1, ErrorMessage.PARAMATER_ERROR);
        }

        try {
            Map<String, Object> data = studentService.postLogin(student);
            return new RestData(data);
        } catch (LibException e) {
            return new RestData(1, e.getMessage());
        }
    }

    @RequestMapping(value = "/code", method = RequestMethod.GET)
    public RestData getCode(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        logger.info("GET getCode");

        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        Student student = studentMapper.selectByCondition(new Student(httpServletRequest.getHeader("token"))).get(0);
        try {
            //生产验证码字符串并保存到redis中
            String createText = defaultKaptcha.createText();
            logger.info(createText);
            redisService.putHash("code", student.getStudentId(), createText);

            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (Exception e) {
            try {
                httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            } catch (IOException e1) {
                logger.warn(e1.getLocalizedMessage());
            }
            return new RestData(1, ErrorMessage.SYSTEM_ERROR);
        }

        //定义response输出类型为image/jpeg类型,使用base64输出流输出图片的byte数组
        byte[] captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        String apache = new String(Base64.encodeBase64(captchaChallengeAsJpeg));
        return new RestData(apache);
    }
}
