package za.ac.tut.u220390519.taskmanagementsystem.exception;


import javax.naming.AuthenticationException;

public class ClassAlreadyExistsException extends AuthenticationException {

    public ClassAlreadyExistsException(String explanation) {
        super("This class already exists");
    }
}
