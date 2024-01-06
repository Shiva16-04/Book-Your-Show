package com.example.book_your_show.entities;

import com.example.book_your_show.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column( nullable = false)
    String name;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    Role role;
    @Column(nullable = false)
    int age;
    @Column(nullable = false, unique = true)
    @Email(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "should be a valid email address")
    String emailId;
    @Column(nullable = false, unique = true)
    @Pattern(regexp="[0-9]{10}", message="Should be a valid contact number")
    String contactNumber;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Ticket>ticketList;
}
