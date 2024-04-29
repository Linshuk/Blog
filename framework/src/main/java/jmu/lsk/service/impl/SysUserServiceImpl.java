package jmu.lsk.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jmu.lsk.domain.ResponseResult;
import jmu.lsk.domain.entity.SysUser;
import jmu.lsk.domain.vo.UserInfoVo;
import jmu.lsk.enums.AppHttpCodeEnum;
import jmu.lsk.exception.SystemException;
import jmu.lsk.mapper.SysUserMapper;
import jmu.lsk.service.SysUserService;
import jmu.lsk.utils.BeanCopyUtils;
import jmu.lsk.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.Valid;

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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseResult register(SysUser user) {

        //对数据进行是否存在的判断
        if(userNameExist(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        if(nickNameExist(user.getNickName())){
            throw new SystemException(AppHttpCodeEnum.NICKNAME_EXIST);
        }
        //...
        //对密码进行加密

        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        //存入数据库
        save(user);
        return ResponseResult.okResult();
    }

    private boolean nickNameExist(String nickName) {
        return count(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getNickName,nickName))>0;
    }

    private boolean userNameExist(String userName) {
        return count(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUserName,userName))>0;
    }
}
