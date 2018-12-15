package by.tc.web.entity.film;

import java.io.Serializable;
import java.util.Date;

public class Film implements Serializable {
    private int id;
    private String name;
    private String ganre;
    private String director;
    private double avgRating;
    private Date datePrimer;
    private String nameImage;
    private String namePoster;
    private String duration;
    private String description;
    private String lang;

    public Film(int id, String name, String ganre, String director, double avgRating, Date datePrimer, String nameImage,
                String namePoster, String duration, String description, String lang) {
        this.id = id;
        this.name = name;
        this.ganre = ganre;
        this.director = director;
        this.avgRating = avgRating;
        this.datePrimer = datePrimer;
        this.nameImage = nameImage;
        this.namePoster = namePoster;
        this.duration = duration;
        this.description = description;
        this.lang = lang;
    }

    public Film() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGanre() {
        return ganre;
    }

    public void setGanre(String ganre) {
        this.ganre = ganre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }

    public Date getDatePrimer() {
        return datePrimer;
    }

    public void setDatePrimer(Date datePrimer) {
        this.datePrimer = datePrimer;
    }

    public String getNameImage() {
        return nameImage;
    }

    public void setNameImage(String nameImage) {
        this.nameImage = nameImage;
    }

    public String getNamePoster() {
        return namePoster;
    }

    public void setNamePoster(String namePoster) {
        this.namePoster = namePoster;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Film film = (Film) o;

        if (id != film.id) return false;
        if (Double.compare(film.avgRating, avgRating) != 0) return false;
        if (name != null ? !name.equals(film.name) : film.name != null) return false;
        if (ganre != null ? !ganre.equals(film.ganre) : film.ganre != null) return false;
        if (director != null ? !director.equals(film.director) : film.director != null) return false;
        if (datePrimer != null ? !datePrimer.equals(film.datePrimer) : film.datePrimer != null) return false;
        if (nameImage != null ? !nameImage.equals(film.nameImage) : film.nameImage != null) return false;
        if (namePoster != null ? !namePoster.equals(film.namePoster) : film.namePoster != null) return false;
        if (duration != null ? !duration.equals(film.duration) : film.duration != null) return false;
        if (description != null ? !description.equals(film.description) : film.description != null) return false;
        return lang != null ? lang.equals(film.lang) : film.lang == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (ganre != null ? ganre.hashCode() : 0);
        result = 31 * result + (director != null ? director.hashCode() : 0);
        temp = Double.doubleToLongBits(avgRating);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (datePrimer != null ? datePrimer.hashCode() : 0);
        result = 31 * result + (nameImage != null ? nameImage.hashCode() : 0);
        result = 31 * result + (namePoster != null ? namePoster.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (lang != null ? lang.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ganre='" + ganre + '\'' +
                ", director='" + director + '\'' +
                ", avgRating=" + avgRating +
                ", datePrimer=" + datePrimer +
                ", nameImage='" + nameImage + '\'' +
                ", namePoster='" + namePoster + '\'' +
                ", duration='" + duration + '\'' +
                ", description='" + description + '\'' +
                ", lang='" + lang + '\'' +
                '}';
    }
}
