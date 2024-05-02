package jmu.lsk.domain.vo;

import jmu.lsk.domain.entity.SysRole;
import jmu.lsk.domain.entity.SysUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsVo {

    private List<Long> roleIds;

    private List<SysRole> roles;

    private SysUser user;
}
