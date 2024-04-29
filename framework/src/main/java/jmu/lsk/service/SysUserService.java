package jmu.lsk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jmu.lsk.domain.ResponseResult;
import jmu.lsk.domain.entity.SysUser;

public interface SysUserService extends IService<SysUser> {
    ResponseResult userInfo();

    ResponseResult updateUserInfo(SysUser user);

    ResponseResult register(SysUser user);
}
