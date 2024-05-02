package jmu.lsk.mapper;


import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import jmu.lsk.domain.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户和角色关联表(SysUserRole)表数据库访问层
 *
 * @author makejava
 * @since 2024-05-03 00:01:47
 */
@Mapper
public interface SysUserRoleMapper extends MppBaseMapper<SysUserRole> {

}
