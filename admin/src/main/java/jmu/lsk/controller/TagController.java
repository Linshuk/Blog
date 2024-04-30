package jmu.lsk.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import jmu.lsk.domain.ResponseResult;
import jmu.lsk.domain.dto.TagListDto;
import jmu.lsk.domain.vo.TagVo;
import jmu.lsk.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/content/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/list")
    @ApiOperation(value = "标签列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "页号"),
            @ApiImplicitParam(name = "pageSize",value = "每页大小"),
            @ApiImplicitParam(name = "tagListDto",value = "标签查询dto")
    })
    public ResponseResult list(Integer pageNum, Integer pageSize, TagListDto tagListDto){
        return tagService.pageTagList(pageNum,pageSize,tagListDto);
    }


    @PostMapping
    @ApiOperation(value = "添加标签")
    @ApiImplicitParam(name = "tagListDto",value = "标签Dto")
    public ResponseResult addTag(@RequestBody @Valid TagListDto tagListDto){
        return tagService.addTag(tagListDto);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除标签")
    @ApiImplicitParam(name = "id",value = "标签id")
    public ResponseResult deleteTag(@PathVariable Long id){
        return tagService.deleteTag(id);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "获取标签")
    @ApiImplicitParam(name = "id",value = "标签id")
    public ResponseResult getTag(@PathVariable Long id){
        return tagService.getTag(id);
    }

    @PutMapping
    @ApiOperation(value = "编辑标签")
    @ApiImplicitParam(name = "tagListDto",value = "标签Dto")
    public ResponseResult putTag(@RequestBody TagListDto tagListDto){
        return tagService.putTag(tagListDto);
    }

    @GetMapping("/listAllTag")
    public ResponseResult listAllTag(){
        List<TagVo> list = tagService.listAllTag();
        return ResponseResult.okResult(list);
    }
}