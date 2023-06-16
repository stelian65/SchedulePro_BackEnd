package schedule.pro.application.Exception;

public class RequestNotFoundException extends Exception{
    public RequestNotFoundException(){
        super("Request not found");
    }
}
