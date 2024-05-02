package jmu.lsk.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jmu.lsk.domain.ResponseResult;
import jmu.lsk.domain.dto.AddUserDto;
import jmu.lsk.domain.entity.*;
import jmu.lsk.domain.vo.PageVo;
import jmu.lsk.domain.vo.UserDetailsVo;
import jmu.lsk.domain.vo.UserInfoVo;
import jmu.lsk.enums.AppHttpCodeEnum;
import jmu.lsk.exception.SystemException;
import jmu.lsk.mapper.SysUserMapper;
import jmu.lsk.service.SysRoleService;
import jmu.lsk.service.SysUserRoleService;
import jmu.lsk.service.SysUserService;
import jmu.lsk.utils.BeanCopyUtils;
import jmu.lsk.utils.SecurityUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    @Override
    public ResponseResult pageUserList(Integer pageNum, Integer pageSize, String status, String userName, String phonenumber) {
        LambdaQueryWrapper<SysUser> lambdaQueryWrapper= new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotBlank(status),SysUser::getStatus,status);
        lambdaQueryWrapper.like(StringUtils.isNotBlank(userName),SysUser::getUserName,userName);
        lambdaQueryWrapper.like(StringUtils.isNotBlank(phonenumber),SysUser::getPhonenumber,phonenumber);

        Page page = new Page(pageNum,pageSize);
        page(page,lambdaQueryWrapper);

        return ResponseResult.okResult(new PageVo(page.getRecords(),page.getTotal()));
    }
    @Autowired
    SysUserRoleService sysUserRoleService;

    @Override
    public ResponseResult addUser(AddUserDto addUserDto) {
        SysUser sysUser = BeanCopyUtils.copyBean(addUserDto,SysUser.class);
        String encodePassword = passwordEncoder.encode(addUserDto.getPassword());
        sysUser.setPassword(encodePassword);
        if(count(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getPhonenumber,addUserDto.getPhonenumber()))>0)
        {
            throw new SystemException(AppHttpCodeEnum.PHONENUMBER_EXIST);
        }
        if(count(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUserName,addUserDto.getUserName()))>0)
        {
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        if(count(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getEmail,addUserDto.getEmail()))>0)
        {
            throw new SystemException(AppHttpCodeEnum.EMAIL_EXIST);
        }
        save(sysUser);
        List<SysUserRole> sysUserRoles = addUserDto.getRoleIds().stream().map(aLong -> new SysUserRole(sysUser.getId(),aLong)).toList();
        sysUserRoleService.saveOrUpdateBatchByMultiId(sysUserRoles);
        return ResponseResult.okResult();
    }

    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public ResponseResult deleteUser(Long id) {
        if(SecurityUtils.getLoginUser().getUser().getId().equals(id))
        {
            throw new SystemException(AppHttpCodeEnum.ERROR_OPERAION);
        }
        sysUserMapper.deleteById(id);
        List<SysUserRole> sysUserRoles = sysUserRoleService.list(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getUserId,id));
        for (SysUserRole sysUserRole:sysUserRoles
        ) {
            sysUserRoleService.deleteByMultiId(sysUserRole);
        }
        return ResponseResult.okResult();
    }

    @Autowired
    SysRoleService sysRoleService;

    @Override
    public ResponseResult getUserDetails(Long id) {

       List<Long> rolesId = sysUserRoleService.list(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getUserId,id))
               .stream()
               .map(SysUserRole::getRoleId).toList();

        return ResponseResult.okResult(new UserDetailsVo(rolesId,sysRoleService.list(),getById(id)));
    }

    @Override
    public ResponseResult updateUser(AddUserDto addUserDto) {
        SysUser sysUser = BeanCopyUtils.copyBean(addUserDto,SysUser.class);
        List<SysUserRole> UserRole = addUserDto.getRoleIds().stream()
                .map(roleId -> new SysUserRole(sysUser.getId(), roleId))
                .collect(Collectors.toList());
        List<SysUserRole> sysUserRoles = sysUserRoleService.list(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getUserId,addUserDto.getId()));
        for (SysUserRole sysUserRole:sysUserRoles
        ) {
            sysUserRoleService.deleteByMultiId(sysUserRole);
        }
        sysUserRoleService.saveOrUpdateBatchByMultiId(UserRole);
        //添加 博客和标签的关联
        updateById(sysUser);
        return ResponseResult.okResult();
    }

    private boolean nickNameExist(String nickName) {
        return count(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getNickName,nickName))>0;
    }

    private boolean userNameExist(String userName) {
        return count(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUserName,userName))>0;
    }
}
