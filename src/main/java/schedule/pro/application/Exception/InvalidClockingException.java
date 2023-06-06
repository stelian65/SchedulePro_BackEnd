package schedule.pro.application.Exception;


public class InvalidClockingException extends Exception{

    public  InvalidClockingException(){
        super("Illegal clocking format");
    }
}
