package lab3.application.Lab3;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UsersController {

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
        List<UserEntity> users = new ArrayList<>();
        users.add(new UserEntity(1L, "Max", 22));
        users.add(new UserEntity(2L, "Klaudia", 21));
        users.add(new UserEntity(3L, "Szymon", 18));
        users.add(new UserEntity(4L, "Szymon", 22));


        //Hashmap .Values

        UsersPage userPage = new UsersPage();
        userPage.setUsers(users);
        userPage.setPageNumber(pageNumber);
        userPage.setTotalCount(users.size());
        userPage.setPageSize(pageSize);

        return userPage;
    }
}
