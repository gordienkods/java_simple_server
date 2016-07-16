package exceptions;

/**
 * Created by Димон on 16.07.2016.
 */
public class InternalServerError extends Error {

    public InternalServerError(String msg){
        super(msg);
    }

    public InternalServerError(){
        super();
    }

}
