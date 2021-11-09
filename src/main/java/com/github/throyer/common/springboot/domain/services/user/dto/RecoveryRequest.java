package com.github.throyer.common.springboot.domain.services.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RecoveryRequest {

    @Email
    @NotNull
    @NotEmpty
    private String email;
}
