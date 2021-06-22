package net.nanquanyuhao.security.springmvc.interceptor;

import net.nanquanyuhao.security.springmvc.model.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 认证拦截器
 */
@Component
public class SimpleAuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 校验用户请求的 url 是否在用户的权限范围内
        // 去除用户身份信息
        Object attribute = request.getSession().getAttribute(UserDto.SESSION_USER_KEY);
        if (attribute == null){
            // 没有认证，提示登录
            writeContent(response, "请登录");
        }

        UserDto userDto = (UserDto) attribute;
        // 请求的 url
        String requestURI = request.getRequestURI();
        if (userDto.getAuthorities().contains("p1") && requestURI.contains("/r/r1")) {
            return true;
        }
        if (userDto.getAuthorities().contains("p2") && requestURI.contains("/r/r2")) {
            return true;
        }
        writeContent(response, "没有权限，拒绝访问");

        return false;
    }

    /**
     * 响应信息给客户端
     *
     * @param response
     * @param msg
     * @throws IOException
     */
    private void writeContent(HttpServletResponse response, String msg) throws IOException {

        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print(msg);
        writer.close();
    }
}
