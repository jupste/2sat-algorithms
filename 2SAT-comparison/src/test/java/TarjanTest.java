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
import comparison.TarjanAlgorithm;
import util.GraphUtils;

/**
 *
 * @author jussiste
 */
public class TarjanTest {

    private GraphUtils util;

    @Before
    public void setUp() {
        this.util = new GraphUtils();
    }

    @Test
    public void givesTrueWhenSmallCNFisSatisfiable() {
        int[] teststatement = new int[]{1, 2, -1, -2};
        TarjanAlgorithm t = new TarjanAlgorithm(util.initializeCNF(teststatement), util.countVariables(teststatement));
        assertTrue(t.checkSatisfiability());
    }

    @Test
    public void givesFalseWhenSmallCNFnotSatisfiable() {
        int[] teststatement = new int[]{1, 2, -1, -2, 1, -2};
        TarjanAlgorithm t = new TarjanAlgorithm(util.initializeCNF(teststatement), util.countVariables(teststatement));
        assertFalse(t.checkSatisfiability());
    }

    @Test(timeout = 10000)
    public void givesTrueWhenLargeCNFisSatisfiable() {
        int[] bigstatement = new int[1000000];
        for (int i = 0; i < 1000000; i++) {
            bigstatement[i] = i % 100;
        }
        TarjanAlgorithm t = new TarjanAlgorithm(util.initializeCNF(bigstatement), util.countVariables(bigstatement));
        assertTrue(t.checkSatisfiability());
    }

    @Test(timeout = 10000)
    public void givesFalseWhenLargeCNFnotSatisfiable() {
        int[] bigstatement = new int[1000000];
        for (int i = 0; i < 1000000; i++) {
            bigstatement[i] = i % 100+1;
        }
        bigstatement[1] = -1;
        bigstatement[2] = -99;
        bigstatement[99] = -99;
        bigstatement[100] = 1;
        bigstatement[9999] = 99;
        bigstatement[10000] = 1;
        bigstatement[99999] = -1;
        bigstatement[100000] = 99;
        TarjanAlgorithm t = new TarjanAlgorithm(util.initializeCNF(bigstatement), util.countVariables(bigstatement));
        assertFalse(t.checkSatisfiability());
    }
}
