package jmu.lsk.controller;

import jmu.lsk.domain.ResponseResult;
import jmu.lsk.domain.entity.Article;
import jmu.lsk.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 35238
 * @date 2023/7/18 0018 21:48
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    //注入公共模块的ArticleService接口
    private ArticleService articleService;

    @GetMapping("/hotArticleList")
    //ResponseResult是huanf-framework工程的domain目录的类
    public ResponseResult hotArticleList(){
        //查询热门文章，封装成ResponseResult返回
        ResponseResult result = articleService.hotArticleList();
        return result;
    }

    @GetMapping("/articleList")
    //ResponseResult是huanf-framework工程的domain目录的类
    public ResponseResult articleList(Integer pageNum,Integer pageSize,Long categoryId){
        return articleService.articleList(pageNum,pageSize,categoryId);
    }

    @GetMapping("/{id}")
    public ResponseResult getArticleDetail(@PathVariable("id") Long id) {//注解里指定的id跟上一行保持一致

        //根据id查询文章详情
        return articleService.getArticleDetail(id);
    }

    @PutMapping("/updateViewCount/{id}")
    public ResponseResult updateViewCount(@PathVariable("id") Long id){
        return articleService.updateViewCount(id);
    }
}