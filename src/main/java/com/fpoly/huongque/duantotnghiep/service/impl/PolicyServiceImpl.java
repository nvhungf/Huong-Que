package com.fpoly.huongque.duantotnghiep.service.impl;

import com.fpoly.huongque.duantotnghiep.dao.PolicyDao;
import com.fpoly.huongque.duantotnghiep.entity.Policy;
import com.fpoly.huongque.duantotnghiep.service.PolicyService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PolicyServiceImpl implements PolicyService {

    private final PolicyDao policyDao;

    public PolicyServiceImpl(PolicyDao policyDao) {
        this.policyDao = policyDao;
    }

    @Override
    public List<Policy> getAll() {
        return this.policyDao.findAll();
    }

    @Transactional
    @Override
    public Policy save(Policy policy) {
        try {
            return this.policyDao.save(policy);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Transactional
    @Override
    public Policy remove(Policy policy) {
        try {
            this.policyDao.deleteById(policy.getIdPolicy());
            return policy;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
