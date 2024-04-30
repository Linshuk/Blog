package jmu.lsk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jmu.lsk.domain.entity.Tag;
import org.apache.ibatis.annotations.Mapper;


/**
 * 标签(Tag)表数据库访问层
 *
 * @author makejava
 * @since 2024-04-29 20:38:48
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag> {

}
