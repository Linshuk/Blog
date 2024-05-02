package jmu.lsk.service.impl;


import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import jmu.lsk.domain.entity.SysRoleMenu;
import jmu.lsk.mapper.SysRoleMenuMapper;
import jmu.lsk.service.SysRoleMenuService;
import org.springframework.stereotype.Service;

/**
 * 角色和菜单关联表(SysRoleMenu)表服务实现类
 *
 * @author makejava
 * @since 2024-05-02 21:30:42
 */
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl extends MppServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {

}
