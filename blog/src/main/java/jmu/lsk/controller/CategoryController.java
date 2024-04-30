package jmu.lsk.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jmu.lsk.domain.ResponseResult;
import jmu.lsk.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
@Api(tags = "目录",description = "评目录相关接口")
public class CategoryController {

    @Autowired
    //CategoryService是我们在huanf-framework工程里面写的接口
    private CategoryService categoryService;

    @GetMapping("/getCategoryList")
    @ApiOperation(value = "目录列表",notes = "获取文章目录")
    //ResponseResult是我们在huanf-framework工程里面写的实体类
    public ResponseResult getCategoryList(){
        return categoryService.getCategoryList();
    }

}