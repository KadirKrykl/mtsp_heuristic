package edu.eskisehir.strategy;

import edu.eskisehir.solution.Depot;
import edu.eskisehir.solution.Solution;

import java.util.List;

public class SwapHubWithNodeInRoute implements SolutionStrategy {
    @Override
    public void solve(Solution solution) {
        Depot depot = solution.getRandomDepot(random, null);
        List<Integer> allNodes = depot.getAllNodes();
        if (!allNodes.isEmpty()) {
            int choosenNode = allNodes.get(random.nextInt(allNodes.size()));
            int temp = depot.getDepot();
            depot.setDepot(choosenNode);
            int newDistance = solution.calculateSolution();
            if (solution.getDistance() < newDistance) {
                depot.setDepot(temp);
            } else {
                solution.setDistance(newDistance);
                solution.getGain().setSwapHubWithNodeInRoute(solution.getGain().getSwapHubWithNodeInRoute()+1);
            }
        }
    }
}
