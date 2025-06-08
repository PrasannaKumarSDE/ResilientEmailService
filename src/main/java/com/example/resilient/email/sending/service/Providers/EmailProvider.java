package com.example.resilient.email.sending.service.Providers;

import com.example.resilient.email.sending.service.Model.EmailRequest;

public interface EmailProvider {
  boolean send(EmailRequest request);
}
