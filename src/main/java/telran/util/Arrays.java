package telran.util;

import java.util.Comparator;
import java.util.function.Predicate;

public class Arrays {
    public static int search(int[] ar, int key) {
        int index = 0;
        while (index < ar.length && key != ar[index]) {
            index++;
        }
        return index == ar.length ? -1 : index;
    }

    public static int[] add(int[] ar, int number) {
        int[] res = java.util.Arrays.copyOf(ar, ar.length + 1);
        res[ar.length] = number;
        return res;
    }

    public static int[] insert(int[] ar, int index, int number) {
        int[] newArr = java.util.Arrays.copyOf(ar, ar.length + 1);
        newArr[index] = number;
        System.arraycopy(ar, index, newArr, index + 1, ar.length - index);
        return newArr;
    }

    public static int[] remove(int[] numbers, int index) {
        int[] newArr = java.util.Arrays.copyOf(numbers, numbers.length - 1);
        System.arraycopy(numbers, index + 1, newArr, index, newArr.length - index);
        return newArr;
    }

    private static boolean pushMaxAtEnd(int[] ar, int length) {
        boolean res = true;
        for (int i = 0; i < length; i++) {
            if (ar[i] > ar[i + 1]) {
                res = false;
                swap(ar, i, i + 1);
            }
        }
        return res;
    }

    public static void swap(int[] ar, int i, int j) {
        int tmp = ar[i];
        ar[i] = ar[j];
        ar[j] = tmp;
    }

    public static int[] sort(int[] ar) {
        int length = ar.length;
        boolean flSorted = false;
        while (!flSorted) {
            length--;
            flSorted = pushMaxAtEnd(ar, length);
        }
        return ar;
    }

    /**
     * 
     * @param ar  - sorted array
     * @param key - being searched number
     * @return see comments definition
     */
    public static int binarySearch(int[] ar, int key) {
        // index of the search key, if it is contained in the array;
        // otherwise, (-(insertion point) - 1).
        // The insertion point is defined as the point at which the key would be
        // inserted into
        // the array: the index of the first element greater than the key, or a.length
        // if all elements in the array are less than the specified key. Note that this
        // guarantees that the return value will be >= 0 if and only if the key is
        // found.
        int low = 0, high = ar.length - 1;
        int res = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (ar[mid] < key) {
                low = mid + 1;
            } else if (ar[mid] > key) {
                high = mid - 1;
            } else if (ar[mid] == key) {
                res = mid;
                break;
            }
        }
        return res == -1 ? -low - 1 : res;
    }

    public static int[] insertSorted(int[] arSorted, int number) {
        // arSorted is sorted array
        // to insert number at index to keep the array sorted
        // additional sorting is disallowed
        int[] newArr; 
        int pos = binarySearch(arSorted, number);
        if (pos < 0) {
            pos = Math.abs(pos) - 1;
            newArr = insert(arSorted, pos, number);
        } else {
            newArr = insert(arSorted, pos, number);
        }
        return newArr;
    }

    public static boolean isOneSwap(int[] array) {
        //return true if a given array has exactly one swap to get sorted array
        //the swaped array's elements may or may not be neighbors 
        int[] newArr = java.util.Arrays.copyOf(array, array.length);
        int[] sortedArr = sort(newArr);
        int mismatch = 0;
        for (int i = 0; i < array.length; i++) {        //swaping is a action with two elements
            if (array[i] != sortedArr[i])               //so if we have 2 elements on wrong places => isOneSwap must return true
                mismatch++;                             
        }                                               //if we have sorted array and no elements on a wrong places => isOneSwap must return false
        return mismatch == 2 ? true : false;        
    }
    public static <T> void sort (T[] array, Comparator<T> comparator){
        int length = array.length;
        boolean flSort = false;
        do {
            length--;
            flSort = true;
            for (int i = 0; i < length; i++){
                if (comparator.compare(array[i], array[i + 1]) > 0) {
                    swap(array, i, i + 1);
                    flSort = false;
                }
            }
        } while (!flSort);
    }

    private static <T> void swap(T[] array, int i, int j) {
        T tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
    public static <T> int binarySearch(T[] array, T key, Comparator<T> comp){
        int low = 0, high = array.length - 1;
        int res = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = comp.compare(array[mid], key);
            if (cmp < 0) {
                low = mid + 1;
            } else if (cmp > 0) {
                high = mid - 1;
            } else {
                res = mid;
                break;
            }
        }
        return res == -1 ? -low - 1 : res;
    } 
    public static <T> int binarySearch(T[] array, T key){
        return binarySearch(array, key,(Comparator<T>) Comparator.naturalOrder());
    }

    public static <T> T[] insert(T[] array, int index, T item){
        T [] newArr = java.util.Arrays.copyOf(array, array.length + 1);
        newArr[index] = item;
        System.arraycopy(array, index, newArr, index + 1, array.length - index);
        return newArr;
    }
    public static <T> T[] find(T[] array, Predicate<T> predicate){
        T [] result = java.util.Arrays.copyOf(array, 0);
        for (int i = 0; i < array.length; i++){ 
            if (predicate.test(array[i])){
                result = insert(result, result.length, array[i]);
            }
        }
        return result;
    }
    public static <T> T [] removeIf(T [] array, Predicate <T> predicate) {
        return find(array, predicate.negate()) ;
    }   
    public static String matchesRules(char[] chars, CharacterRule[] mustBeRules, CharacterRule[] mustNotBeRules) {
        String errorMassage0 = checkRules(chars, mustBeRules);
        String errorMassage1 = checkRules(chars,mustNotBeRules);

        return errorMassage0 + errorMassage1;
    }

    private static String checkRules(char[] chars, CharacterRule[] rules) {
        String errorMassage  = "";
        for (int i = 0; i < rules.length; i++) {
            errorMassage += checkRule(chars, rules[i]);
        }
        return errorMassage;
    }

    private static String checkRule(char[] chars, CharacterRule rule) {
        boolean isMissed = true;
        int j = 0;
        while (isMissed && j < chars.length) {
            char ch = chars[j];
            boolean test = rule.getPredicate().test(ch);
            if (test) { 
                isMissed = false;
            }
            j++;
        }
        boolean res = (rule.getFlag() && isMissed) || (!rule.getFlag() && !isMissed) ;
        return res ? rule.getErrorMassage(): "";

    }

}
