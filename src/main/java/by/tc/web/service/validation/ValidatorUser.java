package by.tc.web.service.validation;

import by.tc.web.entity.user.User;


public class ValidatorUser {
    private final static String VALID_LOGIN = "^[a-zA-Z](.[a-zA-Z0-9_-]*)$";
    private final static String VALID_PASSWORD = "^(?!.*admin)[A-Za-z0-9_]{8,}$";
    private final static String VALID_EMAIL = "^((([0-9A-Za-z]{1}[-0-9A-z\\.]{1,}[0-9A-Za-z]{1}))@([-A-Za-z]{1,}\\.){1,2}[-A-Za-z]{2,})$";
    private static final int LENGTH = 20;

    public static boolean authValidator(String login, String password) {
        if (login.isEmpty() || password.isEmpty()) {
            return false;
        }
        return true;
    }

    public static boolean regValidator(User user) {
        if (user.getLogin().isEmpty()
                || user.getPassword().isEmpty()
                || user.geteMail().isEmpty()
                || user.getNickname().isEmpty()) {
            return false;
        }
        if (user.getLogin().length() < 4 || user.getLogin().length() > LENGTH
                || user.getPassword().length() < 8 || user.getPassword().length() > LENGTH) {
            return false;
        }

        if (!user.getLogin().matches(VALID_LOGIN)) {
            return false;
        }
        if (!user.getPassword().matches(VALID_PASSWORD)) {
            return false;
        }
        if (!user.geteMail().matches(VALID_EMAIL)) {
            return false;
        }
        return true;
    }

    public static boolean editValidator(User user) {
        if (!regValidator(user)) {
            return false;
        }
        if (user.getPathToImage() == null || user.getPathToImage().isEmpty()) {
            return false;
        }
        return true;
    }

    public static boolean banValidator(int userId, Integer banId) {
        return true;
    }

    public static boolean profileValidator(User user)
    {
        if (user.getPassword().isEmpty()
                || user.geteMail().isEmpty()
                || user.getNickname().isEmpty()) {
            return false;
        }
        if (!user.getPassword().matches(VALID_PASSWORD)) {
            return false;
        }
        if (!user.geteMail().matches(VALID_EMAIL)) {
            return false;
        }
        return true;
    }
}
