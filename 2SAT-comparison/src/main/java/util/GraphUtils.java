/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author jussiste
 */
public class GraphUtils {
    
    /**
     * Prints the given graph. Most likely not needed in final program
     * @param g given graph
     */
    public static void displayGraph(ArrayList<Integer> []g){
        for(int i= 0; i<g.length; i++){
            System.out.print(i + "-->");
            for(int j=0; j<g[i].size(); j++){
                System.out.print(g[i].get(j)+ " ");
            }
            System.out.println("");
        }
    }

    /**
     * 
     * @param statement the proposition sentence as an integer array. The proposition symbols are labeled as integers so that for example array [1,2,-2,1,1,3] means (1 or 2)and(not 2 or 1)and(1 or 3).
     * If the array is not even it is not consider a valid CNF sentence and the method returns an empty array.
     * @return the statement in graph form
     */
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

    /**
     * Counts the number of variables in a given sentence. A proposition symbol and it's negation are considered to be the same symbol.
     * @param statement the given CNF-sentence in integer array form
     * @return number of variables
     */
    public int countVariables(int[]statement){
        HashSet<Integer> variables=new HashSet<>();
        for(int i=0; i<statement.length;i++){
                variables.add(Math.abs(statement[i]));
        }
        return variables.size();
    }
}
