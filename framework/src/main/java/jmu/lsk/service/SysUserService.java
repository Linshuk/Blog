package jmu.lsk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jmu.lsk.domain.ResponseResult;
import jmu.lsk.domain.dto.AddUserDto;
import jmu.lsk.domain.entity.SysUser;

public interface SysUserService extends IService<SysUser> {
    ResponseResult userInfo();

    ResponseResult updateUserInfo(SysUser user);

    ResponseResult register(SysUser user);

    ResponseResult pageUserList(Integer pageNum, Integer pageSize, String status, String userName, String phonenumber);

    ResponseResult addUser(AddUserDto addUserDto);

    ResponseResult deleteUser(Long id);

    ResponseResult getUserDetails(Long id);

    ResponseResult updateUser(AddUserDto addUserDto);
}
