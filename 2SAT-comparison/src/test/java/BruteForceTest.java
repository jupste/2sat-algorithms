/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import comparison.BruteForce;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jussiste
 */
public class BruteForceTest {

    private BruteForce brute = new BruteForce();

    @Test
    public void bruteForceCanDetectIfSmallCNFisSatiable() {
        int[] statement = new int[]{1, 2, 3, 4, 5, 6};
        assertTrue(brute.checkEveryCombination(6, statement));
    }

    @Test
    public void bruteForceCanDetectIfSmallCNFisNotSatiable() {
        int[] statement = new int[]{1, 2, -1, -2, 1, -2, -1, 2};
        assertFalse(brute.checkEveryCombination(2, statement));
    }

    @Test
    public void bruteForceCanDetectIfMediumCNFisSatiable() {
        int[] statement = new int[100];
        for (int i = 0; i < 100; i++) {
            statement[i] = i % 10 + 1;
        }
        assertTrue(brute.checkEveryCombination(10, statement));
    }

    @Test
    public void bruteForceCanDetectIfMediumCNFisNotSatiable() {
        int[] statement = new int[100];
        for (int i = 0; i < 100; i++) {
            statement[i] = i % 10 + 1;
        }
        statement[2] = -1;
        statement[3] = -2;
        statement[4] = -1;
        statement[5] = 2;
        statement[6] = 1;
        statement[7] = -2;
        assertFalse(brute.checkEveryCombination(10, statement));
    }
}
