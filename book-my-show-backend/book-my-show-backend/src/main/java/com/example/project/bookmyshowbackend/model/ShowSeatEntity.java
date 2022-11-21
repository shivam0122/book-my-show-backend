package com.example.project.bookmyshowbackend.model;


import com.example.project.bookmyshowbackend.enums.SeatType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(value = { AuditingEntityListener.class})
@Table(name = "show_seats")
@Builder
@ToString
public class ShowSeatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "rate", nullable = false)
    private int rate;

    @Column(name = "seat_number", nullable = false)
    private String seatNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "seat_type", nullable = false)
    private SeatType seatType;

    @Column(name = "is_booked", columnDefinition = "bit(1) default 0", nullable = false)
    private boolean booked;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "booked_at")
    private Date bookedAt;

    @ManyToOne
    @JsonIgnore
    private ShowEntity show;


    @ManyToOne
    @JsonIgnore
    private TicketEntity ticket;

}
