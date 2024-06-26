package jmu.lsk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jmu.lsk.domain.ResponseResult;
import jmu.lsk.domain.dto.CategoryDto;
import jmu.lsk.domain.entity.Category;
import jmu.lsk.domain.vo.CategoryVo;
import org.springframework.context.annotation.Lazy;

import java.util.List;

/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2024-04-27 20:25:28
 */
public interface CategoryService extends IService<Category> {
    ResponseResult getCategoryList();

    List<CategoryVo> listAllCategory();

    ResponseResult pageCategoryList(Integer pageNum, Integer pageSize, String name, String status);

    ResponseResult putCategory(CategoryDto categoryDto);

    ResponseResult deleteTag(Long id);

    ResponseResult addCategory(CategoryDto categoryDto);
}
