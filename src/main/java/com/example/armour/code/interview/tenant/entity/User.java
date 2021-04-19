package com.example.armour.code.interview.tenant.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 User Class User can have many roles.
 */

@Entity
@Table(name = "tbl_user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    Integer userId;

    @Size(max = 100)
    @Column(name = "full_name",nullable = false)
    String fullName;

    @Size(max = 10)
    @Column(name = "gender",nullable = false)
    String gender;

    @Size(max = 50)
    @Column(name = "user_name",nullable = false,unique = true)
    String userName;

    @Size(max = 100)
    @Column(name = "password",nullable = false)
    String password;

    @Size(max = 10)
    @Column(name = "status",nullable = false)
    String status;

    @ManyToMany(cascade = CascadeType.DETACH,fetch = FetchType.EAGER)
    @JoinTable(
            name = "tbl_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns =@JoinColumn(name = "role_id")
    )
    @Builder.Default
    Set<Role> roles = new HashSet();

}
