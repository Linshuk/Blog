package jmu.lsk.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuTreeVo {
    private List<MenuTreeVo> children;

    private Long parentId;

    private String label;

    private Long id;

}
