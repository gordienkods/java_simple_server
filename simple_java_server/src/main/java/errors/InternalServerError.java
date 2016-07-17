package errors;

public class InternalServerError extends Error {

    public InternalServerError(String msg){
        super(msg);
    }

    public InternalServerError(){
        super();
    }

}
