package jmu.lsk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jmu.lsk.domain.ResponseResult;
import jmu.lsk.domain.entity.SysUser;


/**
 * 用户表(SysUser)表服务接口
 *
 * @author makejava
 * @since 2024-04-28 13:21:37
 */
public interface BlogLoginService extends IService<SysUser> {

    ResponseResult login(SysUser user);

    ResponseResult logout();
}
