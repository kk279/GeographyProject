package com.geography.demo;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    GeometryFactory geometryFactory = new GeometryFactory();

    public User addUser(Double latitude, Double longitude) {

//        Point point = geometryFactory.createPoint(new Coordinate(latitude, longitude));
        Point point = new Point(latitude, longitude);

        User user = new User();
        user.setLocation(point);

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
