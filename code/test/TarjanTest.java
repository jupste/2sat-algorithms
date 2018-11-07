/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pkg2sat.comparison.TarjanAlgorithm;
import pkg2sat.comparison.util.GraphUtils;

/**
 *
 * @author jussiste
 */
public class TarjanTest {
    private GraphUtils util;
    
    @Before
    public void setUp() {
        this.util= new GraphUtils();
    }
    
    @Test
    public void givesTrueWhenSmallCNFisSatisfiable(){
        int [] teststatement=new int []{1,2,-1,-2};
        TarjanAlgorithm t= new TarjanAlgorithm(util.initializeCNF(teststatement) , util.countVariables(teststatement));
        assertTrue(t.checkSatisfiability());
    }
    @Test
    public void givesFalseWhenSmallCNFnotSatisfiable(){
        int [] teststatement= new int[]{1,2,-1,-2, 1, -2};
        TarjanAlgorithm t= new TarjanAlgorithm(util.initializeCNF(teststatement), util.countVariables(teststatement));
        assertFalse(t.checkSatisfiability());
    }
    @Test
    public void givesTrueWhenLargeCNFisSatisfiable(){
        int [] bigstatement=new int[1000000];
        for(int i=0; i<1000000; i++){
            bigstatement[i]=i%100;
        }
        TarjanAlgorithm t= new TarjanAlgorithm(util.initializeCNF(bigstatement), util.countVariables(bigstatement));
        assertTrue(t.checkSatisfiability());
    }
    @Test
    public void givesFalseWhenLargeCNFnotSatisfiable(){
        int [] bigstatement= new int[1000000]; 
        for(int i=0; i<1000000; i++){
            bigstatement[i]=i%100;
        }
        bigstatement[8551]= 31;
        bigstatement[8552]= 22;
        bigstatement[8553]= -31;
        bigstatement[8554]= -22;
        bigstatement[8555]= 31;
        bigstatement[8556]= -22;
        TarjanAlgorithm t= new TarjanAlgorithm(util.initializeCNF(bigstatement), util.countVariables(bigstatement));
        assertFalse(t.checkSatisfiability());
    }
}
