package com.devopsbuddy.backend.service;

import com.devopsbuddy.web.domain.frontend.FeedbackPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

public abstract class AbstractEmailService implements EmailService{

    @Value("${default.to.address}")
    private String defaultToAddress;

    protected SimpleMailMessage prepareSimpleMailMessageFromFeedback(FeedbackPojo feedbackPojo){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(defaultToAddress);
        message.setFrom(feedbackPojo.getEmail());
        message.setSubject("[DevOPs Buddy]: Feedback received from "+feedbackPojo.getFirstName() + " " + feedbackPojo.getLastName() + "!");
        message.setText(feedbackPojo.getFeedback());
        return message;

    }



    @Override
    public void sendFeedbackEmail(FeedbackPojo feedbackPojo) {
        sendGenericEmailMessage(prepareSimpleMailMessageFromFeedback(feedbackPojo));
    }
}
