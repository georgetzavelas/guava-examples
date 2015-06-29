package com.google.guava.example;

import java.util.Arrays;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class CollectionTransformOrFilter {


    @SuppressWarnings("unused")
    private static void usingFilterAndFindWithIterablesAndPredicates() {
        List<Integer> myInts = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15);

        // Return as strings *** No Copying Done!! ***
        Iterable<String> asStrings = Iterables.transform(myInts, Functions.toStringFunction());

        // Predicate for matching items with only one length
        Predicate<String> getStringLenOnePredicate = new Predicate<String>(){
            @Override
            public boolean apply(String str) {
                return str.length() == 1;
            }
        };

        // Predicate for matching items that are odd
        Predicate<String> getStringOddValuePredicate = new Predicate<String>(){
            @Override
            public boolean apply(String str) {
                return (str.endsWith("1") || str.endsWith("3") || str.endsWith("5") || str.endsWith("7") || str.endsWith("9"));
            }
        };

        // Filter to only get those with one digit  *** Still No Copying Done! ***
        Iterable<String> onlyThoseWithOneDigit = Iterables.filter(asStrings, getStringLenOnePredicate);

        // Now, it finally applies the transform()s and filter()s!
        List<String> filteredList = Lists.newArrayList(onlyThoseWithOneDigit);
        System.out.println(filteredList);

        // Return if all items satisfy the Predicate
        boolean allHaveLenOne = Iterables.all(asStrings, getStringLenOnePredicate);
        // Return if any items satisfy the Predicate
        boolean anyHaveLenOne = Iterables.any(asStrings, getStringLenOnePredicate);
        // Returns index of first item to satisfy the Predicate
        int firstHaveLenOne = Iterables.indexOf(asStrings, getStringLenOnePredicate);
        // Returns the first item to satisfy the Predicate with a default if none is found
        String firstHaveLenOneItem = Iterables.find(asStrings, getStringLenOnePredicate, "0");

        // Composed Predicate, can be and, or, not
        Predicate<String> composedPredicate = Predicates.and(getStringLenOnePredicate, getStringOddValuePredicate);
        // Filters only get those with one digit and odd
        Iterable<String> onlyThoseWithOneDigitAndOdd = Iterables.filter(asStrings, composedPredicate);
    }


    @SuppressWarnings("unused")
    private static void usingTransformsWithFunctions() {
        List<Integer> myInts = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15);

        // Function to add one to each element
        Function<Integer, Integer> plusOneFunction = new Function<Integer,Integer>(){
            @Override
            public Integer apply(Integer input) {
                return input + 1;
            }
        };

        // Function to multiply by 2 to each element
        Function<Integer, Integer> multiplyByTwoFunction = new Function<Integer,Integer>(){
            @Override
            public Integer apply(Integer input) {
                return input * 2;
            }
        };

        // Combine both functions to work as one
        Function<Integer, Integer> composedFunction = Functions.compose(plusOneFunction, multiplyByTwoFunction);

        List<Integer> addOneAndMultiplyByTwo = Lists.transform(myInts, composedFunction);
    }
}
