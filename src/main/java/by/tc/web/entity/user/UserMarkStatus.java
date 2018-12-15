package by.tc.web.entity.user;

import java.io.Serializable;
import java.util.Objects;

public class UserMarkStatus implements Serializable {
    private int userId;
    private int status;
    private int userMark;

    public UserMarkStatus(int userId, int status, int userMark) {
        this.userId = userId;
        this.status = status;
        this.userMark = userMark;
    }

    public UserMarkStatus() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUserMark() {
        return userMark;
    }

    public void setUserMark(int userMark) {
        this.userMark = userMark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserMarkStatus that = (UserMarkStatus) o;
        return userId == that.userId &&
                status == that.status &&
                userMark == that.userMark;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, status, userMark);
    }

    @Override
    public String toString() {
        return "UserMarkStatus{" +
                "userId=" + userId +
                ", status=" + status +
                ", userMark=" + userMark +
                '}';
    }
}
