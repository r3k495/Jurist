package com.example.jurist;

public class jurist_lawyer {

    String first_name;
    String last_name;
    String email;
    String phone_no;
    String reference_no;


    public jurist_lawyer(){



    }

    public jurist_lawyer( String first_name, String last_name, String email, String phone_no, String reference_no) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone_no = phone_no;
        this.reference_no = reference_no;


    }


    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public String getReference_no() {
        return reference_no;
    }

}
