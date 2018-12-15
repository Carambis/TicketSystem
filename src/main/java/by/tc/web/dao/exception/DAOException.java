package by.tc.web.dao.exception;

public class DAOException extends Exception{
    private static final long serialVersionUID = -7747725748637417035L;

    public DAOException() {}

    public DAOException(String message) {
        super(message);
    }

    public DAOException(Exception e) {
        super(e);
    }

    public DAOException(String message, Exception e) {
        super(message, e);
    }
}
