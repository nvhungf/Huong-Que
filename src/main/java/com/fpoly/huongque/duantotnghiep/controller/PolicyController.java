package com.fpoly.huongque.duantotnghiep.controller;

import com.fpoly.huongque.duantotnghiep.entity.Policy;
import com.fpoly.huongque.duantotnghiep.service.PolicyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/policies")
public class PolicyController {

    private final PolicyService policyService;

    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(this.policyService.getAll());
    }

    @PostMapping
    public ResponseEntity save(@RequestBody  Policy policy) {
        Policy save = this.policyService.save(policy);
        if (save == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(save);
    }

    @DeleteMapping
    public ResponseEntity remove(@RequestBody Policy policy) {
        Policy removed = this.policyService.remove(policy);
        if (removed == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(policy);
    }
}
