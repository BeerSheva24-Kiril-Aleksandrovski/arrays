package telran.util.test;

import java.util.Comparator;

public class EvenOddComparator implements Comparator <Integer> {

    @Override
    public int compare(Integer arg0, Integer arg1) {
        int res;
        boolean isEven0 = arg0 % 2 == 0;
        boolean isEven1 = arg1 % 2 == 0;
        if (isEven0 == isEven1) {
            int cmp = Integer.compare(arg0, arg1);
            res = isEven0 ? cmp : -cmp;
        } else {
            res = isEven0 ? -1 : 1;
        }
        return res;
    }
}
