package by.tc.web.service;

import by.tc.web.entity.user.Ban;
import by.tc.web.entity.user.User;
import by.tc.web.service.exception.ServiceException;

import java.util.List;

public interface UserService {
    User authorization(String login, String password) throws ServiceException;

    boolean registration(User user) throws ServiceException;

    boolean addUser(User user) throws ServiceException;

    boolean editUser(User user) throws ServiceException;

    boolean deleteUser(int userId) throws ServiceException;

    List<User> getAllUser(String lang) throws ServiceException;

    List<Ban> getAllBanReason(String lang) throws ServiceException;

    void banUser(int userId, Integer banId) throws ServiceException;

    boolean editProfile(User user) throws ServiceException;
}
