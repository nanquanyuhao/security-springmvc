package net.nanquanyuhao.security.springmvc.controller;

import net.nanquanyuhao.security.springmvc.model.AuthenticationRequest;
import net.nanquanyuhao.security.springmvc.model.UserDto;
import net.nanquanyuhao.security.springmvc.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @version 1.0
 **/
@RestController
public class LoginController {

    @Autowired
    AuthenticationService authenticationService;

    @RequestMapping(value = "/login", produces = "text/plain;charset=utf-8")
    public String login(AuthenticationRequest authenticationRequest) {

        UserDto userDto = authenticationService.authentication(authenticationRequest);
        return userDto.getUsername() + "登录成功";
    }
}
