package edu.eskisehir.solution;

import edu.eskisehir.TurkishNetwork;

import java.util.List;
import java.util.Random;

public class Route {
    private List<Integer> nodes;

    public Route() {}

    public Route(List<Integer> nodes) {
        this.nodes = nodes;
    }

    public List<Integer> getNodes() {
        return nodes;
    }

    public void setNodes(List<Integer> nodes) {
        this.nodes = nodes;
    }

    public Integer calculate() {
        int totalDistance = 0;
        for (int i = 0; i < nodes.size() - 1; i++) {
            int from = nodes.get(i);
            int to = nodes.get(i + 1);
            totalDistance += TurkishNetwork.distance[from][to];
        }
        return totalDistance;
    }

    public Integer getRandomNodeIdx(Random random, Integer notThis) {
        int idx;
        do {
            try {
                idx = random.nextInt(nodes.size()-2)+1;
            } catch (IllegalArgumentException e) {
                System.out.println(nodes);
                throw e;
            }

        } while (notThis != null && idx == notThis);
        return idx;
    }

    public String printNodes(boolean v) {
        StringBuilder sb = new StringBuilder();
        String delimiter = ","; // Define the delimiter

        for (int i = 1; i < nodes.size()-1; i++) {
            sb.append(v ? TurkishNetwork.cities[nodes.get(i)] : nodes.get(i));
            if (i < nodes.size() - 2) {
                sb.append(delimiter);
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String delimiter = " "; // Define the delimiter

        for (int i = 1; i < nodes.size()-1; i++) {
            sb.append(nodes.get(i));
            if (i < nodes.size() - 2) {
                sb.append(delimiter);
            }
        }
        return sb.toString();
    }
}
