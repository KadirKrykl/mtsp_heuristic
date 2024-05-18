package edu.eskisehir.solution;

import java.util.*;

public class Depot {
    private Integer depot;
    private List<Route> routes;

    public Depot() {}

    public Depot(Integer depot) {
        this.depot = depot;
        routes = new ArrayList<>();
    }

    public Integer getDepot() {
        return depot;
    }

    public void setDepot(Integer depot) {
        for (int i = 0; i < routes.size(); i++) {
            int idx = routes.get(i).getNodes().indexOf(depot);
            if (idx >= 0) {
                routes.get(i).getNodes().set(idx, this.depot);
            }
            routes.get(i).getNodes().set(0, depot);
            routes.get(i).getNodes().set(routes.get(i).getNodes().size()-1, depot);
        }
        this.depot = depot;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public Route getRandomRoute(Random random, Route notThis) {
        int idx;
        Route route;
        do {
            idx = random.nextInt(routes.size());
            route = routes.get(idx);
        } while (route.equals(notThis));
        return route;
    }

    public List<Integer> getAllNodes() {
        Set<Integer> allNodes = new HashSet<>();
        for (Route route : routes) {
            allNodes.addAll(route.getNodes());
        }
        allNodes.remove(depot);
        return allNodes.stream().toList();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("    {\n");
        sb.append("      \"depot\": \"").append(depot).append("\",\n");
        sb.append("      \"routes\": [\n");
        for (int j = 0; j < routes.size(); j++) {
            sb.append("        \"").append(routes.get(j).toString()).append("\"");
            if (j < routes.size() - 1) {
                sb.append(",");
            }
            sb.append("\n");
        }
        sb.append("      ]\n");
        sb.append("    }");
        return sb.toString();
    }
}
