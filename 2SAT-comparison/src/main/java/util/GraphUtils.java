/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.HashMap;
import util.CustomHashSet;
import util.CustomHashmap.HashMapEntry;

/**
 *
 * @author jussiste
 */
public class GraphUtils {

    /**
     * @param CNF string that contains only numbers
     * @return the CNF in integer array form if the string is valid. Otherwise returns an empty array.
     */
    public int[] createFromString(String CNF) {
        String parse = CNF.replaceAll(";", "");
        String[] variables = parse.split(" ");
        if (variables.length % 2 != 0) {
            System.out.println("Invalid CNF please try again.");
            return new int[0];
        }
        int[] statement = new int[variables.length];
        for (int i = 0; i < variables.length; i++) {
            try {
                statement[i] = Integer.parseInt(variables[i]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid CNF. Please try again");
                return new int[0];
            }
        }
        return statement;
    }

    /**
     *
     * @param statement the proposition sentence as an integer array. The
     * proposition symbols are labeled as integers so that for example array
     * [1,2,-2,1,1,3] means (1 or 2)and(not 2 or 1)and(1 or 3). If the array is
     * not even it is not consider a valid CNF sentence and the method returns
     * an empty array.
     * @return the statement in graph form
     */
    public CustomArrayList<Integer>[] initializeCNF(int[] statement) {
        int count = countVariables(statement);
        CustomArrayList<Integer>[] graph = new CustomArrayList[(4 * count * count)];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new CustomArrayList<>();
        }
        if (statement.length % 2 == 1) {
            return graph;
        }
        for (int i = 0; i < statement.length - 1; i++) {
            if (statement[i] > 0) {
                if (statement[i + 1] > 0) {
                    graph[count + statement[i]].add(statement[i + 1]);
                    graph[count + statement[i + 1]].add(statement[i]);
                } else {
                    graph[count + statement[i]].add(count - statement[i + 1]);
                    graph[-statement[i + 1]].add(statement[i]);
                }
            } else {
                if (statement[i + 1] > 0) {
                    graph[-statement[i]].add(statement[i + 1]);
                    graph[count + statement[i + 1]].add(count - statement[i]);
                } else {
                    graph[-statement[i]].add(count - statement[i + 1]);
                    graph[-statement[i + 1]].add(count - statement[i]);
                }
            }
        }
        return graph;
    }

    /**
     * Generates a large int array that is satisfiable and has a certain number
     * of variables
     *
     * @param variables number of variables in the CNF
     * @return
     */
    public int[] initializeLargeSatisfiable(int variables) {
        int[] statement = new int[1000000];
        for (int i = 0; i < statement.length; i++) {
            statement[i] = i % variables + 1;
        }
        return statement;
    }

    /**
     * Generates a large int array that is not satisfiable and has a certain number
     * of variables
     *
     * @param variables number of variables in the CNF
     * @return
     */
    public int[] initializeLargeNonSatisfiable(int variables) {
        int[] statement = new int[1000000];
        for (int i = 0; i < statement.length; i++) {
            statement[i] = i % variables + 1;
        }
        statement[100] = 1;
        statement[101] = 1;
        statement[102] = -1;
        statement[103] = -1;
        return statement;
    }

    /**
     * Counts the number of variables in a given sentence. A proposition symbol
     * and it's negation are considered to be the same symbol.
     *
     * @param statement the given CNF-sentence in integer array form
     * @return number of variables
     */
    public int countVariables(int[] statement) {
        CustomHashSet<Integer> variables = new CustomHashSet<>();
        for (int i = 0; i < statement.length; i++) {
            variables.add(Math.abs(statement[i]));
        }
        return variables.size();
    }
}
