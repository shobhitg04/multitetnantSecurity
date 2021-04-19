package com.example.armour.code.interview.tenant.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

import static lombok.AccessLevel.PRIVATE;

/**
 Permission defines the url is accessible or not.
 */
@Entity
@Table(name = "tbl_permission")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Permission implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Size(max = 100)
    @Column(name = "name",nullable = false)
    private String name;

}
