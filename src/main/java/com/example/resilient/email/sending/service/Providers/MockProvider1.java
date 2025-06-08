package com.example.resilient.email.sending.service.Providers;

import com.example.resilient.email.sending.service.Model.EmailRequest;

public class MockProvider1 implements EmailProvider{

	@Override
	public boolean send(EmailRequest request) {
		// TODO Auto-generated method stub
		return Math.random() > 0.3;
	}
	
	

}
