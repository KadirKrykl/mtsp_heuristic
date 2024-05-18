package edu.eskisehir;

import edu.eskisehir.solution.Depot;
import edu.eskisehir.solution.Route;
import edu.eskisehir.solution.Solution;

import java.util.*;

public class MTSP {
    private int numDepots;
    private int salesmenPerDepot;
    private List<Integer> cities;
    private Random random;

    public MTSP(int numDepots, int salesmenPerDepot) {
        this.numDepots = numDepots;
        this.salesmenPerDepot = salesmenPerDepot;
        this.cities = new ArrayList<>();
        for (int i = 0; i < TurkishNetwork.distance.length; i++) {
            cities.add(i);
        }
        random = new Random();
    }

    public Solution generateRandomSolution() {
        Solution solution = new Solution();
        List<Integer> citiesTemp = new ArrayList<>(cities.stream().toList());
        int remainingSalesman = numDepots*salesmenPerDepot;
        int remainingVisitCity = citiesTemp.size() - numDepots;
        for (int i = 0; i < numDepots; i++) {
            Collections.shuffle(citiesTemp);
            Depot depot = new Depot(citiesTemp.removeFirst());

            for (int salesman = 0; salesman < salesmenPerDepot; salesman++) {
                List<Integer> route = new ArrayList<>();
                route.add(depot.getDepot()); // Start at depot

                //Calculate visiting city number
                if (remainingSalesman == 1) {
                    route.addAll(citiesTemp);
                } else {
                    int range = remainingVisitCity/remainingSalesman;
                    int max = range + 2;
                    int min = range -2;
                    route.addAll(citiesTemp.subList(0, random.nextInt((max - min + 1)) + min));
                }
                route.add(depot.getDepot()); // Return to depot
                depot.getRoutes().add(new Route(route));
                citiesTemp.removeAll(route); // Remove depot from cities
                remainingSalesman--;
                remainingVisitCity = citiesTemp.size();
            }
            solution.getDepots().add(depot);
        }
        solution.setDistance(solution.calculateSolution());
        return solution;
    }
}
