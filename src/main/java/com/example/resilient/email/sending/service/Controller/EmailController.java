package com.example.resilient.email.sending.service.Controller;

import com.example.resilient.email.sending.service.Model.EmailRequest;
import com.example.resilient.email.sending.service.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest request) {
        String response = emailService.sendEmail(request);
        return ResponseEntity.ok(response);
        
       
    }
}
