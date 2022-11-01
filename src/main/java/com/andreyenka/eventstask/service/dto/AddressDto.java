package com.andreyenka.eventstask.service.dto;

import lombok.*;

import javax.validation.constraints.NotNull;


@Data
public class AddressDto {

    @NotNull(message = "id couldn't be empty")
    private Long id;

    @NotNull(message = "country couldn't be empty")
    private String country;

    @NotNull(message = "city couldn't be empty")
    private String city;

    @NotNull(message = "street couldn't be empty")
    private String street;

    @NotNull(message = "house couldn't be empty")
    private String house;

    @NotNull(message = "room couldn't be empty")
    private String room;

}
