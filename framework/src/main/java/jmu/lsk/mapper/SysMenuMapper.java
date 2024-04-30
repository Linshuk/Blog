package jmu.lsk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jmu.lsk.domain.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * 菜单权限表(SysMenu)表数据库访问层
 *
 * @author makejava
 * @since 2024-04-30 13:36:08
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    List<String> selectPermsByUserId(Long userId);

    List<SysMenu> selectRouterMenuTreeByUserId(Long userId);

    List<SysMenu> selectAllRouterMenu();
}
