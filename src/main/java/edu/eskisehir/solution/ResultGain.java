package edu.eskisehir.solution;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResultGain {
    private int swapHubWithNodeInRoute;
    private int insertNodeBetweenRoutes;
    private int swapNodesInRoute;
    private int swapNodesBetweenRoutes;
    private int insertNodeInRoute;

    public ResultGain() {
        swapHubWithNodeInRoute = 0;
        insertNodeBetweenRoutes = 0;
        swapNodesInRoute = 0;
        swapNodesBetweenRoutes = 0;
        insertNodeInRoute = 0;
    }

    public int getSwapHubWithNodeInRoute() {
        return swapHubWithNodeInRoute;
    }

    public void setSwapHubWithNodeInRoute(int swapHubWithNodeInRoute) {
        this.swapHubWithNodeInRoute = swapHubWithNodeInRoute;
    }

    public int getInsertNodeBetweenRoutes() {
        return insertNodeBetweenRoutes;
    }

    public void setInsertNodeBetweenRoutes(int insertNodeBetweenRoutes) {
        this.insertNodeBetweenRoutes = insertNodeBetweenRoutes;
    }

    public int getSwapNodesInRoute() {
        return swapNodesInRoute;
    }

    public void setSwapNodesInRoute(int swapNodesInRoute) {
        this.swapNodesInRoute = swapNodesInRoute;
    }

    public int getSwapNodesBetweenRoutes() {
        return swapNodesBetweenRoutes;
    }

    public void setSwapNodesBetweenRoutes(int swapNodesBetweenRoutes) {
        this.swapNodesBetweenRoutes = swapNodesBetweenRoutes;
    }

    public int getInsertNodeInRoute() {
        return insertNodeInRoute;
    }

    public void setInsertNodeInRoute(int insertNodeInRoute) {
        this.insertNodeInRoute = insertNodeInRoute;
    }

    public String print() throws JsonProcessingException {
        return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
    }
}
