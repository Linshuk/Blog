package jmu.lsk.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import jmu.lsk.domain.ResponseResult;
import jmu.lsk.domain.dto.SysUserDto;
import jmu.lsk.domain.entity.SysUser;
import jmu.lsk.service.SysUserService;
import jmu.lsk.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@Api(tags = "用户",description = "用户相关接口")
public class UserController {

    @Autowired
    private SysUserService userService;

    @GetMapping("/userInfo")
    @ApiOperation(value = "用户详情",notes = "获取用户详情")
    public ResponseResult userInfo(){
        return userService.userInfo();
    }

    @PutMapping("/userInfo")
    @ApiOperation(value = "更新用户详情")
    @ApiImplicitParam(name = "SysUserDto",value = "用户dto")
    public ResponseResult updateUserInfo(@RequestBody SysUserDto sysUserDto){
        SysUser user = BeanCopyUtils.copyBean(sysUserDto, SysUser.class);
        return userService.updateUserInfo(user);
    }

    @PostMapping("/register")
    @ApiOperation(value = "用户注册")
    @ApiImplicitParam(name = "SysUserDto",value = "用户dto")
    public ResponseResult register(@RequestBody @Valid SysUserDto sysUserDto){
        SysUser user = BeanCopyUtils.copyBean(sysUserDto, SysUser.class);
        return userService.register(user);
    }
}