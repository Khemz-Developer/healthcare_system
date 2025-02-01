package com.devstack.healthcare.system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRoleHasUser {

    @EmbeddedId
    private UserRoleHasUserKey id = new UserRoleHasUserKey();

    @ManyToOne // Many-to-One relationship between User and UserRoleHasUser | Many UserRoleHasUser entries can point to the same User.
    @MapsId("user") //  Uses the user field from UserRoleHasUserKey as a foreign key.
    @JoinColumn(name = "user_id", nullable = false) // The database column storing the User's ID. | Foreign Key: user_id in the user_role_has_user table.
    private User user;

    @ManyToOne // Many-to-One relationship between UserRole and UserRoleHasUser | Many UserRoleHasUser entries can point to the same UserRole.
    @MapsId("userRole")
    @JoinColumn(name = "role_id", nullable = false) // role_id is a foreign key in UserRoleHasUser table
    private UserRole userRole;

}

//This is a bridge table that connects Users and User Roles (Many-to-Many relationship).
