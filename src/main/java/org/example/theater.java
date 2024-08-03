package org.example;


import java.util.ArrayList;

public class theater {
    String name="";
    String address="";
    String phone="";
    int capacity=0;
    ArrayList<Boolean> seatsBooked=new ArrayList<>(){};
    public theater(String name,String address,String phone,int capacity,ArrayList<Boolean> seatsBooked){
        this.name=name;
        this.address=address;
        this.phone=phone;
        this.capacity=capacity;
        this.seatsBooked=seatsBooked;
    }

    //getters
    public String getName(){
        return name;
    }
    public String getAddress(){
        return address;
    }
    public String getPhone(){
        return phone;
    }
    public int getCapacity(){
        return capacity;
    }
    public ArrayList<Boolean> getSeatsBooked(){
        return seatsBooked;
    }
    //setters
    public void setCapacity(int capacity){
        this.capacity=capacity;
    }
    public void setSeatsBooked(ArrayList<Boolean> seatsBooked){
        this.seatsBooked=seatsBooked;
    }

}
