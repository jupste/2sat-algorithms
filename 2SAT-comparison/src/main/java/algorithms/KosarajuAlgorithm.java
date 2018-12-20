/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import datastructures.CustomArrayList;
import datastructures.CustomHashmap;
import datastructures.CustomStack;

/**
 *
 * @author jussiste
 */
public class KosarajuAlgorithm {

    private boolean[] visited;
    private int[] component;
    private int[] finish;
    private int[] order;
    private CustomArrayList<Integer>[] graph;
    private CustomArrayList<Integer>[] reversegraph;
    private CustomHashmap<Integer, Boolean> truthAssignment;
    private int parent, numVariables, counter;
    private CustomStack<Integer> stack;

    /**
     *
     * @param graph
     * @param numVariables
     */
    public KosarajuAlgorithm(CustomArrayList<Integer>[] graph, int numVariables) {
        this.graph = graph;
        int size = graph.length;
        this.truthAssignment = new CustomHashmap<>();
        this.stack = new CustomStack<>();
        this.visited = new boolean[size];
        this.component = new int[size];
        this.finish = new int[size];
        this.reversegraph = new CustomArrayList[size];
        this.parent = 0;
        this.counter = 1;
        this.numVariables = numVariables;
        for (int i = 0; i < size; i++) {
            reversegraph[i] = new CustomArrayList<>();
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < graph[i].size(); j++) {
                reversegraph[graph[i].get(j)].add(i);
            }
        }
    }

    /**
     * Depth first search used as a utility method in Kosaraju's
     * algorithm
     *
     * @param i current node
     */
    public void dfs(int i) {
        visited[i] = true;
        for (int iter : graph[i]) {
            if (!visited[iter]) {
                dfs(iter);
            }
        }
        stack.push(i);
    }

    /**
     * Determines if two elements are strongly connected.
     * @param u a node
     * @param v a node
     * @return true if strongly connected, false otherwise 
     */
    public boolean stronglyConnected(int u, int v) {
        return component[u] == component[v];
    }

    /**
     * Reverse depth first search used as a utility method in Kosaraju's
     * algorithm
     *
     * @param i current node
     */
    public void dfs_reverse(int i) {
        visited[i] = true;
        for (int iter : reversegraph[i]) {
            if (!visited[iter]) {
                dfs_reverse(iter);
            }
        }
        component[i] = counter;
    }

    /**
     * Method that determines if a given CNF is satisfiable
     * @return true if satisfiable, false otherwise
     */
    public boolean checkSatisfiability() {
        for (int i = 1; i <= 2 * numVariables; i++) {
            if (!visited[i]) {
                dfs(i);
            }
        }
        this.visited = new boolean[this.graph.length];
        while (!stack.isEmpty()) {
            int n = stack.pop();
            if (!visited[n]) {
                dfs_reverse(n);
                counter++;
            }
        }
        for (int i = 1; i <= numVariables; i++) {
            if (stronglyConnected(i, i + numVariables)) {
                return false;
            }
        }
        return true;
    }

}
