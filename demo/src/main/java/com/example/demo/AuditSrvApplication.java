package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.rabbit.ConsumeLogout;
import com.example.demo.rabbit.CreateAuditLog;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class AuditSrvApplication {

	@Autowired
	CreateAuditLog createAuditLog;

	@Autowired
	ConsumeLogout consumeLogout;

	@PostConstruct
	public void init() {
		createAuditLog.init();
		consumeLogout.init();
	}

	public static void main(String[] args) {
		SpringApplication.run(AuditSrvApplication.class, args);
	}

}
