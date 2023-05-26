package com.example.listam.entity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "hashtag")
public class Hashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;

}
