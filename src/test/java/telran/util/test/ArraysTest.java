package telran.util.test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import static telran.util.Arrays.*;
public class ArraysTest {
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
}
