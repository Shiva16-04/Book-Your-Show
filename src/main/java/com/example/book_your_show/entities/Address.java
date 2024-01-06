package com.example.book_your_show.entities;

import com.example.book_your_show.enums.City;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.net.URL;

@Entity
@Table
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(nullable = false, unique = true)
    String code;
    @Column(nullable = false)
    String plotNo;
    String locality;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    City city;
    @Column(nullable = false)
    Integer pinCode;
    @Column(nullable = false, unique = true)
    URL locationUrl;
    @OneToOne //parent with respect to the theatre #bidirectional mapping
    Theatre theatre;
}
