package jmu.lsk.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jmu.lsk.domain.ResponseResult;
import jmu.lsk.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("link")
@Api(tags = "友链",description = "友链相关接口")
public class LinkController {

    @Autowired

    private LinkService linkService;


    @GetMapping("/getAllLink")
    @ApiOperation(value ="友链列表",notes = "获取友链列表")
    public ResponseResult getAllLink(){
        return linkService.getAllLink();
    }

}