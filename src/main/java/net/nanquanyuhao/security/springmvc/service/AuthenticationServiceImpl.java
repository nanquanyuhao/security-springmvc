package net.nanquanyuhao.security.springmvc.service;

import net.nanquanyuhao.security.springmvc.model.AuthenticationRequest;
import net.nanquanyuhao.security.springmvc.model.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Administrator
 * @version 1.0
 **/
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    /**
     * 用户认证，校验用户身份信息是否合法
     *
     * @param authenticationRequest 用户认证请求，账号和密码
     * @return 认证成功的用户信息
     */
    @Override
    public UserDto authentication(AuthenticationRequest authenticationRequest) {
        //校验参数是否为空
        if (authenticationRequest == null
                || StringUtils.isEmpty(authenticationRequest.getUsername())
                || StringUtils.isEmpty(authenticationRequest.getPassword())) {
            throw new RuntimeException("账号和密码为空");
        }

        //根据账号去查询数据库,这里测试程序采用模拟方法
        UserDto user = getUserDto(authenticationRequest.getUsername());
        //判断用户是否为空
        if (user == null) {
            throw new RuntimeException("查询不到该用户");
        }

        //校验密码
        if (!authenticationRequest.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("账号或密码错误");
        }

        //认证通过，返回用户身份信息
        return user;
    }

    //根据账号查询用户信息
    private UserDto getUserDto(String userName) {
        return userMap.get(userName);
    }

    //用户信息
    private Map<String, UserDto> userMap = new HashMap<>();
    {
        userMap.put("zhangsan", new UserDto("1010",
                "zhangsan", "123", "张三", "133443"));
        userMap.put("lisi", new UserDto("1011",
                "lisi", "456", "李四", "144553"));
    }
}
