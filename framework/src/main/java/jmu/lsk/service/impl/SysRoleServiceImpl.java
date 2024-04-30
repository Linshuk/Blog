package jmu.lsk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jmu.lsk.domain.entity.SysRole;
import jmu.lsk.mapper.SysRoleMapper;
import jmu.lsk.service.SysRoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * 角色信息表(SysRole)表服务实现类
 *
 * @author makejava
 * @since 2024-04-30 13:38:36
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
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
}
