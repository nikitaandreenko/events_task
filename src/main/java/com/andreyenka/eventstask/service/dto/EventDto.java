package com.andreyenka.eventstask.service.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Data
public class EventDto {


    private Long id;

    @NotNull(message = "topic couldn't be empty")
    private Topic topic;

    private String description;

    @NotNull(message = "organizer couldn't be empty")
    private OrganizerDto organizer;


    private LocalDateTime dateTime;

    @NotNull(message = "location couldn't be empty")
    private LocationDto location;

    @NotNull(message = "status couldn't be empty")
    private Status status = Status.IN_WORK;

    public enum Topic {
        WEDDING,

        BIRTHDAY,

        FUNERAL,

        CORPORATE,

        CHRISTMAS,

        CONFERENCE,

        NO_TOPIC
    }

    public enum Status {
        IN_WORK, COMPLETED, CANCELED
    }

}
