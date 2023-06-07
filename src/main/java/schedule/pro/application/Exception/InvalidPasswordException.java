package schedule.pro.application.Exception;

public class InvalidPasswordException extends Exception {
    public InvalidPasswordException(){
        super("Password is invalid ");
    }
}
