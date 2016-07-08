package entity;

import org.json.JSONObject;

public class UserEntity implements Comparable {

    private String userId;
    private Integer levelId;
    private Integer result;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        UserEntity that = (UserEntity) obj;

        if (levelId != that.levelId) return false;
        if (result != that.result) return false;
        return userId.equals(that.userId);

    }

    public int compareTo(Object obj){
        UserEntity that = (UserEntity) obj;
        return result.compareTo(that.getResult());
    }

    @Override
    public int hashCode() {
        int result1 = userId.hashCode();
        result1 = 31 * result1 + levelId;
        result1 = 31 * result1 + result;
        return result1;
    }

    @Override
    public String toString() {
        return new JSONObject(this).toString();
    }
}
