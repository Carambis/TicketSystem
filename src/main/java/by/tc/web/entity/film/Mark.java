package by.tc.web.entity.film;

import java.io.Serializable;

public class Mark implements Serializable {
    private int filmId;
    private int userId;
    private int mark;
    private String comment;

    public Mark(int filmId, int userId, int mark, String comment) {
        this.filmId = filmId;
        this.userId = userId;
        this.mark = mark;
        this.comment = comment;
    }

    public Mark() {
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mark mark1 = (Mark) o;

        if (filmId != mark1.filmId) return false;
        if (userId != mark1.userId) return false;
        if (mark != mark1.mark) return false;
        return comment != null ? comment.equals(mark1.comment) : mark1.comment == null;
    }

    @Override
    public int hashCode() {
        int result = filmId;
        result = 31 * result + userId;
        result = 31 * result + mark;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Mark{" +
                "filmId=" + filmId +
                ", userId=" + userId +
                ", mark=" + mark +
                ", comment='" + comment + '\'' +
                '}';
    }
}
