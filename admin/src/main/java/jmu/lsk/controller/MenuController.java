package jmu.lsk.controller;

import jmu.lsk.domain.ResponseResult;
import jmu.lsk.domain.entity.SysMenu;
import jmu.lsk.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/menu")
public class MenuController {

    @Autowired
    SysMenuService sysMenuService;

    @GetMapping("/list")
    public ResponseResult list( String status, String menu){
        return sysMenuService.menuList(status,menu);
    }

    @GetMapping("{id}")
    public ResponseResult getMenu(@PathVariable Long id){
        return ResponseResult.okResult(sysMenuService.getById(id));
    }

    @PostMapping
    public ResponseResult addMenu(@RequestBody SysMenu sysMenu){
        sysMenuService.save(sysMenu);
        return ResponseResult.okResult();
    }

    @PutMapping
    public ResponseResult updateMenu(@RequestBody SysMenu sysMenu){
        return sysMenuService.updateMenu(sysMenu);
    }

    @DeleteMapping("{id}")
    public ResponseResult deleteMenu(@PathVariable Long id){
        return sysMenuService.deleteMenu(id);
    }

    @GetMapping("/treeselect")
    public ResponseResult getMenuTree(){
        return ResponseResult.okResult(sysMenuService.selectMenuTree(1L));
    }

    @GetMapping("/roleMenuTreeselect/{id}")
    public ResponseResult getMenuTree(@PathVariable Long id){
        return ResponseResult.okResult(sysMenuService.selectRoleMenuTree(id));
    }
}
