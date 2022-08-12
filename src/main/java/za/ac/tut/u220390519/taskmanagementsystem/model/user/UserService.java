package za.ac.tut.u220390519.taskmanagementsystem.model.user;

import za.ac.tut.u220390519.taskmanagementsystem.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public void createUser(User user){
        userRepository.save(user);
    }

    public  User findUser(String email){

        User user =  userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));

        return user;
    }

    public  User findUserByUserName(String username){

        User user =  userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        return user;
    }


    public List<User> getAllUsers(){
        List<User> users =  new ArrayList<>();

        Iterator userIterator = userRepository.findAll().iterator();

        while(userIterator.hasNext()){

            User user = (User) userIterator.next();
            users.add(user);
        }
        return users;
    }

}
