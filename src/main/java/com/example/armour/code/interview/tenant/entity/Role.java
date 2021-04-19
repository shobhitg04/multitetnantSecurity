package com.example.armour.code.interview.tenant.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static lombok.AccessLevel.PRIVATE;

/**
 User Class User can have many roles.
 */
@Entity
@Table(name = "tbl_role")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Size(max = 100)
    @Column(name = "name",nullable = false)
    String name;

    @ManyToMany(cascade = CascadeType.DETACH,fetch = FetchType.EAGER)
    @JoinTable(
            name = "tbl_role_permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns =@JoinColumn(name = "permission_id")
    )
    @Builder.Default
    Set<Permission> permissions = new HashSet();
}
