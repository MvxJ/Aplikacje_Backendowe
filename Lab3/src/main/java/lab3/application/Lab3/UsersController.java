package lab3.application.Lab3;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;

    @RequestMapping("api/users")
    @ResponseBody
    public UsersPage getUsers(
            @RequestParam(defaultValue = "1", name="page-number") int pageNumber,
            @RequestParam(defaultValue = "20", name="page-size") int pageSize
    ) {
        return usersService.getUsers(pageNumber, pageSize);
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
        return usersService.createUser(user);
    }

    @RequestMapping(
            value = "api/users/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity getUser(@PathVariable Integer id) throws JsonProcessingException {
        return usersService.getUser(id);
    }

    @RequestMapping(
            value = "api/users/{id}/update",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity updateUser(@PathVariable Integer id, @RequestBody UserEntity user) throws JsonProcessingException {
        try {
            return usersService.updateUser(id, user);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"result\":\"true\"}");
        }
    }

    @RequestMapping(
            value = "api/users/{id}/remove",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity deleteUser(@PathVariable Integer id)
    {
        try {
            usersService.deleteUsers(id);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"result\":\"true\"}");
        }

        return null;
    }
}
