/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comparison;

import util.GraphUtils;
import java.util.ArrayList;
/**
 *
 * @author jussiste
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GraphUtils util=new GraphUtils();
        int [] statement= new int[]{1, 2,-1,-2};
        ArrayList<Integer> [] graph= util.initializeCNF(statement);
        BruteForce brute= new BruteForce();
        KosarajuAlgorithm kosajaru= new KosarajuAlgorithm(graph, util.countVariables(statement));
        boolean[] arg= new boolean[]{true, true};
        System.out.println("Brute: " +brute.checkEveryCombination(2, statement));
        TarjanAlgorithm tarjan=new TarjanAlgorithm(graph,util.countVariables(statement));
        //util.displayGraph(tarjan.getReversegraph());
        System.out.println("Tarjan: "+ tarjan.checkSatisfiability());
        System.out.println("Kosajaru: "+ kosajaru.checkSatisfiability());
    }
    
}
