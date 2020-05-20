package com.example.userapi.models;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    public User(){}

    public User(long id, String name, String surname, String birthday){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "picture")
    private String picture;

    @Column(name = "birthday")
    private String birthday;

    @Column(name = "weather_details")
    private String weather_details;

    public long getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getWeather_details() {
        return weather_details;
    }

    public void setWeather_details(String weather_details) {
        this.weather_details = weather_details;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
