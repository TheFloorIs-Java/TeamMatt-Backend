package App.Controller;

import App.Models.*;
import App.Service.SessionService;
import App.Service.SpringTestService;
import App.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    private final SpringTestService sts;
    private final UserService us;

    private final SessionService ss;

    @Autowired
    public UserController(SpringTestService sts, UserService us, SessionService ss){
        this.sts = sts;
        this.us = us;
        this.ss = ss;
    }


    @GetMapping("/users/id/{id}")
    public User getUserById(@PathVariable("id") int id){
        User user = us.getUserById(id);
        return user;
    }

    @GetMapping("/users/session")
    public Session getUserBySession(@RequestHeader Map<String, String> headers){
        Session session = null;
        if (headers.get("session") != null)
            session = ss.getSessionInfo(headers.get("session"));
        return session;
    }

    @GetMapping("/users/all")
    public List<User> getAllUsers(){
        List<User> users = us.getAllUsers();
        return users;
    }

    @GetMapping("/login")
    public Session attemptLogin(@RequestBody User user){
        User loggedInUser = us.AttemptLogin(user);
        Session session = null;
        if (loggedInUser != null){
            session = ss.newSession(user);
        }
        return session;
    }

    @PostMapping("/users")
    public User registerUsers(@RequestBody User user){
        User registeredInUser = us.AttemptRegister(user);
        return registeredInUser;
    }

    @PutMapping("/users/id/{id}")
    public User updateUserById(@PathVariable("id") int id, @RequestBody User user){
        User updatedUser = us.updateUserById(user, id);
        return updatedUser;
    }
}