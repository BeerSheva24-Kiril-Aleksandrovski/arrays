package telran.util.test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import static telran.util.Arrays.*;

import java.util.Random;
public class ArraysTest {
private static final int N_ELEMENTS = 1_000;
int[] numbers = {10, 7, 12, -4, 13, 3, 14};
@Test
void searchTest(){
    assertEquals(0, search(numbers, 10));
    assertEquals(6, search(numbers, 14));
    assertEquals(3, search(numbers, -4));
    assertEquals(-1, search(numbers, 100));
}

@Test
void addTest() {
    int newNumber = 100;
    int [] expected = {10, 7, 12, -4, 13, 3, 14, newNumber};
    assertArrayEquals(expected, add(numbers, newNumber));
}

@Test
void insertTest() {
    int newNumber = 100;
    int [] expected = {10, 7, 12, -4, newNumber, 13, 3, 14};
    assertArrayEquals(expected, insert(numbers,4,100 ));
    int [] expected1 = {newNumber, 10, 7, 12, -4, 13, 3, 14};
    assertArrayEquals(expected1, insert(numbers,0,100 ));
    int [] expected2 = {10, 7, 12, -4, 13, 3, 14, newNumber};
    assertArrayEquals(expected2, insert(numbers,7,100 ));
    
}

@Test
void removeTest() {
    int [] expected = {10, 7, 12, -4, 3, 14};
    assertArrayEquals(expected, remove(numbers,4));
    int [] expected1 = {7, 12, -4, 13, 3, 14};
    assertArrayEquals(expected1, remove(numbers,0));
    int [] expected2 = {10, 7, 12, -4, 13, 3};
    assertArrayEquals(expected2, remove(numbers,6));
    int [] expected3 = {10, 7, -4, 13, 3, 14};
    assertArrayEquals(expected3, remove(numbers,2));

}
@Test
void sortTest () {
    int [] testNubers = java.util.Arrays.copyOf(numbers, numbers.length);
    int [] expected = {-4, 3, 7, 10, 12, 13, 14};
    sort(testNubers);
    assertArrayEquals(expected, testNubers);
}
@Test
void sortTestRandomArray () {
    int [] array = getRandomlyArray (N_ELEMENTS);
    int limit = array.length - 1;
    sort(array);
    for (int i = 0; i < limit; i++){
        assertTrue(array [i] <= array [i + 1]);
    }
}

private int[] getRandomlyArray(int nElements) {
    int [] res = new int[nElements];
    Random random = new Random();
    for (int i = 0; i < nElements; i++){
        res [i] = random.nextInt();
    }
    return res;
}
@Test
void binarySearchTest () {
    int [] expected =  {-4, 3, 7, 10, 12, 13, 14};
    assertEquals(3, binarySearch(expected, 10));
    assertEquals(0, binarySearch(expected, -4));
    assertEquals(6, binarySearch(expected, 14));
    assertEquals(-8, binarySearch(expected, 15));
    assertEquals(-1, binarySearch(expected, -5));
}
@Test
void insertSortedTest () {
    int [] arSorted =  {-4, 3, 7, 10, 12, 13, 14};
    int [] expectedBeg =  {-5, -4, 3, 7, 10, 12, 13, 14};
    assertArrayEquals(expectedBeg, insertSorted(arSorted, -5));
    int [] expectedEnd =  {-4, 3, 7, 10, 12, 13, 14, 15};
    assertArrayEquals((expectedEnd), insertSorted(arSorted, 15));
    int [] expectedMid =  {-4, 3, 4, 7, 10, 12, 13, 14};
    assertArrayEquals(expectedMid, insertSorted(arSorted, 4));
    int [] expectedMid1 =  {-4, 3, 7, 10, 10, 12, 13, 14};
    assertArrayEquals(expectedMid1,insertSorted(arSorted, 10));
    int [] expectedBeg2 =  {-4, -4, 3, 7, 10, 12, 13, 14};
    assertArrayEquals(expectedBeg2,insertSorted(arSorted, -4));
}
@Test
void isOneSwapTest() {
    assertTrue(isOneSwap(new int [] {1,5,3,4,2,5}));        //need to swap 5 and 2 => number of swaps = 1
    assertFalse(isOneSwap(new int [] {1,2,3,4,5,6,1,4}));   //number of swaps > 1
    assertTrue(isOneSwap(new int [] {1,6,3,4,5,2,6}));      //need to swap 6 and 2 => number of swaps = 1
    assertTrue(isOneSwap(new int [] {7,2,3,3,3,4,5,6,1}));  //need to swap 7 and 1 => number of swaps = 1
    assertFalse(isOneSwap(new int [] {1,2,3,4,5,6}));       //no need to swap
    assertTrue(isOneSwap(new int [] {1,2,4,3,5,6}));        //swap 4 and 3 => number of swaps = 1 
    assertFalse(isOneSwap(new int [] {1,2,3,5,6,4}));       //numer of swops = 2
    assertFalse(isOneSwap(new int [] {1,2,3,4,5,6,1}));

}
@Test
void sortAnyTypeTest() {
    String [] strings = {"lmn", "cfta", "w", "aa"};
    String [] expectedASCII = {"aa", "cfta", "lmn", "w"};
    String [] expectedLength = {"w", "aa", "lmn", "cfta"};
    sort(strings, new ComparatorASCII());
    assertArrayEquals(expectedASCII, strings);
    sort(strings, new ComparatorLength());
    assertArrayEquals(expectedLength, strings);

}
@Test
void binarySearchAnyTypeTest () {
    //String [] strings = {"lmn", "cfta", "w", "aa"};
    String [] expectedASCII = {"aa", "cfta", "lmn", "w"};
    String [] expectedLength = {"w", "aa", "lmn", "cfta"};
    int res = binarySearch(expectedLength,"lmn", new ComparatorLength());
    assertEquals(2, res);
    res = binarySearch(expectedLength,"cfta", new ComparatorLength());
    assertEquals(3, res);
    res = binarySearch(expectedASCII,"w", new ComparatorASCII());
    assertEquals(3, res);
    res = binarySearch(expectedASCII,"aa", new ComparatorASCII());
    assertEquals(0, res);
    Integer[] numbers = {10, 20, 30, 40, 50};
    res = binarySearch(numbers, 30, new ComparatorInteger());
    assertEquals(2, res);
    res = binarySearch(numbers, 60, new ComparatorInteger());
    assertEquals(-6, res);
    res = binarySearch(numbers, -5, new ComparatorInteger());
    assertEquals(-1, res);
}   
}
