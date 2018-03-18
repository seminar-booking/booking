package com.example.member.mail;

import java.util.Map;

public class EmailDTO {

    private String addressToSend;

    private EmailType emailType;

    private Map<String, Object> variables;

    public String getAddressToSend() {
        return addressToSend;
    }

    public void setAddressToSend(String addressToSend) {
        this.addressToSend = addressToSend;
    }

    public EmailType getEmailType() {
        return emailType;
    }

    public void setEmailType(EmailType emailType) {
        this.emailType = emailType;
    }

    public Map<String, Object> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, Object> variables) {
        this.variables = variables;
    }
}
