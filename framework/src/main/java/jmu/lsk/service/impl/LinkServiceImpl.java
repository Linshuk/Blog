package jmu.lsk.service.impl;

import com.aliyun.oss.model.LiveChannel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jmu.lsk.constants.SystemConstants;
import jmu.lsk.domain.ResponseResult;
import jmu.lsk.domain.entity.Link;
import jmu.lsk.domain.vo.LinkVo;
import jmu.lsk.mapper.LinkMapper;
import jmu.lsk.service.LinkService;
import jmu.lsk.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 友链(Link)表服务实现类
 *
 * @author makejava
 * @since 2024-04-27 23:45:38
 */
@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Override
    public ResponseResult getAllLink() {
        LambdaQueryWrapper<Link> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);
        List<Link> links = list(wrapper);
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(links, LinkVo.class);

        return ResponseResult.okResult(linkVos);
    }
}
