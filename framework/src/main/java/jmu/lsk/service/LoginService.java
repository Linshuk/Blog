package jmu.lsk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jmu.lsk.domain.ResponseResult;
import jmu.lsk.domain.entity.SysUser;

public interface LoginService extends IService<SysUser> {

    ResponseResult login(SysUser user);

    ResponseResult logout();
}
