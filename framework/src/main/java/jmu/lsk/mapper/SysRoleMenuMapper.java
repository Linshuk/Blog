package jmu.lsk.mapper;

import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import jmu.lsk.domain.entity.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;


/**
 * 角色和菜单关联表(SysRoleMenu)表数据库访问层
 *
 * @author makejava
 * @since 2024-05-02 21:30:42
 */
@Mapper
public interface SysRoleMenuMapper extends MppBaseMapper<SysRoleMenu> {

}
