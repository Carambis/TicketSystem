package by.tc.web.dao.connection_pool;

import java.util.ResourceBundle;

public class DBResourceManager {
    private final static DBResourceManager instance = new DBResourceManager();
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("db");

    private DBResourceManager(){}
    public static DBResourceManager getInstance() {
        return instance;
    }

    public String getValue(String key) {
        return resourceBundle.getString(key);
    }
}
