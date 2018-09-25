package com.simple.notes.entities;

import lombok.*;

import javax.persistence.*;

/**
 * Created by damodaram.setti on 9/23/2018.
 */
@Entity
@Table(name = "user", schema = "public")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class User {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "password")
    private String password;
}
