package telran.util;

import java.util.function.Predicate;

public class CharacterRule {
    public boolean flag;
    public Predicate<Character> predicate ;
    public String errorMassage;

    // constructor
    public CharacterRule(boolean flag, Predicate <Character> predicate, String errorMassage){
        this.flag = flag;
        this.predicate = predicate;
        this.errorMassage = errorMassage;
    }

    public boolean getFlag() {
        return flag;
    }

    public Predicate <Character> getPredicate() {
        return predicate;
    }
    public String getErrorMassage () {
        return errorMassage;
    }
}
