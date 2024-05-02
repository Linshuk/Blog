package jmu.lsk.service.impl;


import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import jmu.lsk.domain.entity.SysUserRole;
import jmu.lsk.mapper.SysUserRoleMapper;
import jmu.lsk.service.SysUserRoleService;
import org.springframework.stereotype.Service;

/**
 * 用户和角色关联表(SysUserRole)表服务实现类
 *
 * @author makejava
 * @since 2024-05-03 00:01:47
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends MppServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

}
