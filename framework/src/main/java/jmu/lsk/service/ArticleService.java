package jmu.lsk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jmu.lsk.domain.ResponseResult;
import jmu.lsk.domain.dto.AddArticleDto;
import jmu.lsk.domain.entity.Article;
import org.springframework.context.annotation.Lazy;


/**
 * 文章表(Article)表服务接口
 *
 * @author makejava
 * @since 2024-04-27 17:20:58
 */
public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    //根据id查询文章详情
    ResponseResult getArticleDetail(Long id);

    ResponseResult updateViewCount(Long id);

    ResponseResult add(AddArticleDto article);
}

