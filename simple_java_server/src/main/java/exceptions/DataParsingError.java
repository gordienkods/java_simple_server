package exceptions;

/**
 * Created by Димон on 16.07.2016.
 */
public class DataParsingError extends InternalServerError {

    public DataParsingError() {
        super();
    }

    public DataParsingError(String msg) {
        super(msg);
    }

}
