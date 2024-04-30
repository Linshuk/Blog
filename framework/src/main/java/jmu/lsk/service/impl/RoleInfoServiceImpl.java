package jmu.lsk.service.impl;

import jmu.lsk.domain.LoginUser;
import jmu.lsk.domain.ResponseResult;
import jmu.lsk.domain.entity.SysUser;
import jmu.lsk.domain.vo.AdminUserInfoVo;
import jmu.lsk.domain.vo.UserInfoVo;
import jmu.lsk.service.RoleInfoService;
import jmu.lsk.service.SysMenuService;
import jmu.lsk.service.SysRoleService;
import jmu.lsk.utils.BeanCopyUtils;
import jmu.lsk.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleInfoServiceImpl implements RoleInfoService {

    @Autowired
    SysMenuService sysMenuService;

    @Autowired
    SysRoleService roleService;

    @Override
    public ResponseResult getInfo() {
            //获取当前登录的用户
            LoginUser loginUser = SecurityUtils.getLoginUser();
            //根据用户id查询权限信息
            List<String> perms = sysMenuService.selectPermsByUserId(loginUser.getUser().getId());
            //根据用户id查询角色信息
            List<String> roleKeyList = roleService.selectRoleKeyByUserId(loginUser.getUser().getId());

            //获取用户信息
            SysUser user = loginUser.getUser();
            UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
            //封装数据返回

            AdminUserInfoVo adminUserInfoVo = new AdminUserInfoVo(perms,roleKeyList,userInfoVo);
            return ResponseResult.okResult(adminUserInfoVo);

    }
}
