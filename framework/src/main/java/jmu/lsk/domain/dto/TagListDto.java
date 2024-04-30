package jmu.lsk.domain.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 标签(Tag)表实体类
 *
 * @author makejava
 * @since 2024-04-29 20:38:48
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagListDto {

    private Long id;

    @ApiModelProperty(notes = "标签名")
    @NotBlank(message = "标签名不能为空")
    private String name;

    @ApiModelProperty(notes = "备注")
    @NotBlank(message = "备注不能为空")
    private String remark;
}
