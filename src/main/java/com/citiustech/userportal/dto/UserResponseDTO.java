package com.citiustech.userportal.dto;


import java.util.List;

import com.citiustech.userportal.Model.AppUserRole;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserResponseDTO {

    @ApiModelProperty(position = 0)
    private Integer id;
    @ApiModelProperty(position = 1)
    private String username;
    @ApiModelProperty(position = 2)
    private String email;
    @ApiModelProperty(position = 3)
    List<AppUserRole> appUserRoles;

}
