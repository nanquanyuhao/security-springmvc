package net.nanquanyuhao.security.springmvc.controller;

import net.nanquanyuhao.security.springmvc.model.AuthenticationRequest;
import net.nanquanyuhao.security.springmvc.model.UserDto;
import net.nanquanyuhao.security.springmvc.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author Administrator
 * @version 1.0
 **/
@RestController
public class LoginController {

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(value = "/login", produces = "text/plain;charset=utf-8")
    public String login(AuthenticationRequest authenticationRequest, HttpSession session) {

        UserDto userDto = authenticationService.authentication(authenticationRequest);

        // 存入 session
        session.setAttribute(UserDto.SESSION_USER_KEY, userDto);
        return userDto.getUsername() + "登录成功";
    }

    /**
     * 登出逻辑
     *
     * @param session
     * @return
     */
    @GetMapping(value = "/logout", produces = {"text/plain;charset=utf-8"})
    public String logout(HttpSession session) {
        session.invalidate();
        return "退出成功";
    }

    /**
     * 资源访问逻辑
     *
     * @param session
     * @return
     */
    @GetMapping(value = "/r/r1", produces = {"text/plain;charset=utf-8"})
    public String r1(HttpSession session) {

        String fullname = null;
        Object attribute = session.getAttribute(UserDto.SESSION_USER_KEY);

        if (attribute == null) {
            fullname = "匿名";
        } else {
            UserDto userDto = (UserDto) attribute;
            fullname = userDto.getFullname();
        }

        return fullname + "访问资源 r1";
    }
}
