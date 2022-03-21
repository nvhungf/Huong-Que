package com.fpoly.huongque.duantotnghiep.service;

import com.fpoly.huongque.duantotnghiep.entity.Policy;

import java.util.List;

public interface PolicyService {

    List<Policy> getAll();

    Policy save(Policy policy);

    Policy remove(Policy policy);
}
