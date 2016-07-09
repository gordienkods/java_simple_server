package service;

/**
 * Created by Димон on 09.07.2016.
 */
public interface Data {

    public void addNewUser(Integer userId, Integer levelId, Integer result);

    public String getTop20UsersByDecresingResultsOrderOnLevel(Integer level);

}
