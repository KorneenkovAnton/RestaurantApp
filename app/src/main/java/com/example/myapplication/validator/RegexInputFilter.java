package com.example.myapplication.validator;

import android.text.InputFilter;
import android.text.Spanned;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexInputFilter implements InputFilter {
    private Pattern pattern;

    public RegexInputFilter(String pattern) {
        this.pattern = Pattern.compile(pattern);
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

        String text = dest.subSequence(0,dstart).toString()+ source.subSequence(start, end).toString()
                + dest.subSequence(dend,dest.length()).toString();

        Matcher matcher = pattern.matcher(text);

        if(!matcher.matches()){
            if(!matcher.hitEnd()){
                return "";
            }
        }

        return null;
    }
}
