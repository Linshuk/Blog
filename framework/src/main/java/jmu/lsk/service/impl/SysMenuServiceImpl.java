package jmu.lsk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jmu.lsk.constants.SystemConstants;
import jmu.lsk.domain.ResponseResult;
import jmu.lsk.domain.entity.SysMenu;
import jmu.lsk.domain.entity.SysRoleMenu;
import jmu.lsk.domain.vo.MenuTreeVo;
import jmu.lsk.domain.vo.RoleMenuTreeVo;
import jmu.lsk.enums.AppHttpCodeEnum;
import jmu.lsk.exception.SystemException;
import jmu.lsk.mapper.SysMenuMapper;
import jmu.lsk.service.SysMenuService;
import jmu.lsk.service.SysRoleMenuService;
import jmu.lsk.utils.BeanCopyUtils;
import jmu.lsk.utils.SecurityUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 菜单权限表(SysMenu)表服务实现类
 *
 * @author makejava
 * @since 2024-04-30 13:36:08
 */
@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
    @Override
    public List<String> selectPermsByUserId(Long id) {
        //如果是管理员，返回所有的权限
        if(id == 1L){
            LambdaQueryWrapper<SysMenu> wrapper = new LambdaQueryWrapper<>();
            wrapper.in(SysMenu::getMenuType, SystemConstants.MENU,SystemConstants.BUTTON);
            wrapper.eq(SysMenu::getStatus, SystemConstants.STATUS_NORMAL);
            List<SysMenu> menus = list(wrapper);
            return menus.stream()
                    .map(SysMenu::getPerms)
                    .collect(Collectors.toList());
        }
        //否则返回所具有的权限
        return getBaseMapper().selectPermsByUserId(id);
    }

    @Override
    public List<SysMenu> selectRouterMenuTreeByUserId(Long userId) {
        SysMenuMapper menuMapper = getBaseMapper();
        List<SysMenu> menus = null;
        //判断是否是管理员
        if(SecurityUtils.isAdmin()){
            //如果是 获取所有符合要求的Menu
            menus = menuMapper.selectAllRouterMenu();
        }else{
            //否则  获取当前用户所具有的Menu
            menus = menuMapper.selectRouterMenuTreeByUserId(userId);
        }

        //构建tree
        //先找出第一层的菜单  然后去找他们的子菜单设置到children属性中
        List<SysMenu> menuTree = builderMenuTree(menus,0L);
        return menuTree;
    }

    private List<SysMenu> builderMenuTree(List<SysMenu> menus, Long parentId) {
        List<SysMenu> menuTree = menus.stream()
                .filter(menu -> menu.getParentId().equals(parentId))
                .map(menu -> menu.setChildren(getChildren(menu, menus)))
                .collect(Collectors.toList());
        return menuTree;
    }

    private List<SysMenu> getChildren(SysMenu menu, List<SysMenu> menus) {
        List<SysMenu> childrenList = menus.stream()
                .filter(m -> m.getParentId().equals(menu.getId()))
                .map(m->m.setChildren(getChildren(m,menus)))
                .collect(Collectors.toList());
        childrenList.sort(Comparator.comparingInt(SysMenu::getOrderNum));
        return childrenList;
    }

    @Override
    public ResponseResult menuList(String status, String menuName){
        LambdaQueryWrapper<SysMenu> lambdaQueryWrapper =new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotBlank(status),SysMenu::getStatus,status);
        lambdaQueryWrapper.like(StringUtils.isNotBlank(menuName),SysMenu::getMenuName,menuName);
        lambdaQueryWrapper.orderByAsc(SysMenu::getParentId);
        List<SysMenu> sysMenuList = list(lambdaQueryWrapper);

        return ResponseResult.okResult(sysMenuList);
    }

    @Override
    public ResponseResult updateMenu(SysMenu sysMenu) {

        if(sysMenu.getParentId().equals(sysMenu.getId())){
            throw new SystemException(AppHttpCodeEnum.ERROR_PARENTMENU);
        }

        updateById(sysMenu);

        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteMenu(Long id){
        if(count(Wrappers.<SysMenu>lambdaQuery().eq(SysMenu::getParentId,id))>0)
        {
            throw new SystemException(AppHttpCodeEnum.ERROR_EXISTCHLIDREN);
        }

        return ResponseResult.okResult();
    }

    @Override
    public  List<MenuTreeVo> selectMenuTree(Long id) {
        SysMenuMapper menuMapper = getBaseMapper();
        List<SysMenu> menus = menuMapper.selectAllRouterMenu();
        List<MenuTreeVo> menuTree = builderMenuTreeVo(menus,0L);
        return menuTree;
    }

    @Autowired
    SysRoleMenuService sysRoleMenuService;

    @Override
    public RoleMenuTreeVo selectRoleMenuTree(Long id) {
        SysMenuMapper menuMapper = getBaseMapper();
        List<Long> menus = null;
        //判断是否是管理员
        if(id.equals(1L)){
            //如果是 获取所有符合要求的Menu
            menus = menuMapper.selectAllRouterMenu().stream()
                    .map(SysMenu::getId).collect(Collectors.toList());
        }else{
            //否则  获取当前用户所具有的Menu
            menus = sysRoleMenuService.list(Wrappers.<SysRoleMenu>lambdaQuery().eq(SysRoleMenu::getRoleId,id))
                    .stream()
                    .map(SysRoleMenu::getMenuId).collect(Collectors.toList());
        }
        RoleMenuTreeVo roleMenuTreeVo  = new RoleMenuTreeVo(selectMenuTree(id),menus);
        return roleMenuTreeVo;
    }


    private List<MenuTreeVo> builderMenuTreeVo(List<SysMenu> menus, Long parentId) {
        List<MenuTreeVo> menuTree = menus.stream()
                .filter(menu->menu.getParentId().equals(parentId))
                .map(sysMenu -> {
                    MenuTreeVo menuTreeVo = BeanCopyUtils.copyBean(sysMenu, MenuTreeVo.class);
                    menuTreeVo.setLabel(sysMenu.getMenuName());
                    menuTreeVo.setChildren(getChildrenVo(sysMenu,menus));
                    return menuTreeVo;
                }).collect(Collectors.toList());
        return menuTree;
    }

    private List<MenuTreeVo> getChildrenVo(SysMenu menu, List<SysMenu> menus) {
        List<MenuTreeVo> childrenList = menus.stream()
                .filter(m -> m.getParentId().equals(menu.getId()))
                .map(sysMenu -> {
                    MenuTreeVo menuTreeVo = BeanCopyUtils.copyBean(sysMenu, MenuTreeVo.class);
                    menuTreeVo.setLabel(sysMenu.getMenuName());
                    menuTreeVo.setChildren(getChildrenVo(sysMenu,menus));
                    return menuTreeVo;
                }).collect(Collectors.toList());
        return childrenList;
    }
}
