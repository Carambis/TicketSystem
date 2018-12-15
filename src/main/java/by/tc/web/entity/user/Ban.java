package by.tc.web.entity.user;

import java.io.Serializable;

public class Ban implements Serializable{
    private int id;
    private String lang;
    private String name;
    private String description;

    public Ban(int id, String lang, String name, String description) {
        this.id = id;
        this.lang = lang;
        this.name = name;
        this.description = description;
    }

    public Ban() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ban ban = (Ban) o;

        if (id != ban.id) return false;
        if (lang != null ? !lang.equals(ban.lang) : ban.lang != null) return false;
        if (name != null ? !name.equals(ban.name) : ban.name != null) return false;
        return description != null ? description.equals(ban.description) : ban.description == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (lang != null ? lang.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Ban{" +
                "id=" + id +
                ", lang='" + lang + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
