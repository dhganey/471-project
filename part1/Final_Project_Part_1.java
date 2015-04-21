package final_project_part_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class Final_Project_Part_1 {

    public static void main(String[] args) {
        SearchType sType = SearchType.DFS;
        
        HashMap<Integer, HashSet<Integer>> people = new HashMap<>();
        String[] pair = new String[2];
        int first, second;
        
        // read in the file and initialize the HashMap of people
        BufferedReader br = null;
        try {
            String currentLine;
            br = new BufferedReader(new FileReader("data_files/348.edges.txt"));
            while ((currentLine = br.readLine()) != null) {
                pair = currentLine.split(" ");
                first = Integer.parseInt(pair[0]);
                second = Integer.parseInt(pair[1]);
                if (!people.containsKey(first)) {
                    HashSet<Integer> friends = new HashSet<>();
                    people.put(first, friends);
                }
                people.get(first).add(second);
                if (!people.containsKey(second)) {
                    HashSet<Integer> friends = new HashSet<>();
                    people.put(second, friends);
                }
                people.get(second).add(first);
            }
            br.close();
            
//            for (Integer person : people.keySet()) {
//                System.out.println(person + ": " + people.get(person).toString());
//            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (br != null)
                    br.close();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        
        // create the initial state
        State initialState = new State(new HashMap<Integer, Integer>(), new HashMap<Integer, Double>(), 0, (sType == SearchType.DFS) ? 0 : people.size(), 0);
        State finalState = null;
        
        System.out.println("There are " + people.size() + " people total");
        
        if (sType == SearchType.DFS)
            finalState = DFSSolver.dfs(initialState, people);
        else if (sType == SearchType.AStar)
            finalState = AStarSolver.aStarSearch(initialState, people);
        
        
        System.out.println("Result:");
        for (int person : finalState.getGivenCard().keySet()) {
            System.out.println(person + " was given card " + finalState.getGivenCard().get(person));
        }
        System.out.println("Total reward = " + finalState.getReward());
    }
    
    public static double calcDFSReward(HashMap<Integer, Double> exposedToCard) {
        double sum = 0.0;
        for (double pAdoption : exposedToCard.values()) {
            sum += pAdoption;
        }
        return sum;
    }
    
    public static double calcAStarReward(HashMap<Integer, Double> exposedToCard, int notExposed, int depth) {
//        System.out.println("In function call " + exposedToCard.size());
        if (depth == 10) {
            notExposed = 0;
        }
        return calcDFSReward(exposedToCard) + notExposed;
    }
    
    public static double calcPAdoption(int numGivenCard, int numExposedToCard) {
        if (numExposedToCard == 0 && numGivenCard == 0)
            return 0.1;
        return Math.max(0.1, (1.0-1.0/(numExposedToCard + numGivenCard)));
    }
}
