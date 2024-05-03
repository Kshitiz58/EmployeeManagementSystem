package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Subscriber;

public interface SubscriberRepository extends JpaRepository<Subscriber, String>{

	
}
