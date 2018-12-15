package by.tc.web.dao;

import by.tc.web.dao.exception.DAOException;
import by.tc.web.entity.user.Ban;
import by.tc.web.entity.user.User;
import by.tc.web.entity.user.UserMarkStatus;

import java.util.List;

public interface UserDAO {
    User authorization(String login, String password) throws DAOException;
    void registration(User user) throws DAOException;
    void addUser(User user)throws DAOException;
    void editUser(User user)throws DAOException;
    void deleteUser(int userId)throws DAOException;
    List<User> getAllUser(String lang)throws DAOException;
    List<Ban> getAllBanReason(String lang)throws DAOException;
    void banUser(int userId, Integer banId)throws DAOException;
    void editProfile(User user) throws DAOException;
    void setUsersStatus(List<UserMarkStatus> list) throws DAOException;
}
