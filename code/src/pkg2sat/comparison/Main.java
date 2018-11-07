/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2sat.comparison;

import java.util.ArrayList;
import pkg2sat.comparison.util.*;
/**
 *
 * @author jussiste
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    // placeholder main method to test code functionality
    public static void main(String[] args) {
        GraphUtils util=new GraphUtils();
        int[] statement=new int []{1, 2, -1, -2, 1, -2};
        ArrayList<Integer> [] graph= util.initializeCNF(statement);

        //util.displayGraph(graph);
        
        TarjanAlgorithm tarjan=new TarjanAlgorithm(graph,2);
        System.out.println("");
        //util.displayGraph(tarjan.getReversegraph());
        System.out.println(tarjan.checkSatisfiability());
    }
    
}
