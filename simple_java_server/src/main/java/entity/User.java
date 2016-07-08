package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {

    private Integer userId;
    private Integer scoresOnLevel;
    private List<LevelAndScore> levelsAndScores = new ArrayList<LevelAndScore>();

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void addLevelAndScore(int level, int score){
        LevelAndScore levelAndScore = new LevelAndScore();
        levelAndScore.setLevel(level);
        levelAndScore.setScore(score);
        levelsAndScores.add(levelAndScore);

    }





}
