package com.example.project.bookmyshowbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tickets")
@Builder
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "alloted_seats", nullable = false)
    private String allottedSeats;

    @Column(name = "amount", nullable = false)
    private double amount;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "booked_at", nullable = false)
    private Date bookedAt;

    @ManyToOne
    @JsonIgnore
    @JoinColumn
    private UserEntity user; //this is foreign key that will connect to the user table

    @ManyToOne
    @JsonIgnore
    private ShowEntity show;

    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ShowSeatEntity> seats;

}
