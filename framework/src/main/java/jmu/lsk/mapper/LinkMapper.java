package jmu.lsk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jmu.lsk.domain.entity.Link;
import org.apache.ibatis.annotations.Mapper;


/**
 * 友链(Link)表数据库访问层
 *
 * @author makejava
 * @since 2024-04-27 23:45:38
 */
@Mapper
public interface LinkMapper extends BaseMapper<Link> {

}
