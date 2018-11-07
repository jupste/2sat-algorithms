/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2sat.comparison;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author jussiste
 */
public class TarjanAlgorithm {
    private boolean[] visited;
    private int[] leader;
    private int[] finish;
    private int[] order;
    private ArrayList<Integer> [] graph;
    private ArrayList<Integer> [] reversegraph;
    private HashMap<Integer, Boolean> truthAssignment;
    private int t, parent, numVariables;

    /**
     * Constructor for the object that handels Tarjan's algorithm. It receives the graph and the number of variables in the graph. 
     * @param graph the graph generated by GraphUtils class. This constructor also creates a transpose of this graph.
     * @param numVariables number of variables in the graph. Note that a proposition symbol and it's negation are considered 
     * as the same variable in this case. 
     */
    public TarjanAlgorithm(ArrayList<Integer>[] graph, int numVariables) {
        int size=graph.length;
        this.graph = graph;
        this.visited=new boolean[size];
        this.leader=new int[size];
        this.finish=new int[size];
        this.order=new int[size];
        this.truthAssignment=new HashMap<>();
        this.reversegraph= new ArrayList [size];
        this.parent=0;
        this.t=0;
        this.numVariables=numVariables;
        for(int i=0; i<size; i++){
            reversegraph[i]=new ArrayList<>();
        }
        for(int i=0; i<size; i++){
            for(int j=0; j<graph[i].size(); j++){
                reversegraph[graph[i].get(j)].add(i);
            }
        }
    }

    /**
     * Not needed in final program
     * @return
     */
    public ArrayList<Integer>[] getGraph() {
        return graph;
    }
    
    /**
     * Not needed in final program
     * @return
     */
    public ArrayList<Integer>[] getReversegraph() {
        return reversegraph;
    }
    
    /**
     * Depth first search used as a utility method in Tarjan's algorithm
     * @param i current node
     */
    public void dfs(int i){
        visited[i]=true;
        leader[i]=parent;
        for(int iter: graph[i]){
            if(!visited[iter]){
                dfs(iter);
            }
        }
    }

    /**
     * Reverse depth first search used as a utility method in Tarjan's algorithm
     * @param i current node
     */
    public void dfs_reverse(int i){
        visited[i]=true;
        for(int iter: reversegraph[i]){
            if(!visited[iter]){
                dfs_reverse(iter);
            }
        }
        t++;
        finish[i]=t;
    }

    /**
     * Check if the two nodes are strongly connected
     * @param u a node in graph
     * @param v an other node in graph
     * @return true if nodes are strongly connected, false otherwise 
     */
    public boolean stronglyConnected(int u, int v){
        return leader[u]==leader[v];
    }

    /**
     * Checks if the given CNF-sentence if satisfiable. If the proposition symbol and it's negation are found in the same strongly connected components list then the given sentence is unsatisfiable. 
     * If no such SCC are found then the sentence is satisfiable. If the sentence is satisfiable then the attribute truthAssignment can be used to recover the valuations that satisfy the sentence.
     * @return true or false
     */
    public boolean checkSatisfiability(){
        for(int i=2*numVariables; i>0; i--){
            if(!this.visited[i]){
                dfs_reverse(i);
            }
            this.order[this.finish[i]]=i;
        }
        this.visited=new boolean[this.graph.length];
        
        for(int i=2*numVariables; i>0; i--){
            if(!visited[order[i]]){
                parent= order[i];
                dfs(order[i]);
            }
        }
        for(int check=2*numVariables; check>0; check--){
            int u=order[check];
            if(u > numVariables){
                if(stronglyConnected(u, u-numVariables)){
                    return false;
                }
                if(!truthAssignment.containsKey(leader[u])){
                    truthAssignment.put(u,true);
                    truthAssignment.put(u-numVariables, false);
                }
            }else{
                if(stronglyConnected(u, numVariables+u)){
                    return false;
                }
                if(!truthAssignment.containsKey(leader[u])){
                    truthAssignment.put(u,true);
                    truthAssignment.put(u+numVariables, false);
                }
            }
        }
        return true;
    }
    
}
