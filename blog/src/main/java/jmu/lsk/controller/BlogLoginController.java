package jmu.lsk.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import jmu.lsk.domain.ResponseResult;
import jmu.lsk.domain.dto.SysUserDto;
import jmu.lsk.domain.entity.SysUser;
import jmu.lsk.service.BlogLoginService;
import jmu.lsk.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Api(tags = "登录",description = "用户登录相关接口")
public class BlogLoginController {

    @Autowired
    //BlogLoginService是我们在service目录写的接口
    private BlogLoginService blogLoginService;

    @PostMapping("/login")
    @ApiOperation(value = "登录",notes = "用户登录")
    @ApiImplicitParam(name = "SysUserDto",value = "用户dto")
    //ResponseResult是我们在huanf-framework工程里面写的实体类
    public ResponseResult login(@RequestBody SysUserDto sysUserDto){
        SysUser user = BeanCopyUtils.copyBean(sysUserDto,SysUser.class);
        return blogLoginService.login(user);
    }

    @PostMapping("/logout")
    @ApiOperation(value = "退出登录",notes = "用户退出登录")
    public ResponseResult logout(){
        return blogLoginService.logout();
    }

}