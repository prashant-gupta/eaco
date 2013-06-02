package edu.usit.deepanker.replaceablepatterns;

import edu.usit.deepanker.constants.MutationRegexPattern;
import edu.usit.deepanker.interfaces.ReplaceablePattern;
import sun.text.normalizer.Replaceable;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: abc
 * Date: 19/5/13
 * Time: 2:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class ThisKeyword implements ReplaceablePattern{
        public Pattern getPattern(){
            return MutationRegexPattern.THIS_KEYWORD_REPLACEMENT_PATTERN;
        }
        public List<String> getReplacements(){
            List<String> list =new ArrayList<String>();
            list.add(" ");
            return list;
        }
        public String getPatternName(){
            return "ThisKeyword";
        }
}
