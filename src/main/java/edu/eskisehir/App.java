package edu.eskisehir;

import com.lexicalscope.jewel.cli.CliFactory;
import com.lexicalscope.jewel.cli.Option;
import edu.eskisehir.solution.Solution;
import edu.eskisehir.strategy.*;

import java.util.*;

/**
 * Hello world!
 *
 */
public class App {
    private static final List<Class<? extends SolutionStrategy>> SOLUTION_CLASSES = Arrays.asList(
            InsertNodeBetweenRoutes.class,
            InsertNodeInRoute.class,
            SwapHubWithNodeInRoute.class,
            SwapNodesBetweenRoutes.class,
            SwapNodesInRoute.class
    );

    public static void main( String[] args ) {
        try {
            Options options = CliFactory.parseArguments(Options.class, args);
            int d = options.getDepotNumber();
            int s = options.getSalesmanNumber();
            MTSP mtsp = new MTSP(d, s);
            TreeMap<Integer, Solution> results = new TreeMap<>();
            for (int i = 0; i < 100000; i++) {
                Solution solution = mtsp.generateRandomSolution();
                results.put(solution.getDistance(), solution);
            }
            Solution solution =  results.firstEntry().getValue();
            solution.printLog(options.isVerbose(), false);
            System.out.print("\n\n");

            for (int i = 0; i < 5000000; i++) {
                solution.setStrategy(createRandomSolution());
                solution.executeStrategy();
            }

            solution.printLog(options.isVerbose(), true);
            solution.printFile();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public interface Options
    {
        @Option(shortName = "d", description = "Depot number")
        int getDepotNumber();

        @Option(shortName = "s", description = "Salesman number")
        int getSalesmanNumber();

        @Option(shortName = "v", description = "Visualize?")
        boolean isVerbose();
    }

    public static SolutionStrategy createRandomSolution() {
        Random random = new Random();
        int index = random.nextInt(SOLUTION_CLASSES.size());
        Class<? extends SolutionStrategy> solutionClass = SOLUTION_CLASSES.get(index);
        try {
            return solutionClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Or handle exception as needed
        }
    }
}
