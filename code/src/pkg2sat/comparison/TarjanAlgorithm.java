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

    public ArrayList<Integer>[] getGraph() {
        return graph;
    }
    
    public ArrayList<Integer>[] getReversegraph() {
        return reversegraph;
    }
    
    public void dfs(int i){
        visited[i]=true;
        leader[i]=parent;
        for(int iter: graph[i]){
            if(!visited[iter]){
                dfs(iter);
            }
        }
    }
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
    public boolean stronglyConnected(int u, int v){
        return leader[u]==leader[v];
    }

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
