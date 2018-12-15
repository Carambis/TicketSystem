package by.tc.web.entity.film;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable{
    private String image;
    private String nickname;
    private int mark;
    private String comment;
    private Date date;

    public Comment(String image, String nickname, int mark, String comment, Date date) {
        this.image = image;
        this.nickname = nickname;
        this.mark = mark;
        this.comment = comment;
        this.date = date;
    }

    public Comment() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment1 = (Comment) o;

        if (mark != comment1.mark) return false;
        if (image != null ? !image.equals(comment1.image) : comment1.image != null) return false;
        if (nickname != null ? !nickname.equals(comment1.nickname) : comment1.nickname != null) return false;
        if (comment != null ? !comment.equals(comment1.comment) : comment1.comment != null) return false;
        return date != null ? date.equals(comment1.date) : comment1.date == null;
    }

    @Override
    public int hashCode() {
        int result = image != null ? image.hashCode() : 0;
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + mark;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "image='" + image + '\'' +
                ", nickname='" + nickname + '\'' +
                ", mark=" + mark +
                ", comment='" + comment + '\'' +
                ", date=" + date +
                '}';
    }
}
