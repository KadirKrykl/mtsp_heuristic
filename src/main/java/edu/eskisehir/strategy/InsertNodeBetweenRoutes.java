package edu.eskisehir.strategy;

import edu.eskisehir.solution.Depot;
import edu.eskisehir.solution.Route;
import edu.eskisehir.solution.Solution;

import java.util.List;

public class InsertNodeBetweenRoutes implements SolutionStrategy {

    @Override
    public void solve(Solution solution) {
        List<Depot> depots = solution.getDepots();
        if (depots.size() < 2 && depots.getFirst().getRoutes().size() < 2) {
            return;
        }
        Depot depot1 = solution.getRandomDepot(random, null);
        Depot depot2 = depot1.getRoutes().size() >= 2 ? solution.getRandomDepot(random, null) : solution.getRandomDepot(random, depot1.getDepot());

        Route route1 = depot1.getRandomRoute(random, null);
        Route route2 = depot1.getDepot().equals(depot2.getDepot()) ? depot2.getRandomRoute(random, route1) : depot2.getRandomRoute(random, null);
        if (route1.getNodes().size() < 4) {return;}
        int n1 = route1.getRandomNodeIdx(random, null);
        int n2 = route2.getNodes().size() < 3 ? 1 : route2.getRandomNodeIdx(random, null);

        Integer temp = route1.getNodes().remove(n1);
        route2.getNodes().add(n2+1, temp);
        int newDistance = solution.calculateSolution();
        if (solution.getDistance() < newDistance) {
            route2.getNodes().remove(n2+1);
            route1.getNodes().add(n1, temp);
        } else {
            solution.setDistance(newDistance);
            solution.getGain().setInsertNodeBetweenRoutes(solution.getGain().getInsertNodeBetweenRoutes()+1);
        }
    }
}
