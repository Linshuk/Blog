package jmu.lsk.service.impl;


import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import jmu.lsk.domain.entity.ArticleTag;
import jmu.lsk.mapper.ArticleTagMapper;
import jmu.lsk.service.ArticleTagService;
import org.springframework.stereotype.Service;


/**
 * 文章标签关联表(ArticleTag)表服务实现类
 *
 * @author makejava
 * @since 2024-04-30 18:41:16
 */
@Service("articleTagService")
public class ArticleTagServiceImpl extends MppServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {

}
