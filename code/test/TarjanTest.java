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
}
