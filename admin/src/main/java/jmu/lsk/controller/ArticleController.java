package jmu.lsk.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import jmu.lsk.domain.ResponseResult;
import jmu.lsk.domain.dto.AddArticleDto;
import jmu.lsk.domain.dto.TagListDto;
import jmu.lsk.domain.entity.SysMenu;
import jmu.lsk.domain.vo.ArticleDetailVo;
import jmu.lsk.service.ArticleService;
import jmu.lsk.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public ResponseResult add(@RequestBody AddArticleDto article){
        return articleService.add(article);
    }

    @GetMapping("/list")
    public ResponseResult list( Integer pageNum,Integer pageSize,String title,String summary){
        return articleService.articleList(pageNum,pageSize,title,summary);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "获取文章")
    @ApiImplicitParam(name = "id",value = "文章id")
    public ResponseResult getArticle(@PathVariable Long id){
        return articleService.getArticleDetail(id);
    }

    @PutMapping
    @ApiOperation(value = "编辑文章")
    @ApiImplicitParam(name = "AddArticleDto",value = "文章Dto")
    public ResponseResult updateArticle(@RequestBody AddArticleDto addArticleDto){
        return articleService.updateArticle(addArticleDto);
    }

}