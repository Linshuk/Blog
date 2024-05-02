package jmu.lsk.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserDto {

    private Long id;

    @NotBlank
    private  String userName;

    @NotBlank
    private String nickName;

    @NotBlank
    private String password;

    @NotBlank
    private  String phonenumber;

    @NotBlank
    @Email
    private String email;

    private String sex;

    private String status;

    private List<Long> roleIds;
}
