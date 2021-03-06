package final_project_part_1;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;

public class AStarSolver {
    public static Comparator<State> rewardComparator = new Comparator<State>(){
        @Override
        public int compare(State s1, State s2) {
            if (s2.getHeuristicReward() > s1.getHeuristicReward()) {
                return 1;
            }
            if (s2.getHeuristicReward() < s1.getHeuristicReward()) {
                return -1;
            }
            return 0;
        }
    };
    
    public static State aStarSearch(State initialState, HashMap<Integer, HashSet<Integer>> people) {
        // queue to keep track of states to visit
        Queue<State> frontier = new PriorityQueue<>(1, rewardComparator);
        
        // add initial state to the queue
        frontier.offer(initialState);
        
        int count = 0;
        while (!frontier.isEmpty()) {
            if (count == 1000) {
                System.out.println("WHYYYYY");
            }
            
            System.out.println();
            
            State current = frontier.poll();
            System.out.println("Started next iteration with depth " + current.getDepth());
            System.out.println("Looking at person " + current.person);
            
            // inspect the frontier
            System.out.println("The estimated reward for this state is " + current.getHeuristicReward());
            System.out.println("The total number of people exposed is " + current.getExposedToCard().size());
            
//            if (current.isGoal()) {
            if (current.getDepth() == 7) {
                System.out.println("Just found goal");
                for (int guy : people.keySet()) {
                    System.out.print(guy + ": ");
                    HashSet<Integer> frs = people.get(guy);
                    for (int fr : frs) {
                        System.out.print(fr + " ");
                    }
                    System.out.println();
                }
                return current;
            }
            
            // from each state, you can reach a deeper state by giving a card to someone who
            // a) hasn't already been given a card, AND
            // b) hasn't yet been exposed to a card
            /*
            for each person who, based on the current state, has not been given or exposed to the card:
                create a new state in which they get the card
                add that to the frontier
            */
            HashMap<Integer, Integer> givenCard = current.getGivenCard();
            HashMap<Integer, Double> exposedToCard = current.getExposedToCard();
            for (Integer person : people.keySet()) {
                if (!givenCard.containsKey(person) && !exposedToCard.containsKey(person)) {
//                    System.out.println("Found person: " + person);
                    HashMap<Integer, Integer> newGivenCard = new HashMap<>();
                    HashMap<Integer, Double> newExposedToCard = new HashMap<>();
                    newExposedToCard.putAll(exposedToCard);
                    newGivenCard.putAll(givenCard);
                    newGivenCard.put(person, current.getDepth()+1);
                    HashSet<Integer> friends = people.get(person);
                    double pAdoption = Final_Project_Part_1.calcPAdoption(givenCard.size(), exposedToCard.size());
//                    System.out.println("pAdoption for friends of " + person + " is " + pAdoption);
//                    System.out.println("friends size" + friends.size());
                    for (int friend : friends) {
                        if (!newExposedToCard.containsKey(friend)) {
                            // will need to calculate actual cost later, just using 0.0 for now because it doesn't matter
                            newExposedToCard.put(friend, pAdoption);
                        }
                    }
                    double reward = Final_Project_Part_1.calcDFSReward(newExposedToCard);
                    double hReward = Final_Project_Part_1.calcAStarReward(newExposedToCard, people.size()-newExposedToCard.size(), current.getDepth()+1);
//                    System.out.println("dfsReward = " + reward);
//                    System.out.println("hReward = " + hReward);
//                    System.out.println("newExposedToCard.size() = " + newExposedToCard.size());
                    State newState = new State(newGivenCard, newExposedToCard, reward, hReward, current.getDepth()+1);
//                    newState.parent = current;
                    newState.person = person;
//                    System.out.println("Added state to frontier: ");
//                    for (int guy : newState.getGivenCard()) {
//                        System.out.println(guy);
//                    }
//                    System.out.println("Reward = " + newState.getReward());
//                    System.out.println("Priority Queue size = " + frontier.size());
                    frontier.offer(newState);
                }
            }
            count++;
        }
        System.out.println("Failed to find goal");
        return null;
    }
}
