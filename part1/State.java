package final_project_part_1;

import java.util.HashMap;
import java.util.HashSet;

public class State {
    private HashMap<Integer, Integer> givenCard;
    private HashMap<Integer, Double> exposedToCard;
    private int depth;
    private double reward;
    private double heuristicReward;
//    public State parent;
    public int person;
    
    public State(HashMap<Integer, Integer> givenCard, HashMap<Integer, Double> exposedToCard, double reward, double heuristicReward, int depth) {
        this.givenCard = givenCard;
        this.exposedToCard = exposedToCard;
        this.reward = reward;
        this.heuristicReward = heuristicReward;
        this.depth = depth;
//        parent = null;
        person = -1;
    }
    
//    public void addPerson(int person) {
//        givenCard.put(person);
//    }
    
    public double getReward() {
        return reward;
    }
    
    public double getHeuristicReward() {
        return heuristicReward;
    }
    
    public boolean isGoal() {
        return depth == 10;
    }
    
    public HashMap<Integer, Integer> getGivenCard() {
        return givenCard;
    }
    
    public HashMap<Integer, Double> getExposedToCard() {
        return exposedToCard;
    }
    
    public int getDepth() {
        return depth;
    }
}
