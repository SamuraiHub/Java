/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.junit.*;
import static org.junit.Assert.assertArrayEquals;
/**
 * 
 * Testing KComplementary algorithm
 * 
 * @author Muaz Aljarhi
 */

public class KComplementaryTest {
    
    private KComplementary kComplementary;
    
    @Before
    public void initiate() {
        this.kComplementary = new KComplementary();
    }
    
    
    @Test
    public void test1() {
        Integer[][] expectedResult = new Integer[][]{{1,9},{5,5},{9,1}};
        int[] test = new int[]{1,5,9};
        
        assertArrayEquals(this.kComplementary.getKComplementaryPairs(10,  test), expectedResult);
    }
    
    @Test
    public void test2() {
        Integer[][] expectedResult = new Integer[][]{{5,7},{7,5}};
        int[] test = new int[]{3,5,7};
        
        assertArrayEquals(this.kComplementary.getKComplementaryPairs(12,  test), expectedResult);
    }
    
    @Test
    public void test3() {
        Integer[][] expectedResult = new Integer[][]{{-1,1},{0,0},{1,-1}};
        int[] test = new int[]{5,-1,0,-2,3, 1};
        
        assertArrayEquals(this.kComplementary.getKComplementaryPairs(0,  test), expectedResult);
    }
    
    @Test
    public void test4() {
        Integer[][] expectedResult = new Integer[][]{};
        int[] test = new int[]{};
        
        assertArrayEquals(this.kComplementary.getKComplementaryPairs(1,  test), expectedResult);
    }
    
    public static void main(String args[]) {
      org.junit.runner.JUnitCore.main("KComplementaryTest");
    }
    

}
