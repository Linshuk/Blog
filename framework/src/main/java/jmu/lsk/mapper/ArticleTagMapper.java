package jmu.lsk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import jmu.lsk.domain.entity.ArticleTag;
import org.apache.ibatis.annotations.Mapper;


/**
 * 文章标签关联表(ArticleTag)表数据库访问层
 *
 * @author makejava
 * @since 2024-04-30 18:41:16
 */
@Mapper
public interface ArticleTagMapper extends MppBaseMapper<ArticleTag> {

}
