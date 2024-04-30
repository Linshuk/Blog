package jmu.lsk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jmu.lsk.domain.entity.SysMenu;

import java.util.List;


/**
 * 菜单权限表(SysMenu)表服务接口
 *
 * @author makejava
 * @since 2024-04-30 13:36:08
 */
public interface SysMenuService extends IService<SysMenu> {

    List<String> selectPermsByUserId(Long id);

    List<SysMenu> selectRouterMenuTreeByUserId(Long userId);
}
