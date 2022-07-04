package com.example.project_1_app_empty;

import java.io.Serializable;

public class PhoneBook implements Serializable,Comparable<PhoneBook> {

    private String name;
    private String phone;

    public PhoneBook(String new_name, String new_phone){
        name = new_name;
        phone = new_phone;
    }

    public String getName() {return name;}
    public String getPhone(){
        if(phone.length() < 9)
            return phone;
        else
            return phone.substring(0,3)+"-"+
                    phone.substring(3,7)+"-"+
                    phone.substring(7);
    }

    public void setName(String new_name){name = new_name;}
    public void setPhone(String new_phone){phone = new_phone;}

    @Override
    public int compareTo(PhoneBook phoneBook) {
        if(name.compareTo(phoneBook.getName()) < 0)
            return -1;
        else if(name.compareTo(phoneBook.getName()) > 0)
            return 1;
        return 0;
    }
}