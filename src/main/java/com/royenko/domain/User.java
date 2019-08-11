package com.royenko.domain;

import com.royenko.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name ="users")
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name ="first_name")
    private String firstName;
    @Column(name ="last_name")
    private String lastName;
    private String phone;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

}



