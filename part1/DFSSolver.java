package final_project_part_1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class DFSSolver {
    public static State dfs(State initialState, HashMap<Integer, HashSet<Integer>> people) {
        // stack to keep track of states to visit
        Stack<State> frontier = new Stack<>();
        
        // add initial state to the stack
        frontier.push(initialState);
        
        while (!frontier.isEmpty()) {           
            State current = frontier.pop();
            System.out.println("Started next iteration with depth " + current.getDepth());
            System.out.println("Looking at person " + current.person);
            if (current.isGoal()) {
//                System.out.println("Just found goal");
//                for (int guy : people.keySet()) {
//                    System.out.print(guy + ": ");
//                    HashSet<Integer> frs = people.get(guy);
//                    for (int fr : frs) {
//                        System.out.print(fr + " ");
//                    }
//                    System.out.println();
//                }
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
            HashSet<Integer> givenCard = current.getGivenCard();
            HashMap<Integer, Double> exposedToCard = current.getExposedToCard();
            for (Integer person : people.keySet()) {
                if (!givenCard.contains(person) && !exposedToCard.containsKey(person)) {
//                    System.out.println("Found person: " + person);
                    HashSet<Integer> newGivenCard = new HashSet<>();
                    newGivenCard = (HashSet)givenCard.clone();
                    HashMap<Integer, Double> newExposedToCard = new HashMap<>();
                    newExposedToCard.putAll(exposedToCard);
                    newGivenCard.add(person);
                    HashSet<Integer> friends = people.get(person);
                    double pAdoption = Final_Project_Part_1.calcPAdoption(givenCard.size(), exposedToCard.size());
                    for (int friend : friends) {
                        if (!newExposedToCard.containsKey(friend)) {
                            // will need to calculate actual cost later, just using 0.0 for now because it doesn't matter
                            newExposedToCard.put(friend, pAdoption);
                        }
                    }
                    double reward = Final_Project_Part_1.calcDFSReward(newExposedToCard);
                    State newState = new State(newGivenCard, newExposedToCard, reward, 0, current.getDepth()+1);
                    newState.parent = current;
//                    System.out.println("Added state to frontier: ");
//                    for (int guy : newState.getGivenCard()) {
//                        System.out.println(guy);
//                    }
//                    System.out.println("Reward = " + newState.getReward());
                    newState.person = person;
                    frontier.push(newState);
                }
            }
            
        }
        System.out.println("Failed to find goal");
        return null;
    }
}
