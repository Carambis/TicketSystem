package by.tc.web.entity.user;


import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{
    private int id;
    private String login;
    private String password;
    private String eMail;
    private int status;
    private String nickname;
    private Date dateRegistered;
    private String pathToImage;
    private Integer ban;

    public User(int id, String login, String password, String eMail, int status, String nickname, Date dateRegistered, String pathToImage, Integer ban) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.eMail = eMail;
        this.status = status;
        this.nickname = nickname;
        this.dateRegistered = dateRegistered;
        this.pathToImage = pathToImage;
        this.ban = ban;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public String getPathToImage() {
        return pathToImage;
    }

    public void setPathToImage(String pathToImage) {
        this.pathToImage = pathToImage;
    }

    public Integer getBan() {
        return ban;
    }

    public void setBan(Integer ban) {
        this.ban = ban;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (status != user.status) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (eMail != null ? !eMail.equals(user.eMail) : user.eMail != null) return false;
        if (nickname != null ? !nickname.equals(user.nickname) : user.nickname != null) return false;
        if (dateRegistered != null ? !dateRegistered.equals(user.dateRegistered) : user.dateRegistered != null)
            return false;
        if (pathToImage != null ? !pathToImage.equals(user.pathToImage) : user.pathToImage != null) return false;
        return ban != null ? ban.equals(user.ban) : user.ban == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (eMail != null ? eMail.hashCode() : 0);
        result = 31 * result + status;
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (dateRegistered != null ? dateRegistered.hashCode() : 0);
        result = 31 * result + (pathToImage != null ? pathToImage.hashCode() : 0);
        result = 31 * result + (ban != null ? ban.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", eMail='" + eMail + '\'' +
                ", status=" + status +
                ", nickname='" + nickname + '\'' +
                ", dateRegistered=" + dateRegistered +
                ", pathToImage='" + pathToImage + '\'' +
                ", ban=" + ban +
                '}';
    }
}