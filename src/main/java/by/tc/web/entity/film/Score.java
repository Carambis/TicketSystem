package by.tc.web.entity.film;

import java.io.Serializable;

public class Score implements Serializable {
    private int countCritic;
    private int countUser;
    private double avgCritic;
    private double avgUser;

    public Score(int countCritic, int countUser, double avgCritic, double avgUser) {
        this.countCritic = countCritic;
        this.countUser = countUser;
        this.avgCritic = avgCritic;
        this.avgUser = avgUser;
    }

    public Score() {
    }

    public int getCountCritic() {
        return countCritic;
    }

    public void setCountCritic(int countCritic) {
        this.countCritic = countCritic;
    }

    public int getCountUser() {
        return countUser;
    }

    public void setCountUser(int countUser) {
        this.countUser = countUser;
    }

    public double getAvgCritic() {
        return avgCritic;
    }

    public void setAvgCritic(double avgCritic) {
        this.avgCritic = avgCritic;
    }

    public double getAvgUser() {
        return avgUser;
    }

    public void setAvgUser(double avgUser) {
        this.avgUser = avgUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Score score = (Score) o;

        if (countCritic != score.countCritic) return false;
        if (countUser != score.countUser) return false;
        if (Double.compare(score.avgCritic, avgCritic) != 0) return false;
        return Double.compare(score.avgUser, avgUser) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = countCritic;
        result = 31 * result + countUser;
        temp = Double.doubleToLongBits(avgCritic);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(avgUser);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Score{" +
                "countCritic=" + countCritic +
                ", countUser=" + countUser +
                ", avgCritic=" + avgCritic +
                ", avgUser=" + avgUser +
                '}';
    }
}
