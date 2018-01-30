package com.zooplus.sdc.converter.controllers.model;

import lombok.Value;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

import static com.zooplus.sdc.converter.controllers.ValidationConstants.*;

@Value
public class SignUpRequest {

    @NotEmpty(message = USER_NAME_IS_REQUIRED)
    @Pattern(regexp = USER_NAME_PATTERN, message = WRONG_USER_NAME_FORMAT)
    private String userName;
}
