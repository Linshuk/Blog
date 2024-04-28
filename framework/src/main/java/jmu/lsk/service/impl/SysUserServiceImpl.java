package jmu.lsk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jmu.lsk.domain.entity.SysUser;
import jmu.lsk.mapper.SysUserMapper;
import jmu.lsk.service.SysUserService;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
}
