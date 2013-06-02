package edu.usit.deepanker.replaceablepatterns;

import edu.usit.deepanker.constants.MutationRegexPattern;
import edu.usit.deepanker.interfaces.ReplaceablePattern;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: prashant
 * Date: 12/5/13
 * Time: 4:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class SubtractArithmeticOperator implements ReplaceablePattern {
    public Pattern getPattern(){
        return MutationRegexPattern.SUBTRACTION_ARITHMETIC_OPERATOR_REPLACEMENT_PATTERN;
    }
    public List<String> getReplacements(){
        List<String> list =new ArrayList<String>();
        list.add("+");
        list.add("/");
        list.add("*");
        return list;
    }
    public String getPatternName(){
        return "SubtractArithmeticOperator";
    }
}
