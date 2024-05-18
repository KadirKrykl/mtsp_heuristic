package edu.eskisehir.strategy;

import edu.eskisehir.solution.Depot;
import edu.eskisehir.solution.Route;
import edu.eskisehir.solution.Solution;

public class InsertNodeInRoute implements SolutionStrategy{

    @Override
    public void solve(Solution solution) {
        Depot depot = solution.getRandomDepot(random, null);
        Route route = depot.getRandomRoute(random, null);
        if (route.getNodes().size() > 4){
            int idx = route.getRandomNodeIdx(random, null);
            Integer temp = route.getNodes().remove(idx);
            int idx2 = route.getRandomNodeIdx(random, null);
            route.getNodes().add(idx2+1, temp);
            int newDistance = solution.calculateSolution();
            if (solution.getDistance() < newDistance) {
                route.getNodes().remove(idx2+1);
                route.getNodes().add(idx, temp);
            } else {
                solution.setDistance(newDistance);
                solution.getGain().setInsertNodeInRoute(solution.getGain().getInsertNodeInRoute()+1);
            }
        }
    }
}
