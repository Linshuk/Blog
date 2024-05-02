package jmu.lsk.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class AddRoleDto {

    private Long id;

    private String roleName;

    private String roleKey;

    private Integer roleSort;

    private String Status;

    private List<Long> menuIds;

    private String remark;
}
