package edu.eskisehir.solution;

import com.fasterxml.jackson.core.JsonProcessingException;
import edu.eskisehir.strategy.SolutionStrategy;
import edu.eskisehir.TurkishNetwork;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Solution {
    private SolutionStrategy strategy;
    private Integer distance;
    private List<Depot> depots;
    private ResultGain gain;

    public Solution() {
        distance = 0;
        depots = new ArrayList<>();
        gain = new ResultGain();
    }

    public SolutionStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(SolutionStrategy strategy) {
        this.strategy = strategy;
    }

    public void executeStrategy() {
        strategy.solve(this);
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public List<Depot> getDepots() {
        return depots;
    }

    public ResultGain getGain() {
        return gain;
    }

    public Depot getRandomDepot(Random random, Integer notThis) {
        int idx;
        Depot depot;
        do {
            idx = new Random().nextInt(depots.size());
            depot = depots.get(idx);
        } while (Objects.equals(depot.getDepot(), notThis));
        return depot;
    }

    public Integer calculateSolution() {
        return depots.stream()
                .flatMap(depot -> depot.getRoutes().stream())
                .mapToInt(Route::calculate)
                .sum();
    }

    public void printLog(boolean v, boolean operations) throws JsonProcessingException {
        for (int i = 0; i < depots.size(); i++) {
            Depot depot = depots.get(i);
            System.out.printf("Depot%s:%s \n", i+1, v ? TurkishNetwork.cities[depot.getDepot()] : depot.getDepot());
            for (int j = 0; j < depot.getRoutes().size(); j++) {
                System.out.printf("\tRoute%s:%s \n", j+1, depot.getRoutes().get(j).printNodes(v));
            }
        }
        System.out.printf("**Total cost is %s\n\n", distance);
        if (operations) {
            System.out.printf(gain.print());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n  \"solution\": [\n");
        for (int i = 0; i < depots.size(); i++) {
            sb.append(depots.get(i).toString());
            if (i < depots.size() - 1) {
                sb.append(",");
            }
            sb.append("\n");
        }
        sb.append("  ]\n}");
        return sb.toString();
    }

    public void printFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(String.format("solution_d%ss%s.json",depots.size(),depots.getFirst().getRoutes().size())));
        writer.write(this.toString());
        writer.close();
    }
}


/*
public Depot getRandomDepot(Random random, Integer notThis)
public Integer calculateSolution()
public void printLog(boolean v, boolean operations)
public String toString()
public void printFile() throws IOException
 */