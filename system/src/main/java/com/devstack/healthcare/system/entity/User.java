package com.devstack.healthcare.system.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor // RequiredArgsConstructor is used to generate a constructor with required fields
public class User {
    @Id
    private long id;

    private String name;

    private String email;

    private String password;

    private boolean isAccountNonExpired; // isAccountNonExpired Means user's account is not expired

    private boolean isCredentialsNonExpired; // isCredentialsNonExpired Means user's password is not expired

    private boolean isAccountNonLocked; // isAccountNonLocked Means user is not blocked by admin

    private boolean isEnabled; // is enabled Means user is active or not

    @OneToMany(mappedBy = "user") // mappedBy is used to map the relationship between two entities - User and UserRoleHasUser
    // user can have multiple roles which stored in UserRoleHasUser
    private Set<UserRoleHasUser> userRoleHasUsers;

    public boolean getIsAccountNonExpired() {
        return isAccountNonExpired;
    }
    public boolean getIsCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public boolean getIsAccountNonLocked() {
        return isAccountNonLocked;
    }

    public boolean getIsEnabled() {
        return isEnabled;
    }

}
