/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2sat.comparison.util;

import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author jussiste
 */
public class GraphUtils {
    public static void addEdge(ArrayList<Integer>[] g, int src, int dest){
        g[src].add(dest);
    }
    public static void displayGraph(ArrayList<Integer> []g){
        for(int i= 0; i<g.length; i++){
            System.out.print(i + "-->");
            for(int j=0; j<g[i].size(); j++){
                System.out.print(g[i].get(j)+ " ");
            }
            System.out.println("");
        }
    }
    public ArrayList<Integer>[] initializeCNF(int[]statement){
        int count=countVariables(statement);
        ArrayList<Integer>[] graph=new ArrayList [(4*count*count)];
        for(int i=0; i<graph.length; i++){
            graph[i]=new ArrayList<>();
        }
        if(statement.length%2==1){
            return graph;
        }
        for(int i=0; i<statement.length-1; i++){
            if(statement[i] > 0){
                if(statement[i+1] > 0){
                    graph[count+statement[i]].add(statement[i+1]);
                    graph[count+statement[i+1]].add(statement[i]);
                }else{
                    graph[count+statement[i]].add(count-statement[i+1]);
                    graph[-statement[i+1]].add(statement[i]);
                }
            }else{
                if(statement[i+1] > 0){
                    graph[-statement[i]].add(statement[i+1]);
                    graph[count+statement[i+1]].add(count-statement[i]);
                }else{
                    graph[-statement[i]].add(count-statement[i+1]);
                    graph[-statement[i+1]].add(count-statement[i]);
                }
            }
        }
        return graph;
    }
    public int countVariables(int[]statement){
        HashSet<Integer> variables=new HashSet<>();
        for(int i=0; i<statement.length;i++){
                variables.add(Math.abs(statement[i]));
        }
        return variables.size();
    }
}
