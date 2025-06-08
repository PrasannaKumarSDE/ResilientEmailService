package com.example.resilient.email.sending.service.Service;




import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.resilient.email.sending.service.Model.EmailRequest;
import com.example.resilient.email.sending.service.Providers.EmailProvider;
import com.example.resilient.email.sending.service.Providers.MockProvider1;
import com.example.resilient.email.sending.service.Providers.MockProvider2;
import com.example.resilient.email.sending.service.utils.IdempotencyStore;
import com.example.resilient.email.sending.service.utils.RateLimiter;

@Service
public class EmailService {
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    private final EmailProvider provider1 = new MockProvider1();
    private final EmailProvider provider2 = new MockProvider2();
    private final IdempotencyStore store = new IdempotencyStore();
    private final RateLimiter rateLimiter = new RateLimiter();

    public String sendEmail(EmailRequest request) {
        if (store.isDuplicate(request.getRequestId())) {
            logger.info("Duplicate email request: {}", request.getRequestId());
            return "Duplicate request. Not sent.";
        }

        if (!rateLimiter.allow()) {
        	 logger.warn("Rate limit exceeded for request: {}", request.getRequestId());
             return "Rate limit exceeded. Try later.";
         }

         long backoff = 1000;
         int maxRetries = 3;

         logger.info("Trying Provider 1");
         for (int i = 0; i < maxRetries; i++) {
             if (provider1.send(request)) {
                 logger.info("Email sent via Provider 1 on attempt {}", i + 1);
                 return "Sent via Provider 1";
             }
             try { Thread.sleep(backoff); } catch (InterruptedException ignored) {}
             backoff *= 2;
         }

         logger.info("Falling back to Provider 2");
         for (int i = 0; i < maxRetries; i++) {
             if (provider2.send(request)) {
                 logger.info("Email sent via Provider 2 on attempt {}", i + 1);
                 return "Sent via Provider 2";
             }
             try { Thread.sleep(backoff); } catch (InterruptedException ignored) {}
             backoff *= 2;
         }

         logger.error("Failed to send email using both providers.");
         return "Failed to send after retries and fallback.";
        }
    private final List<EmailRequest> storedRequests = new ArrayList<>();


    public List<EmailRequest> getAllRequests() {
        return storedRequests;
    }
    
}