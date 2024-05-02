package jmu.lsk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jmu.lsk.domain.ResponseResult;
import jmu.lsk.domain.dto.AddRoleDto;
import jmu.lsk.domain.dto.RoleStatusDto;
import jmu.lsk.domain.entity.Article;
import jmu.lsk.domain.entity.ArticleTag;
import jmu.lsk.domain.entity.SysRole;
import jmu.lsk.domain.entity.SysRoleMenu;
import jmu.lsk.domain.vo.PageVo;
import jmu.lsk.mapper.SysRoleMapper;
import jmu.lsk.mapper.SysRoleMenuMapper;
import jmu.lsk.service.SysRoleMenuService;
import jmu.lsk.service.SysRoleService;
import jmu.lsk.utils.BeanCopyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * 角色信息表(SysRole)表服务实现类
 *
 * @author makejava
 * @since 2024-04-30 13:38:36
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Override
    public ResponseResult pageRoleList(Integer pageNum, Integer pageSize, String status, String roleName) {
        LambdaQueryWrapper<SysRole> lambdaQueryWrapper= new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotBlank(status),SysRole::getStatus,status);
        lambdaQueryWrapper.like(StringUtils.isNotBlank(roleName),SysRole::getRoleName,roleName);
        lambdaQueryWrapper.orderByAsc(SysRole::getRoleSort);

        Page page = new Page(pageNum,pageSize);
        page(page,lambdaQueryWrapper);

        return ResponseResult.okResult(new PageVo(page.getRecords(),page.getTotal()));
    }

    @Override
    public List<String> selectRoleKeyByUserId(Long id) {
        //判断是否是管理员 如果是返回集合中只需要有admin
        if(id == 1L){
            List<String> roleKeys = new ArrayList<>();
            roleKeys.add("admin");
            return roleKeys;
        }
        //否则查询用户所具有的角色信息
        return getBaseMapper().selectRoleKeyByUserId(id);
    }

    @Override
    public ResponseResult updateRoleStatus(RoleStatusDto roleStatusDto) {
        SysRole sysRole = new SysRole();
        sysRole.setStatus(roleStatusDto.getStatus());
        sysRole.setId(roleStatusDto.getRoleId());
        updateById(sysRole);
        return ResponseResult.okResult();
    }

    @Autowired
    SysRoleMenuService sysRoleMenuService;

    @Override
    public ResponseResult addRole(AddRoleDto addRoleDto) {
        SysRole sysRole = BeanCopyUtils.copyBean(addRoleDto,SysRole.class);
        save(sysRole);
        List<SysRoleMenu> menuIds = addRoleDto.getMenuIds().stream()
                .map(aLong -> new SysRoleMenu(sysRole.getId(),aLong)).collect(Collectors.toList());
        sysRoleMenuService.saveOrUpdateBatchByMultiId(menuIds);
        return null;
    }

    @Override
    public ResponseResult updateRole(AddRoleDto addRoleDto) {
        SysRole sysRole = BeanCopyUtils.copyBean(addRoleDto,SysRole.class);
        List<SysRoleMenu> sysRoleMenus = addRoleDto.getMenuIds().stream()
                .map(tagId -> new SysRoleMenu(addRoleDto.getId(), tagId))
                .toList();
        List<SysRoleMenu> roleMenus = sysRoleMenuService.list(Wrappers.<SysRoleMenu>lambdaQuery().eq(SysRoleMenu::getRoleId,addRoleDto.getId()));
        for (SysRoleMenu roleMenu:roleMenus
        ) {
            sysRoleMenuService.deleteByMultiId(roleMenu);
        }
        sysRoleMenuService.saveOrUpdateBatchByMultiId(sysRoleMenus);
        //添加 博客和标签的关联
        updateById(sysRole);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteRole(Long id) {

        List<SysRoleMenu> roleMenus = sysRoleMenuService.list(Wrappers.<SysRoleMenu>lambdaQuery().eq(SysRoleMenu::getRoleId,id));
        for (SysRoleMenu roleMenu:roleMenus
        ) {
            sysRoleMenuService.deleteByMultiId(roleMenu);
        }
        removeById(id);
        return ResponseResult.okResult();
    }
}
