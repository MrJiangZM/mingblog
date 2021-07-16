package com.ming.blog.controller;

import com.ming.blog.dao.LocationRepository;
import com.ming.blog.domain.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

@RestController
public class HelloContraller {

    @Autowired
    private LocationRepository locationRepository;

    @RequestMapping("/hello")
    public List<Location> hello() {
        return locationRepository.findAll();
    }

    @RequestMapping("/save")
    public Location save(Long id) {
        Location test = Location.builder().id(id).latitude(1.23f).longtitude(3.21f).type("test").build();
        locationRepository.save(test);
        return test;
    }

    @RequestMapping("/delete")
    public void delete(Long id) {
        locationRepository.deleteById(id);
    }

    @RequestMapping("/update")
    public void delete(Long id, String type) {
        Optional<Location> byId = locationRepository.findById(id);
        byId.ifPresent(v -> {
            v.setType(type);
            locationRepository.save(v);
        });
    }

    @RequestMapping("/findByType")
    public List<Location> findByType(String type) {
        return locationRepository.getLocationsByType(type);
    }

}