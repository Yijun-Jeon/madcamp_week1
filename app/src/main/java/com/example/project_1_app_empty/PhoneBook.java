package com.example.project_1_app_empty;

public class PhoneBook {

    private String name;
    private String phone;

    public PhoneBook(String new_name, String new_phone){
        name = new_name;
        phone = new_phone;
    }

    public String getName() {return name;}
    public String getPhone(){return phone;}

    public void setName(String new_name){name = new_name;}
    public void setPhone(String new_phone){phone = new_phone;}

    @Override
    public String toString() {
        return "PhoneBook{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
