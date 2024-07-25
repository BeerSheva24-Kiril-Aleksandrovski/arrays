package telran.util.test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import telran.util.CharacterRule;

import static telran.util.Arrays.*;

import java.util.Comparator;
import java.util.Random;
import java.util.function.Predicate;

public class ArraysTest {
    private static final int N_ELEMENTS = 1_000;
    int[] numbers = { 10, 7, 12, -4, 13, 3, 14 };

    @Test
    void searchTest() {
        assertEquals(0, search(numbers, 10));
        assertEquals(6, search(numbers, 14));
        assertEquals(3, search(numbers, -4));
        assertEquals(-1, search(numbers, 100));
    }

    @Test
    void addTest() {
        int newNumber = 100;
        int[] expected = { 10, 7, 12, -4, 13, 3, 14, newNumber };
        assertArrayEquals(expected, add(numbers, newNumber));
    }

    @Test
    void insertTest() {
        int newNumber = 100;
        int[] expected = { 10, 7, 12, -4, newNumber, 13, 3, 14 };
        assertArrayEquals(expected, insert(numbers, 4, 100));
        int[] expected1 = { newNumber, 10, 7, 12, -4, 13, 3, 14 };
        assertArrayEquals(expected1, insert(numbers, 0, 100));
        int[] expected2 = { 10, 7, 12, -4, 13, 3, 14, newNumber };
        assertArrayEquals(expected2, insert(numbers, 7, 100));

    }

    @Test
    void removeTest() {
        int[] expected = { 10, 7, 12, -4, 3, 14 };
        assertArrayEquals(expected, remove(numbers, 4));
        int[] expected1 = { 7, 12, -4, 13, 3, 14 };
        assertArrayEquals(expected1, remove(numbers, 0));
        int[] expected2 = { 10, 7, 12, -4, 13, 3 };
        assertArrayEquals(expected2, remove(numbers, 6));
        int[] expected3 = { 10, 7, -4, 13, 3, 14 };
        assertArrayEquals(expected3, remove(numbers, 2));

    }

    @Test
    void sortTest() {
        int[] testNubers = java.util.Arrays.copyOf(numbers, numbers.length);
        int[] expected = { -4, 3, 7, 10, 12, 13, 14 };
        sort(testNubers);
        assertArrayEquals(expected, testNubers);
    }

    @Test
    void sortTestRandomArray() {
        int[] array = getRandomlyArray(N_ELEMENTS);
        int limit = array.length - 1;
        sort(array);
        for (int i = 0; i < limit; i++) {
            assertTrue(array[i] <= array[i + 1]);
        }
    }

    private int[] getRandomlyArray(int nElements) {
        int[] res = new int[nElements];
        Random random = new Random();
        for (int i = 0; i < nElements; i++) {
            res[i] = random.nextInt();
        }
        return res;
    }

    @Test
    void binarySearchTest() {
        int[] expected = { -4, 3, 7, 10, 12, 13, 14 };
        assertEquals(3, binarySearch(expected, 10));
        assertEquals(0, binarySearch(expected, -4));
        assertEquals(6, binarySearch(expected, 14));
        assertEquals(-8, binarySearch(expected, 15));
        assertEquals(-1, binarySearch(expected, -5));
    }

    @Test
    void insertSortedTest() {
        int[] arSorted = { -4, 3, 7, 10, 12, 13, 14 };
        int[] expectedBeg = { -5, -4, 3, 7, 10, 12, 13, 14 };
        assertArrayEquals(expectedBeg, insertSorted(arSorted, -5));
        int[] expectedEnd = { -4, 3, 7, 10, 12, 13, 14, 15 };
        assertArrayEquals((expectedEnd), insertSorted(arSorted, 15));
        int[] expectedMid = { -4, 3, 4, 7, 10, 12, 13, 14 };
        assertArrayEquals(expectedMid, insertSorted(arSorted, 4));
        int[] expectedMid1 = { -4, 3, 7, 10, 10, 12, 13, 14 };
        assertArrayEquals(expectedMid1, insertSorted(arSorted, 10));
        int[] expectedBeg2 = { -4, -4, 3, 7, 10, 12, 13, 14 };
        assertArrayEquals(expectedBeg2, insertSorted(arSorted, -4));
    }

    @Test
    void isOneSwapTest() {
        assertTrue(isOneSwap(new int[] { 1, 5, 3, 4, 2, 5 })); // need to swap 5 and 2 => number of swaps = 1
        assertFalse(isOneSwap(new int[] { 1, 2, 3, 4, 5, 6, 1, 4 })); // number of swaps > 1
        assertTrue(isOneSwap(new int[] { 1, 6, 3, 4, 5, 2, 6 })); // need to swap 6 and 2 => number of swaps = 1
        assertTrue(isOneSwap(new int[] { 7, 2, 3, 3, 3, 4, 5, 6, 1 })); // need to swap 7 and 1 => number of swaps = 1
        assertFalse(isOneSwap(new int[] { 1, 2, 3, 4, 5, 6 })); // no need to swap
        assertTrue(isOneSwap(new int[] { 1, 2, 4, 3, 5, 6 })); // swap 4 and 3 => number of swaps = 1
        assertFalse(isOneSwap(new int[] { 1, 2, 3, 5, 6, 4 })); // numer of swops = 2
        assertFalse(isOneSwap(new int[] { 1, 2, 3, 4, 5, 6, 1 }));

    }

    @Test
    void sortAnyTypeTest() {
        String[] strings = { "lmn", "cfta", "w", "aa" };
        String[] expectedASCII = { "aa", "cfta", "lmn", "w" };
        String[] expectedLength = { "w", "aa", "lmn", "cfta" };
        sort(strings, (a, b) -> a.compareTo(b));
        assertArrayEquals(expectedASCII, strings);
        sort(strings, (a, b) -> Integer.compare(a.length(), b.length()));
        assertArrayEquals(expectedLength, strings);

    }

    @Test
    void binarySearchAnyTypeTest() {
        String [] strings = {"aa", "cfta", "lmn", "w"};
        Integer[] numbers = {1000, 2000, 3000};
        Comparator<String> compStrings = (a, b) -> a.compareTo(b);
        Comparator<Integer> compInteger = Integer::compare;
        assertEquals(1, binarySearch(strings, "cfta", compStrings));
        assertEquals(0, binarySearch(numbers, 1000, compInteger));
    }

    @Test
    void binarySearchNoComparator() {
        String[] strings = { "aa", "cfta", "lmn", "w" };
        Person prs1 = new Person(10, "Vasya");
        Person prs2 = new Person(20, "Itay");
        Person prs3 = new Person(30, "Sara");
        Person[] persons = {
                prs1, prs2, prs3
        };
        assertEquals(1, java.util.Arrays.binarySearch(strings, "cfta"));
        assertEquals(0, java.util.Arrays.binarySearch(persons, prs1));
        assertEquals(-1, java.util.Arrays.binarySearch(persons, new Person(5, "Serg")));
    }

    @Test
    void evenOddSorting() {
        Integer[] array = { 7, -8, 10, -100, 13, -10, 99 };
        Integer[] expected = { -100, -10, -8, 10, 99, 13, 7 }; // even in ascending order first, odd in descending order after that
        sort(array, (a, b) -> {
            boolean isArg0Even = a % 2 == 0;
            boolean isArg1Even = b % 2 == 0;
            boolean noSwapFlag = (isArg0Even && !isArg1Even) ||
            (isArg0Even && isArg1Even && a <= b) ||
             (!isArg0Even && !isArg1Even && a >= b);
            return noSwapFlag ? -1 : 1;
        });
        assertArrayEquals(expected, array);
    }

    @Test
    void findTest() {
        Integer[] array = { 7, -8, 10, -100, 13, -10, 99 };
        Integer[] expected = { 7, 13, 99 };
        assertArrayEquals(expected, find(array, n -> n % 2 != 0));

    }

    @Test
    void removeIfTest() {
        Integer[] array = { 7, -8, 10, -100, 13, -10, 99 };
        Integer[] expectedEven = { -8, 10, -100, -10 };
        assertArrayEquals(expectedEven, removeIf(array, n -> n % 2 != 0));
        Integer[] expectedOdd = { 7, 13, 99 };
        assertArrayEquals(expectedOdd, removeIf(array, n -> n % 2 == 0));
        String[] string = { "aa", "", "cfta", "", "lmn", "w" };
        String[] withoutEmpty = { "aa", "cfta", "lmn", "w" };
        assertArrayEquals(withoutEmpty, removeIf(string, n -> n.length() == 0));
    }

    @Test
    void matchesRulesTest() {
        // TODO
        // Must be rules: at least one capital letter, at least one lower case letter,
        // at least one digit, at least one dot(.)
        // Must not be rules: space is disallowed
        // examples: mathes - {'a', 'n', '*', 'G', '.', '.', '1'}
        // mismatches - {'a', 'n', '*', 'G', '.', '.', '1', ' '} -> "space disallowed",
        // {'a', 'n', '*', '.', '.', '1'} -> "no capital",
        // {'a', 'n', '*', 'G', '.', '.'} -> "no digit"
        char[] chars = { 'a', 'n', '*', 'G', '.', '.', '1' }; // no mistakes
        char[] chars2 = { 'a', 'n', '*', '.', '.', '1' }; // no captipal
        char[] chars3 = { 'F', 'A', '*', '.', '.', '1' }; // no lower case
        char[] chars4 = { 'a', 'M', '*', '1', 'f', '1' }; // no dot
        char[] chars5 = { 'a', 'n', '*', ' ', '1' }; // no capital, no dot, space forbidden
        char[] chars6 = { 'a', 'M', '*', ' ', '1', '.' }; // space forbidden

        CharacterRule capLetterExist = new CharacterRule(true, Character::isUpperCase, "no capital letters ");
        CharacterRule lowCaseExist = new CharacterRule(true, Character::isLowerCase, "no lower case letters ");
        CharacterRule digitExist = new CharacterRule(true, Character::isDigit, "no digits ");
        CharacterRule dotExist = new CharacterRule(true, (a) -> a == '.', "no dots ");
        CharacterRule[] mustBeRules = {
                capLetterExist,
                lowCaseExist,
                digitExist,
                dotExist
        };

        CharacterRule spaceExist = new CharacterRule(false, Character::isSpaceChar, "using space is forbidden ");
        CharacterRule[] mustNotBeRules = {
                spaceExist
        };
        assertEquals("", matchesRules(chars, mustBeRules, mustNotBeRules));
        assertEquals("no capital letters ", matchesRules(chars2, mustBeRules, mustNotBeRules));
        assertEquals("no lower case letters ", matchesRules(chars3, mustBeRules, mustNotBeRules));
        assertEquals("no dots ", matchesRules(chars4, mustBeRules, mustNotBeRules));
        assertEquals("no capital letters no dots using space is forbidden ", matchesRules(chars5, mustBeRules, mustNotBeRules));
        assertEquals("using space is forbidden ", matchesRules(chars6, mustBeRules, mustNotBeRules));

    }

}