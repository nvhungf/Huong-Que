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
public class Policy {

    @Id
    @Column(name = "id_policy", nullable = false)
    private Integer idPolicy;

    @Basic
    @Column(name = "points", nullable = false)
    private Integer points;

    @Basic
    @Column(name = "unit_amount", nullable = false, precision = 0)
    private Double unitAmount;

    @Basic
    @Column(name = "active", nullable = false)
    private Boolean active;

    @Column(name = "name")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Policy policy = (Policy) o;
        return idPolicy == policy.idPolicy && points == policy.points && Double.compare(policy.unitAmount, unitAmount) == 0 && active == policy.active;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPolicy, points, unitAmount, active);
    }
}
