package jmu.lsk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jmu.lsk.domain.entity.SysRole;

import java.util.List;


/**
 * 角色信息表(SysRole)表服务接口
 *
 * @author makejava
 * @since 2024-04-30 13:38:36
 */
public interface SysRoleService extends IService<SysRole> {

    List<String> selectRoleKeyByUserId(Long id);
}
