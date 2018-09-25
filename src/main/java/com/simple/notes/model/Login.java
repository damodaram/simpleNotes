package com.simple.notes.model;

import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Created by damodaram.setti on 9/24/2018.
 */
@Builder
@Setter
@Getter
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class Login {

    @NotNull
    private String emailId;
    @NotNull
    private String password;
}
