package jmu.lsk.domain.entity;


import java.io.Serializable;

import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 用户和角色关联表(SysUserRole)表实体类
 *
 * @author makejava
 * @since 2024-05-03 00:01:47
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user_role")
public class SysUserRole  {

    @MppMultiId
    private Long userId;

    @MppMultiId
    private Long roleId;

}
