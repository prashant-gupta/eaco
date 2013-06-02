package edu.usit.deepanker;

import java.util.regex.Pattern;

public class MutationPatterns {
    public static Pattern getAddArithmeticOperatorReplacementPattern(){
        return Pattern.compile("[^\\s]+[\\s]*[\\+]+?[\\s]*[^\\s]+");
    }
    public static Pattern getSubtractArithmeticOperatorReplacementPattern(){
        return Pattern.compile("[^\\s]+[\\s]*[\\-]++[\\s]*[^\\s]+");
    }
    public static Pattern getMultiplyArithmeticOperatorReplacementPattern(){
        return Pattern.compile("[^\\s]+[\\s]*[\\*][\\s]*[^\\s]+");
    }
    public static Pattern getDivideArithmeticOperatorReplacementPattern(){
        return Pattern.compile("[^\\s]+[\\s]*[/][\\s]*[^\\s]+");
    }
    public static Pattern getMinusOccurance(){
        return Pattern.compile("\\-");
    }
}
