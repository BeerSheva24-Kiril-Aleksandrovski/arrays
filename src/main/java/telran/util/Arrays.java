package telran.util;

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
        int i;
        int[] newArr = java.util.Arrays.copyOf(ar, ar.length + 1);
        for (i = 0; i < newArr.length; i++) {
            if (i < index - 1)
                newArr[i] = ar[i];
            else if (i == index - 1)
                newArr[i] = number;
            else
                newArr[i] = ar[i - 1];
        }
        return newArr;
    }

    public static int[] remove(int[] numbers, int index) {
        int i;
        int[] newArr = java.util.Arrays.copyOf(numbers, numbers.length - 1);
        for (i = 0; i < numbers.length - 1; i++) {
            if (i < index - 1)
                newArr[i] = numbers[i];
            else if (i == index - 1)
                newArr[i] = numbers[i + 1];
            else
                newArr[i] = numbers[i + 1];
        }
        return newArr;
    }
}
