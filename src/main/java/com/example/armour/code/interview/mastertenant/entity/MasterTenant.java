package com.example.armour.code.interview.mastertenant.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;


@Entity
@Table(name = "tbl_tenant_master")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class MasterTenant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tenant_client_id")
    private Integer tenantClientId;

    @Size(max = 50)
    @Column(name = "db_name",nullable = false)
    private String dbName;

    @Size(max = 100)
    @Column(name = "url",nullable = false)
    private String url;

    @Size(max = 50)
    @Column(name = "user_name",nullable = false)
    private String userName;
    @Size(max = 100)
    @Column(name = "password",nullable = false)
    private String password;
    @Size(max = 100)
    @Column(name = "driver_class",nullable = false)
    private String driverClass;
    @Size(max = 10)
    @Column(name = "status",nullable = false)
    private String status;

}
