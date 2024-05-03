package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Subscriber;
import com.example.demo.repository.SubscriberRepository;
import com.example.demo.service.SubscriberService;

@Service
public class SubscriberServiceImpl implements SubscriberService{

	@Autowired SubscriberRepository subsriberRepo;
	
	@Override
	public void subsriberEmail(Subscriber subscriber) {
			subsriberRepo.save(subscriber);	
	}

}
