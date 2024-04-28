package jmu.lsk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jmu.lsk.domain.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;


/**
 * 用户表(SysUser)表数据库访问层
 *
 * @author makejava
 * @since 2024-04-28 13:21:37
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

}
