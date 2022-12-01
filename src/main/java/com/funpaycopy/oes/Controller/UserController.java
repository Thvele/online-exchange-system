package com.funpaycopy.oes.Controller;

import com.funpaycopy.oes.Model.User;
import com.funpaycopy.oes.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public User saveUser(@RequestBody User user){

        return userService.saveUser(user);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserbyId(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/usersSA/{id}")
    public ResponseEntity<Map<String, Boolean>> deactivateUser(@PathVariable("id") Long id){
        boolean deleted = userService.deactivateUser(id);
        Map<String, Boolean> result = new HashMap<>();
        result.put("deleted", deleted);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable("id") Long id){
        boolean deleted = userService.deleteUser(id);
        Map<String, Boolean> result = new HashMap<>();
        result.put("deleted", deleted);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/Uusers/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id,
                                           @RequestBody User user) {

        user = userService.updateUser(id, user);
        return ResponseEntity.ok(user);
    }
}
