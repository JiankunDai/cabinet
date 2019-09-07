package cn.edu.nefu.lib.controller.interceptor;

import cn.edu.nefu.lib.common.RestData;
import cn.edu.nefu.lib.common.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String path = request.getRequestURI();
        logger.debug("TokenInterceptor");

        boolean result = path.contains("login") || "OPTIONS".equals(request.getMethod());

        /*
        if (result) {
            return true;
        } else {
            User user = TokenUtil.getUserByToken(request);
            if (null == user) {
                logger.info("TokenInterceptor failed, token=" + request.getHeader("token"));
                try {
                    responseJson(response, new RestData(2, ErrorMessage.PLEASE_RELOGIN));
                } catch (Exception e) {
                    logger.error(e.getLocalizedMessage());
                }
                return false;
            } else {
                logger.info("TokenInterceptor success, token=" + request.getHeader("token"));
                return true;
            }
        }
         */
        return true;
    }

    private void responseJson(HttpServletResponse response, RestData restData) throws Exception {
        response.setContentType("application/json; charset=utf-8");
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "*");
        response.addHeader("Access-Control-Allow-Headers", "*");
        response.addHeader("Access-Control-Allow-Credentials", "true");
        PrintWriter writer = response.getWriter();
        writer.print(JsonUtil.getJsonString(restData));
        response.flushBuffer();
        writer.close();
    }
}
