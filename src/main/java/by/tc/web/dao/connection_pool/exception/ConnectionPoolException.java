package by.tc.web.dao.connection_pool.exception;

public class ConnectionPoolException extends Exception {

    private static final long serialVersionUID = -1641514563392731991L;

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(String message, Exception e) {
        super(message, e);
    }

    public ConnectionPoolException() {

    }

    public ConnectionPoolException(Exception e) {
        super(e);
    }
}
