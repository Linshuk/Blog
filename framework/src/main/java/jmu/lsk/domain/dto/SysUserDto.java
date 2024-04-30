package jmu.lsk.domain.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "用户dto")
public class SysUserDto {

    @TableId
    @ApiModelProperty(notes = "主键")
    private Long id;

    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(notes = "用户名")
    private String userName;

    @NotBlank(message = "昵称不能为空")
    @ApiModelProperty(notes = "昵称")
    private String nickName;

    @NotBlank(message = "密码不能为空")
    @Size(min=6,max=12,message = "密码长度错误")
    @Pattern(regexp = "^[a-zA-Z0-9_]*$",message = "密码格式错误")
    @ApiModelProperty(notes = "密码")
    private String password;

    @ApiModelProperty(notes = "用户类型：0代表普通用户，1代表管理员")
    private String type;

    @ApiModelProperty(notes = "账号状态（0正常 1停用）")
    private String status;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式错误")
    @ApiModelProperty(notes = "邮箱")
    private String email;

    @ApiModelProperty(notes = "手机号")
    private String phonenumber;

    @ApiModelProperty(notes = "用户性别（0男，1女，2未知）")
    private String sex;

    @ApiModelProperty(notes = "头像")
    private String avatar;

    @ApiModelProperty(notes = "创建人的用户id")
    private Long createBy;

    @ApiModelProperty(notes = "创建时间")
    private Date createTime;

    @ApiModelProperty(notes = "更新人")
    private Long updateBy;

    @ApiModelProperty(notes = "更新时间")
    private Date updateTime;

    @ApiModelProperty(notes = "删除标志（0代表未删除，1代表已删除）")
    private Integer delFlag;
}
