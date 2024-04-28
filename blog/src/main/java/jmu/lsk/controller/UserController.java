package jmu.lsk.controller;

import jmu.lsk.domain.ResponseResult;
import jmu.lsk.domain.entity.SysUser;
import jmu.lsk.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private SysUserService userService;

    @GetMapping("/userInfo")
    public ResponseResult userInfo(){
        return userService.userInfo();
    }

    @PutMapping("/userInfo")
    public ResponseResult updateUserInfo(@RequestBody SysUser user){
        return userService.updateUserInfo(user);
    }
}