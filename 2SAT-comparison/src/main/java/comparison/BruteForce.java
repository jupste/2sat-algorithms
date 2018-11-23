/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comparison;

/**
 *
 * @author jussiste
 */
public class BruteForce {

    /**
     * A method that recursively checks every combination of boolean arrays of size numVariables. Very time intensive and for example as little as 10 variables create over 1000 arrays. O(2^n)
     * @param numVariables size of array
     * @param statement given CNF
     * @return whether there exists a truth distribution that satisfies the given CNF
     */
    public boolean checkEveryCombination(int numVariables, int[]statement){
        for (int i = 0; i < Math.pow(2, numVariables); i++) {
            String bin = Integer.toBinaryString(i);
            while (bin.length() < numVariables)
                bin = "0" + bin;
            char[] chars = bin.toCharArray();
            boolean[] booleanArray = new boolean[numVariables];
            for (int j = 0; j < chars.length; j++) {
                booleanArray[j] = chars[j] == '0' ? true : false;
            }
            if(checkSatisfiability(statement, booleanArray)){
                return true;
            }
        }
        return false;
    }
       
    /**
     * Checks if a given truth distribution can solve the CNF.
     * @param statement given CNF to solve
     * @param arguments given truth distribution
     * @return false if there exists such a conjuction that is wrong with given truth distribution, true if there exists no conflicting conjuction.
     */
    public boolean checkSatisfiability(int[]statement, boolean[] arguments){
        for(int i=0; i<statement.length; i+=2){
            if(statement[i]>0){
                if(statement[i+1]>0){
                    if(!arguments[statement[i]-1] && !arguments[statement[i+1]-1]){
                        return false;
                    }
                }else{
                    if(!arguments[statement[i]-1]&& arguments[-statement[i+1]-1]){
                        return false;
                    }
                }
            }else{
                if(statement[i+1]>0){
                    if(arguments[-statement[i]-1] && !arguments[statement[i+1]-1]){
                        return false;
                    }
                }else{
                    if(arguments[-statement[i]-1] && arguments[-statement[i+1]-1]){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
