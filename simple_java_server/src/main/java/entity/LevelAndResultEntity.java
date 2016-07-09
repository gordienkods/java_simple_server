package entity;


public class LevelAndResultEntity implements Comparable {

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

        LevelAndResultEntity that = (LevelAndResultEntity) o;

        if (!getLevel().equals(that.getLevel())) return false;
        return getScore().equals(that.getScore());
    }

    @Override
    public int hashCode() {
        int result = getLevel().hashCode();
        result = 31 * result + getScore().hashCode();
        return result;
    }

    @Override
    public int compareTo(Object obj){
        LevelAndResultEntity that = (LevelAndResultEntity) obj;
        return this.getScore().compareTo(that.getScore());
    }




}
