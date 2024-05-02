package jmu.lsk.controller;

import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import jmu.lsk.constants.SystemConstants;
import jmu.lsk.domain.ResponseResult;
import jmu.lsk.domain.dto.AddRoleDto;
import jmu.lsk.domain.dto.RoleStatusDto;
import jmu.lsk.domain.entity.SysMenu;
import jmu.lsk.domain.entity.SysRole;
import jmu.lsk.enums.AppHttpCodeEnum;
import jmu.lsk.service.SysMenuService;
import jmu.lsk.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/role")
public class RoleController {

    @Autowired
    SysRoleService sysRoleService;

    @Autowired
    SysMenuService sysMenuService;

    @GetMapping("/list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "页号"),
            @ApiImplicitParam(name = "pageSize",value = "每页大小"),
            @ApiImplicitParam(name = "status",value = "状态"),
            @ApiImplicitParam(name = "roleName",value = "角色名")
    })
    public ResponseResult list(Integer pageNum, Integer pageSize, String status,String roleName){
        return sysRoleService.pageRoleList(pageNum,pageSize,status,roleName);
    }

    @GetMapping("/listAllRole")
    public ResponseResult AllRole(){
        return ResponseResult.okResult(sysRoleService.list(Wrappers.<SysRole>lambdaQuery().eq(SysRole::getStatus, SystemConstants.STATUS_NORMAL)));
    }


    @PostMapping
    public ResponseResult addRole(@RequestBody AddRoleDto addRoleDto){
        sysRoleService.addRole(addRoleDto);
        return ResponseResult.okResult();
    }

    @PutMapping("/changeStatus")
    public ResponseResult updateRoleStatus(@RequestBody RoleStatusDto roleStatusDto){
        return sysRoleService.updateRoleStatus(roleStatusDto);
    }

    @DeleteMapping("{id}")
    public ResponseResult deleteRole(@PathVariable Long id){
        return sysRoleService.deleteRole(id);
    }

    @GetMapping("/{id}")
    public ResponseResult getRole(@PathVariable Long id){
        return ResponseResult.okResult(sysRoleService.getById(id));
    }


    @PutMapping
    public ResponseResult updateRole(@RequestBody AddRoleDto addRoleDto){
    return sysRoleService.updateRole(addRoleDto);
    }
}
