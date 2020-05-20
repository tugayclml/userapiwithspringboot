package com.example.userapi.controller;

import com.example.userapi.Repository.UserPagingAndSortingRepository;
import com.example.userapi.Repository.UserRepository;
import com.example.userapi.customexception.ResourceNotFoundException;
import com.example.userapi.models.User;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@RestController
@RequestMapping(path = "/api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserPagingAndSortingRepository userPagingAndSortingRepository;

    @GetMapping("/users")
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public Optional<User> getUser(@PathVariable long id){
        return userRepository.findById(id);
    }

    @PostMapping("/users")
    public void saveUser(@RequestBody User user) throws Exception {
        String response = sendGet();
        JSONObject obj = new JSONObject(response);
        int tempature = obj.getJSONObject("main").getInt("temp");
        int feels_like = obj.getJSONObject("main").getInt("feels_like");
        int wind_speed = obj.getJSONObject("wind").getInt("speed");
        int wind_deg = obj.getJSONObject("wind").getInt("deg");
        String weather_main_array = obj.getJSONArray("weather").get(0).toString();
        JSONObject weather = new JSONObject(weather_main_array);
        String weather_main = weather.getString("main");

        JSONObject user_weather = new JSONObject();
        user_weather.put("tempature", tempature);
        user_weather.put("feels_like", feels_like);
        user_weather.put("wind_speed", wind_speed);
        user_weather.put("wind_deg", wind_deg);
        user_weather.put("weather", weather_main);

        user.setBirthday(String.valueOf(timeDifferenceNowAndBirthday(user.getBirthday())));
        user.setWeather_details(user_weather.toString());
        userRepository.save(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User user) throws Exception {
        User updatedUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + id));
        updatedUser.setName(user.getName());
        updatedUser.setSurname(user.getSurname());
        updatedUser.setPicture(user.getPicture());
        updatedUser.setBirthday(String.valueOf(timeDifferenceNowAndBirthday(user.getBirthday())));
        userRepository.save(updatedUser);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/users/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable long id) throws Exception{
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + id));
        userRepository.deleteById(id);
        Map<String, Boolean> respone = new HashMap<>();
        respone.put("deleted", Boolean.TRUE);
        return respone;
    }

    @GetMapping("/users/pagination/{pageNumber}")
    public Page<User> getUsersPagination(@PathVariable int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber, 5);
        Page<User> page = userPagingAndSortingRepository.findAll(pageable);
        return page;
    }

    public int timeDifferenceNowAndBirthday(String birthday){
        String[] bday = birthday.split("/");
        int year = Integer.parseInt(bday[2]);
        int month = Integer.parseInt(bday[1]);
        int day = Integer.parseInt(bday[0]);
        LocalDate birthDate = LocalDate.of(year, month, day);
        LocalDate today = LocalDate.now();
        Period period = Period.between(birthDate, today);
        return period.getYears();
    }

    private String sendGet() throws Exception {

        HttpGet request = new HttpGet("http://api.openweathermap.org/data/2.5/weather?id=745042&appid=4e78fa7fef0e4dfb964af67f02475e9f");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // return it as a String
                String result = EntityUtils.toString(entity);
                return result;
            }else{
                return null;
            }
        }

    }
}
