package jmu.lsk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jmu.lsk.domain.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * 角色信息表(SysRole)表数据库访问层
 *
 * @author makejava
 * @since 2024-04-30 13:38:36
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
    List<String> selectRoleKeyByUserId(Long userId);
}
