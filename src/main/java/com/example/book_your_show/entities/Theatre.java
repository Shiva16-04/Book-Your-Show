package com.example.book_your_show.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "theatre")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(nullable = false, unique = true)
    String code;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    int numberOfScreens;
    @OneToOne(mappedBy = "theatre", cascade = CascadeType.ALL)
//    @JoinColumn(nullable = false, unique = true)
    Address address;
    @OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL) //parent with respect to number of screens
    List<Screen> screens;
}
