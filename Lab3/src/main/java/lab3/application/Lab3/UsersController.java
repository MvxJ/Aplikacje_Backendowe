package lab3.application.Lab3;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashMap;

@Controller
public class UsersController {
    private HashMap<Integer, UserEntity> users = new HashMap<Integer, UserEntity>() {{
        put(1, new UserEntity(1, "Max", 22));
        put(2, new UserEntity(2, "Klaudia", 21));
        put(3, new UserEntity(3, "Szymon", 18));
        put(4, new UserEntity(4, "Szymon", 22));
    }};

    @PostConstruct
    private void onCreate()
    {

    }

    @PreDestroy
    private void onDestruct()
    {

    }

    @Autowired
    private UsersService usersService;

    @RequestMapping("api/users")
    @ResponseBody
    public UsersPage getUsers(
            @RequestParam(defaultValue = "1", name="page-number") int pageNumber,
            @RequestParam(defaultValue = "20", name="page-size") int pageSize
    ) {
        UsersPage userPage = new UsersPage();
        userPage.setUsers(users.values());
        userPage.setPageNumber(pageNumber);
        userPage.setTotalCount(users.size());
        userPage.setPageSize(pageSize);

        return userPage;
    }

    @RequestMapping(
            value = "api/users/create",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public UserEntity createUser(
            @RequestBody UserEntity user
    ) {
        users.put(user.getId(), user);

        return user;
    }

    @RequestMapping(
            value = "api/users/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public String getUser(@PathVariable Integer id) throws JsonProcessingException {
        return UsersService.getUser(users, id);
    }
}
