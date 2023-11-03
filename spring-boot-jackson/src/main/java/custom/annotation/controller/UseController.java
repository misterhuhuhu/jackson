package custom.annotation.controller;

import custom.annotation.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UseController {
    
    @GetMapping("user")
    public User getUser(){
        return User.getUser();
    }
}
