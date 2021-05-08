package com.example.demo.model;

import com.example.demo.model.enums.AutoCapacity;
import com.example.demo.model.enums.TransportType;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="delegations")
public class Delegation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull int delegationId;
    @NotNull LocalDateTime dateTimeStart;
    @NotNull LocalDateTime dateTimeStop;
    @NotNull float travelDietAmount=30;
    int breakfastNumber=0;
    int dinnerNumber=0;
    int supperNumber=0;
    TransportType transportType;
    float ticketPrice;
    AutoCapacity autoCapacity;
    float km;
    String description;
    float accommodationPrice;
    float otherTicketsPrice;
    String otherOutlayDesc;
    float otherOutlayPrice;

    @ManyToOne(cascade = CascadeType.ALL)
    User user;

    public Delegation(LocalDateTime dateTimeStart, LocalDateTime dateTimeStop,User user) {
        this.dateTimeStart=dateTimeStart;
        this.dateTimeStop=dateTimeStop;
        this.user=user;
    }
    public Delegation(LocalDateTime dateTimeStart, LocalDateTime dateTimeStop) {
        this.dateTimeStart=dateTimeStart;
        this.dateTimeStop=dateTimeStop;
    }
}
