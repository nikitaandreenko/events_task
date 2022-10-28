package com.andreyenka.modsentesttask.service.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


@Data
public class OrganizerDto {

    @NotNull(message = "id couldn't be empty")
    private Long id;

    @NotNull(message = "name couldn't be empty")
    private String name;

    @NotNull(message = "email couldn't be empty")
    @Email
    private String email;

    @NotNull(message = "address couldn't be empty")
    private AddressDto address;

}

