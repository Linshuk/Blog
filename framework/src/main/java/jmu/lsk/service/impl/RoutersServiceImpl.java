package jmu.lsk.service.impl;

import io.swagger.annotations.ApiOperation;
import jmu.lsk.domain.ResponseResult;
import jmu.lsk.domain.entity.SysMenu;
import jmu.lsk.domain.vo.RoutersVo;
import jmu.lsk.service.RoutersService;
import jmu.lsk.service.SysMenuService;
import jmu.lsk.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoutersServiceImpl implements RoutersService {

    @Autowired
    SysMenuService sysMenuService;

    @Override
    public ResponseResult getRouter() {
        Long userId = SecurityUtils.getUserId();
        //查询menu 结果是tree的形式
        List<SysMenu> menus = sysMenuService.selectRouterMenuTreeByUserId(userId);
        //封装数据返回
        return ResponseResult.okResult(new RoutersVo(menus));
    }
}
