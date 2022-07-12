package org.hcl.cisco.gitoperations.service;

import org.hcl.cisco.gitoperations.model.EmailConfiguration;

public interface SendEmailService {
    String sendEmail(EmailConfiguration emailDetails);
}
