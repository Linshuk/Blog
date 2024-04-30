package jmu.lsk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jmu.lsk.domain.ResponseResult;
import jmu.lsk.domain.dto.TagListDto;
import jmu.lsk.domain.entity.Tag;
import jmu.lsk.domain.vo.PageVo;
import jmu.lsk.domain.vo.TagVo;
import jmu.lsk.enums.AppHttpCodeEnum;
import jmu.lsk.exception.SystemException;
import jmu.lsk.mapper.TagMapper;
import jmu.lsk.service.TagService;
import jmu.lsk.utils.BeanCopyUtils;
import lombok.Synchronized;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 标签(Tag)表服务实现类
 *
 * @author makejava
 * @since 2024-04-29 20:38:48
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Override
    public ResponseResult pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        //分页查询
            LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.like(StringUtils.hasText(tagListDto.getName()),Tag::getName,tagListDto.getName());
            queryWrapper.like(StringUtils.hasText(tagListDto.getRemark()),Tag::getRemark,tagListDto.getRemark());

            Page<Tag> page = new Page<>();
            page.setCurrent(pageNum);
            page.setSize(pageSize);
            page(page, queryWrapper);
            //封装数据返回
            PageVo pageVo = new PageVo(page.getRecords(),page.getTotal());
            return ResponseResult.okResult(pageVo);
        }

    @Override
    @Synchronized
    public ResponseResult addTag(TagListDto tagListDto) {
        if(count(Wrappers.<Tag>lambdaQuery().eq(Tag::getName,tagListDto.getName()))>0)
        {
            throw new SystemException(AppHttpCodeEnum.TAG_EXIST);
        }else{
            Tag tag = BeanCopyUtils.copyBean(tagListDto,Tag.class);
            save(tag);
        }
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteTag(Long id) {
        removeById(id);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getTag(Long id) {
        Tag tag = getById(id);
        TagVo tagVo = BeanCopyUtils.copyBean(tag, TagVo.class);
        return ResponseResult.okResult(tagVo);
    }

    @Override
    public ResponseResult putTag(TagListDto tagDto){
        Tag tag = BeanCopyUtils.copyBean(tagDto,Tag.class);
        updateById(tag);
        return ResponseResult.okResult();
    }

    @Override
    public List<TagVo> listAllTag() {
        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(Tag::getId,Tag::getName);
        List<Tag> list = list(wrapper);
        List<TagVo> tagVos = BeanCopyUtils.copyBeanList(list, TagVo.class);
        return tagVos;
    }
}
