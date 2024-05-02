package jmu.lsk.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import jmu.lsk.domain.ResponseResult;
import jmu.lsk.domain.dto.AddRoleDto;
import jmu.lsk.domain.dto.AddUserDto;
import jmu.lsk.domain.dto.RoleStatusDto;
import jmu.lsk.service.SysMenuService;
import jmu.lsk.service.SysRoleService;
import jmu.lsk.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/system/user")
public class UserController {
    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "页号"),
            @ApiImplicitParam(name = "pageSize",value = "每页大小"),
            @ApiImplicitParam(name = "status",value = "状态"),
            @ApiImplicitParam(name = "userName",value = "用户名")
    })
    public ResponseResult list(Integer pageNum, Integer pageSize, String status, String userName,String phonenumber){
        return sysUserService.pageUserList(pageNum,pageSize,status,userName,phonenumber);
    }

    @PostMapping
    public ResponseResult addUser(@RequestBody @Valid AddUserDto addUserDto){
        sysUserService.addUser(addUserDto);
        return ResponseResult.okResult();
    }
//
//    @PutMapping("/changeStatus")
//    public ResponseResult updateRoleStatus(@RequestBody RoleStatusDto roleStatusDto){
//        return sysRoleService.updateRoleStatus(roleStatusDto);
//    }
//
    @DeleteMapping("{id}")
    public ResponseResult deleteRole(@PathVariable Long id){
        return sysUserService.deleteUser(id);
    }

    @GetMapping("/{id}")
    public ResponseResult getUser(@PathVariable Long id){

        return sysUserService.getUserDetails(id);
    }


    @PutMapping
    public ResponseResult updateRole(@RequestBody AddUserDto addUserDto){
        return sysUserService.updateUser(addUserDto);
    }
}
