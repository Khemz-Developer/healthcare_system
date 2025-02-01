package com.devstack.healthcare.system.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class UserRoleHasUserKey implements Serializable {

    @Column(name = "user_id")
    private long user;
    //private long userId;

    @Column(name = "role_id")
    private long userRole;
    //private long roleId;
}
