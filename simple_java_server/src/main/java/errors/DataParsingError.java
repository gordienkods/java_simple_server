package errors;

public class DataParsingError extends InternalServerError {

    public DataParsingError() {
        super();
    }

    public DataParsingError(String msg) {
        super(msg);
    }

}
