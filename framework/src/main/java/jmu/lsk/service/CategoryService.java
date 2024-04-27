package jmu.lsk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jmu.lsk.domain.ResponseResult;
import jmu.lsk.domain.entity.Category;

/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2024-04-27 20:25:28
 */
public interface CategoryService extends IService<Category> {
    ResponseResult getCategoryList();
}
