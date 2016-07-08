package entity;

import com.google.common.collect.ComparisonChain;
import org.omg.CORBA.Object;

public class LevelAndScore implements Comparable<LevelAndScore> {

    private Integer level;
    private Integer score;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LevelAndScore that = (LevelAndScore) o;

        if (!getLevel().equals(that.getLevel())) return false;
        return getScore().equals(that.getScore());
    }

    @Override
    public int hashCode() {
        int result = getLevel().hashCode();
        result = 31 * result + getScore().hashCode();
        return result;
    }


    public int compareTo(LevelAndScore that){
       return ComparisonChain.start().compare(score, that.getScore()).result();
    }

}
