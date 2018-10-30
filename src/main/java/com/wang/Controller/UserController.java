package com.wang.Controller;

import com.wang.Bean.User;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.*;

/**
 * Created by HuangDanGeeker on 2018/10/30.
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {

    //创建线程安全的Map
    Map<Long, User> users = Collections.synchronizedMap(new HashMap<>());


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<User> getUserList(){

        List<User> result = new ArrayList<>(users.values());

        return result;
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String postUser(@ModelAttribute User user){

        users.put(user.getId(), user);

        return "success";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String putUser(@PathVariable Long id, @ModelAttribute User user){

        User u = users.get(user.getId());
        u.setAge(user.getAge());
        u.setName(user.getName());
        users.put(user.getId(), u);
        return "success";
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        // 处理"/users/{id}"的DELETE请求，用来删除User
        users.remove(id);
        return "success";
    }
}
