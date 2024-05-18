package edu.eskisehir.strategy;

import edu.eskisehir.solution.Solution;

import java.util.Random;

public interface SolutionStrategy {
    Random random = new Random();

    void solve(Solution solution);
}
