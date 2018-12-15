package by.tc.web.entity.film;

import java.io.Serializable;

public class CommentForDelete implements Serializable{
    private int userId;
    private int filmId;
    private String nameFilm;
    private String comment;

    public CommentForDelete(int userId, int filmId, String nameFilm, String comment) {
        this.userId = userId;
        this.filmId = filmId;
        this.nameFilm = nameFilm;
        this.comment = comment;
    }

    public CommentForDelete() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public String getNameFilm() {
        return nameFilm;
    }

    public void setNameFilm(String nameFilm) {
        this.nameFilm = nameFilm;
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

        CommentForDelete that = (CommentForDelete) o;

        if (userId != that.userId) return false;
        if (filmId != that.filmId) return false;
        if (nameFilm != null ? !nameFilm.equals(that.nameFilm) : that.nameFilm != null) return false;
        return comment != null ? comment.equals(that.comment) : that.comment == null;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + filmId;
        result = 31 * result + (nameFilm != null ? nameFilm.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CommentForDelete{" +
                "userId=" + userId +
                ", filmId=" + filmId +
                ", nameFilm='" + nameFilm + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
