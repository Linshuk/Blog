package jmu.lsk.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import jmu.lsk.domain.ResponseResult;
import jmu.lsk.domain.dto.SysUserDto;
import jmu.lsk.domain.entity.SysUser;
import jmu.lsk.domain.vo.RoutersVo;
import jmu.lsk.service.LoginService;
import jmu.lsk.service.RoleInfoService;
import jmu.lsk.service.RoutersService;
import jmu.lsk.utils.BeanCopyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.management.relation.Role;
import javax.validation.Valid;

@RestController
@Api(tags = "登录",description = "用户登录相关接口")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/user/login")
    @ApiOperation(value = "登录",notes = "用户登录")
    @ApiImplicitParam(name = "userDto",value = "用户dto")
    public ResponseResult login(@RequestBody SysUserDto userDto){
        SysUser user = BeanCopyUtils.copyBean(userDto, SysUser.class);
        return loginService.login(user);
    }
    @Autowired
    RoleInfoService roleInfoService;

    @GetMapping("getInfo")
    @ApiOperation(value = "用户信息",notes = "查询用户信息返回权限")
    public ResponseResult getInfo(){
        return roleInfoService.getInfo();
    }

    @Autowired
    RoutersService routersService;
    @GetMapping("getRouters")
    @ApiOperation(value = "路由信息",notes = "根据用户权限返回路由")
    public ResponseResult<RoutersVo> getRouters(){
        return routersService.getRouter();
    }

    @PostMapping("/user/logout")
    @ApiOperation(value = "退出登录")
    public ResponseResult logout(){
        return loginService.logout();
    }

}