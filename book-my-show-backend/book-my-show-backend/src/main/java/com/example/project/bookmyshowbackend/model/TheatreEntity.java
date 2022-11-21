package com.example.project.bookmyshowbackend.model;

import com.example.project.bookmyshowbackend.enums.SeatType;
import com.example.project.bookmyshowbackend.enums.TheatreType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "theatre")
public class TheatreEntity {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    TheatreType type;

    @OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ShowEntity> shows;

    @OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TheatreSeatEntity> seats;


}
