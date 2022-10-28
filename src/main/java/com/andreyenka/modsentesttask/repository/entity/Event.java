package com.andreyenka.modsentesttask.repository.entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "events")
@SQLDelete(sql = "update events set status='CANCELED' where id=?")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "topic")
    @Enumerated(EnumType.STRING)
    private Topic topic;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "organizer_id", referencedColumnName = "id")
    private Organizer organizer;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Event event = (Event) o;
        return id != null && Objects.equals(id, event.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
