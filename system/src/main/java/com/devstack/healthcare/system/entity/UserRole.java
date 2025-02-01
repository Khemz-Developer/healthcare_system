package com.devstack.healthcare.system.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRole {

        @Id
        private long id;

        private String roleName;

        private String description;

        @OneToMany(mappedBy = "userRole") // mappedBy is used to map the relationship between two entities - UserRole and UserRoleHasUser
        // a role can be assigned to multiple users

        // userRole can have multiple users which stored in UserRoleHasUser
        @Column()
        private Set<UserRoleHasUser> userRoleHasUsers;
}
