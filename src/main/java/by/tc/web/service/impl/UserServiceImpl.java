package by.tc.web.service.impl;

import by.tc.web.dao.UserDAO;
import by.tc.web.dao.exception.DAOException;
import by.tc.web.dao.factory.DAOFactory;
import by.tc.web.entity.user.Ban;
import by.tc.web.entity.user.User;
import by.tc.web.service.UserService;
import by.tc.web.service.exception.ServiceException;
import by.tc.web.service.validation.ValidatorGeneral;
import by.tc.web.service.validation.ValidatorUser;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

    @Override
    public User authorization(String login, String password) throws ServiceException {
        User user;
        if (!ValidatorUser.authValidator(login, password)) {
            return null;
        }
        try {
            user = userDAO.authorization(login, password);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return user;
    }

    @Override
    public boolean registration(User user) throws ServiceException {
        if (!ValidatorUser.regValidator(user)) {
            return false;
        }
        try {
            userDAO.registration(user);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return true;
    }

    @Override
    public boolean addUser(User user) throws ServiceException {
        if (!ValidatorUser.regValidator(user)) {
            return false;
        }
        try {
            userDAO.addUser(user);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return true;
    }

    @Override
    public boolean editUser(User user) throws ServiceException {
        if (!ValidatorUser.editValidator(user)) {
            return false;
        }
        try {
            userDAO.editUser(user);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return true;
    }

    @Override
    public boolean deleteUser(int userId) throws ServiceException {
        if (!ValidatorGeneral.validationId(userId)) {
            return false;
        }
        try {
            userDAO.deleteUser(userId);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return true;
    }

    @Override
    public List<User> getAllUser(String lang) throws ServiceException {
        List<User> list = null;
        try {
            list = userDAO.getAllUser(lang);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return list;
    }

    @Override
    public List<Ban> getAllBanReason(String lang) throws ServiceException {
        List<Ban> list = null;
        try {
            list =  userDAO.getAllBanReason(lang);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return list;
    }

    @Override
    public void banUser(int userId, Integer banId) throws ServiceException {
        if (!ValidatorUser.banValidator(userId,banId)) {
            return;
        }
        try {
            userDAO.banUser(userId, banId);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean editProfile(User user) throws ServiceException {
        if (!ValidatorUser.profileValidator(user)) {
            return false;
        }
        try {
            userDAO.editProfile(user);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return true;
    }
}
