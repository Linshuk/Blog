package jmu.lsk.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import jmu.lsk.domain.ResponseResult;
import jmu.lsk.domain.dto.CategoryDto;
import jmu.lsk.domain.dto.TagListDto;
import jmu.lsk.domain.entity.Category;
import jmu.lsk.domain.vo.CategoryVo;
import jmu.lsk.domain.vo.ExcelCategoryVo;
import jmu.lsk.enums.AppHttpCodeEnum;
import jmu.lsk.service.CategoryService;
import jmu.lsk.utils.BeanCopyUtils;
import jmu.lsk.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/content/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    
    @GetMapping("/listAllCategory")
    public ResponseResult listAllCategory(){
        List<CategoryVo> list = categoryService.listAllCategory();
        return ResponseResult.okResult(list);
    }

    @PreAuthorize("@ps.hasPermission('content:category:export')")
    @GetMapping("/export")
    public void export(HttpServletResponse response){
        try {
            //设置下载文件的请求头
            WebUtils.setDownLoadHeader("分类.xlsx",response);
            //获取需要导出的数据
            List<Category> categoryVos = categoryService.list();

            List<ExcelCategoryVo> excelCategoryVos = BeanCopyUtils.copyBeanList(categoryVos, ExcelCategoryVo.class);
            //把数据写入到Excel中
            EasyExcel.write(response.getOutputStream(), ExcelCategoryVo.class).autoCloseStream(Boolean.FALSE).sheet("分类导出")
                    .doWrite(excelCategoryVos);

        } catch (Exception e) {
            //如果出现异常也要响应json
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
            WebUtils.renderString(response, JSON.toJSONString(result));
        }
    }
    @GetMapping("/list")
    @ApiOperation(value = "分类列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "页号"),
            @ApiImplicitParam(name = "pageSize",value = "每页大小"),
            @ApiImplicitParam(name = "name",value = "分类名"),
            @ApiImplicitParam(name = "status",value = "状态")
    })
    public ResponseResult list(Integer pageNum,Integer pageSize,String name,String status){
        return categoryService.pageCategoryList(pageNum,pageSize,name,status);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "获取分类")
    @ApiImplicitParam(name = "id",value = "分类id")
    public ResponseResult getTag(@PathVariable Long id){
        return ResponseResult.okResult(categoryService.getById(id));
    }

    @PutMapping
    @ApiOperation(value = "编辑分类")
    @ApiImplicitParam(name = "categoryDto",value = "分类Dto")
    public ResponseResult putTag(@RequestBody CategoryDto categoryDto){
        return categoryService.putCategory(categoryDto);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "删除分类")
    @ApiImplicitParam(name = "id",value = "分类id")
    public ResponseResult deleteTag(@PathVariable Long id){
        return categoryService.deleteTag(id);
    }

    @PostMapping
    @ApiOperation(value = "添加分类")
    @ApiImplicitParam(name = "categoryDto",value = "分类Dto")
    public ResponseResult addTag(@RequestBody @Valid CategoryDto categoryDto){
        return categoryService.addCategory(categoryDto);
    }

}