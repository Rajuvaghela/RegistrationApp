package com.xtensolution.kptt.model;

import java.io.Serializable;

public class Registrationss implements Serializable {
    public PersonalDetails personalDetails;
    public EmploymentDetails employmentDetails;
    public UploadDetails uploadDetails;
    public ContactDetails contactDetails;


    public PersonalDetails getPersonalDetails() {
        return personalDetails;
    }

    public void setPersonalDetails(PersonalDetails personalDetails) {
        this.personalDetails = personalDetails;
    }

    public EmploymentDetails getEmploymentDetails() {
        return employmentDetails;
    }

    public void setEmploymentDetails(EmploymentDetails employmentDetails) {
        this.employmentDetails = employmentDetails;
    }

    public UploadDetails getUploadDetails() {
        return uploadDetails;
    }

    public void setUploadDetails(UploadDetails uploadDetails) {
        this.uploadDetails = uploadDetails;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }
}
