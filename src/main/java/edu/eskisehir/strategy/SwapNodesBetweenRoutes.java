package edu.eskisehir.strategy;

import edu.eskisehir.solution.Depot;
import edu.eskisehir.solution.Route;
import edu.eskisehir.solution.Solution;

public class SwapNodesBetweenRoutes implements SolutionStrategy{
    @Override
    public void solve(Solution solution) {
        if (solution.getDepots().size() < 2 && solution.getDepots().getFirst().getRoutes().size() < 2) {
            return;
        }
        Depot depot1 = solution.getRandomDepot(random, null);
        Depot depot2 = depot1.getRoutes().size() >= 2 ? solution.getRandomDepot(random, null) : solution.getRandomDepot(random, depot1.getDepot());

        Route route1 = depot1.getRandomRoute(random, null);
        Route route2 = depot1.getDepot().equals(depot2.getDepot()) ? depot2.getRandomRoute(random, route1) : depot2.getRandomRoute(random, null);

        if (route1.getNodes().size() < 4) {return;}
        int n1 = route1.getRandomNodeIdx(random, null);
        int n2 = route2.getNodes().size() < 3 ? 1 : route2.getRandomNodeIdx(random, null);

        int temp = route1.getNodes().get(n1);
        route1.getNodes().set(n1, route2.getNodes().get(n2));
        route2.getNodes().set(n2, temp);

        int newDistance = solution.calculateSolution();
        if (solution.getDistance() < newDistance) {
            route2.getNodes().set(n2, route1.getNodes().get(n1));
            route1.getNodes().set(n1, temp);
        } else {
            solution.setDistance(newDistance);
            solution.getGain().setSwapNodesBetweenRoutes(solution.getGain().getSwapNodesBetweenRoutes()+1);
        }
    }
}
