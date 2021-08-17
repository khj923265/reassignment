package com.rb.rbassignment.service;

import com.rb.rbassignment.repository.TestRepository;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    private final TestRepository testRepository;

    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public String getTest() {
        return testRepository.getTest();
    }
}
