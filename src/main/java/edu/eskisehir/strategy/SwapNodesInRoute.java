package edu.eskisehir.strategy;

import edu.eskisehir.solution.Route;
import edu.eskisehir.solution.Solution;

import java.util.Collections;

public class SwapNodesInRoute implements SolutionStrategy {
    @Override
    public void solve(Solution solution) {
        Route route = solution.getRandomDepot(random, null).getRandomRoute(random, null);
        if (route.getNodes().size() > 4) {
            int n1 = route.getRandomNodeIdx(random, null);
            int n2 = route.getRandomNodeIdx(random, n1);
            if (n1 != n2){
                Collections.swap(route.getNodes(), n1, n2);
                int newDistance = solution.calculateSolution();
                if (solution.getDistance() < newDistance) {
                    Collections.swap(route.getNodes(), n2, n1);
                } else {
                    solution.setDistance(newDistance);
                    solution.getGain().setSwapNodesInRoute(solution.getGain().getSwapNodesInRoute()+1);
                }
            }
        }
    }
}
