package com.example.resilient.email.sending.service.utils;

import java.util.HashSet;
import java.util.Set;

public class IdempotencyStore {
	 private final Set<String> sentRequests = new HashSet<>();

	    public synchronized boolean isDuplicate(String requestId) {
	        return !sentRequests.add(requestId);
	        
	        
}
}