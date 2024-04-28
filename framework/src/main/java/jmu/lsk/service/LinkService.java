package jmu.lsk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jmu.lsk.domain.ResponseResult;
import jmu.lsk.domain.entity.Link;


/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2024-04-27 23:45:38
 */
public interface LinkService extends IService<Link> {
    ResponseResult getAllLink();
}
