package com.fpoly.huongque.duantotnghiep.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Shipping {

    @Id
    @Column(name = "id_Shipping", nullable = false)
    private Integer idShipping;

    @Basic
    @Column(name = "name_Shipping", nullable = false, length = 100)
    private String nameShipping;

    @Basic
    @Column(name = "phone_Number", nullable = false, length = 11)
    private String phoneNumber;

    @Basic
    @Column(name = "email", nullable = false, length = 200)
    private String email;
}
