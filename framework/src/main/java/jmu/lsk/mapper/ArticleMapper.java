package jmu.lsk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jmu.lsk.domain.entity.Article;
import org.apache.ibatis.annotations.Mapper;


/**
 * 文章表(Article)表数据库访问层
 *
 * @author makejava
 * @since 2024-04-27 17:21:00
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

}
