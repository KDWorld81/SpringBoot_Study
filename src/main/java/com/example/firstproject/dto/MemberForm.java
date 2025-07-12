package com.example.firstproject.dto;

public class MemberForm {
    private String email;
    private String password;

    public MemberForm(String email, String password){
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Member toEntity(){
        return new Member(null, email, password);
    }

}
