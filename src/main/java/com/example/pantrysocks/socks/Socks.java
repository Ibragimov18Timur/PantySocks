package com.example.pantrysocks.socks;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Component

// Spring jpa jars.
@Entity
@Table(name= "Socks")
@Data
public class Socks {
    @Id
    private String id;
    private String color;
    private int cottonPart;
    private int quantity;
}
