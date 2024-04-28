package jmu.lsk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jmu.lsk.domain.ResponseResult;
import jmu.lsk.domain.entity.SysUser;
import jmu.lsk.domain.vo.UserInfoVo;
import jmu.lsk.mapper.SysUserMapper;
import jmu.lsk.service.SysUserService;
import jmu.lsk.utils.BeanCopyUtils;
import jmu.lsk.utils.SecurityUtils;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Override
    public ResponseResult userInfo() {
        Long userId = SecurityUtils.getUserId();
        SysUser sysUser = getById(userId);

        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(sysUser,UserInfoVo.class);
        return ResponseResult.okResult(userInfoVo);
    }

    @Override
    public ResponseResult updateUserInfo(SysUser user) {
        updateById(user);
        return ResponseResult.okResult();
    }
}
