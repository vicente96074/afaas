package com.kojstarinnovations.afaas.us.domain.service;

import com.kojstarinnovations.afaas.us.application.uc.BranchServiceFeignC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BranchService {

    public boolean existsStoreById(String id) {
        return Objects.requireNonNull(branchServiceFeignC.existsStoreById(id).getBody()).isExists();
    }

    public boolean existsStoreBranchById(String id) {
        return Objects.requireNonNull(branchServiceFeignC.existsStoreBranchById(id).getBody()).isExists();
    }

    @Autowired
    public BranchService(BranchServiceFeignC branchServiceFeignC) {
        this.branchServiceFeignC = branchServiceFeignC;
    }

    private final BranchServiceFeignC branchServiceFeignC;
}