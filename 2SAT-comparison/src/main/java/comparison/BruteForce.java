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
    public boolean checkEveryCombination(int numVariables, int[]statement){
        numVariables++;
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
                for(boolean b:booleanArray){
                    System.out.print(b+ " ");
                }
                return true;
            }
        }
        return false;
    }
       
    
    public boolean checkSatisfiability(int[]statement, boolean[] arguments){
        for(int i=0; i<statement.length; i+=2){
            if(statement[i]>0){
                if(statement[i+1]>0){
                    if(!arguments[statement[i]] && !arguments[statement[i+1]]){
                        return false;
                    }
                }else{
                    if(!arguments[statement[i]]&& arguments[-statement[i+1]]){
                        return false;
                    }
                }
            }else{
                if(statement[i+1]>0){
                    if(arguments[-statement[i]] && !arguments[statement[i+1]]){
                        return false;
                    }
                }else{
                    if(arguments[-statement[i]] && arguments[-statement[i+1]]){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
