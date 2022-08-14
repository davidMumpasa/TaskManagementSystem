package za.ac.tut.u220390519.taskmanagementsystem.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String email) {
        super("Could not find user with : "+ email);
    }
}
