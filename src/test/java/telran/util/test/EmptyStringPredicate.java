package telran.util.test;

import java.util.function.Predicate;

public class EmptyStringPredicate implements Predicate<String> {

    @Override
    public boolean test(String arg0) {
        return arg0.length() == 0; 
    }

}