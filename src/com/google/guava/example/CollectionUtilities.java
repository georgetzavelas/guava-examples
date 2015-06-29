package com.google.guava.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;
import com.google.common.primitives.Ints;

public class CollectionUtilities {

    @SuppressWarnings("unused")
    public static void usingStaticConstructors() {
        // used to have to do this
        List<String> listWithoutGuava = new ArrayList<String>();
        // guava way, where the type is inferred
        List<String> list = Lists.newArrayList(); // Java 7: new ArrayList<>();
        List<String> listInitialized = Lists.newArrayList("alpha", "beta", "gamma");
        Map<String, Integer> map = Maps.newHashMap();
        // limit the size, although we can do this with Java 7 as well
        List<String> exactly100 = Lists.newArrayListWithCapacity(100); // Java 7: new ArrayList<>(100)

    }

    @SuppressWarnings("unused")
    private static void usingLists() {
        List<Integer> myInts = Ints.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15);

        // Reversed view of the list
        List<Integer> myIntsReversed = Lists.reverse(myInts);
    }

    @SuppressWarnings("unused")
    private static void usingIterables() {
        List<Integer> myInts = Ints.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15);

        // concatenated has elements 1, 2, 3, 4, 5, 6
        Iterable<Integer> concatenated = Iterables.concat(Ints.asList(1, 2, 3),Ints.asList(4, 5, 6));
        // Return FREQUENCY of an item
        int frequency = Iterables.frequency(myInts, 3);
        // Returns an unmodifiable view of the iterable partitioned into chunks of the specified size.
        Iterable<List<Integer>> partitionedLists = Iterables.partition(myInts, 5);
        // Returns the first element of the iterable, or the default value if empty.
        int firstItem = Iterables.getFirst(myInts, null);
        // Return last item element of the iterable, or the default value if empty.
        int lastItem = Iterables.getLast(myInts, null);
        // Make into an array
        Integer[] integers = Iterables.toArray(myInts, Integer.class);
        // Filter can also be used to return a view of filter classes (in this case it is all of the elements)
        Iterable<Integer> onlyStrings = Iterables.filter(myInts, Integer.class);
    }

    public static void usingSetsForUnionIntersection() {
        List<String> firstList = Arrays.asList("One", "Two", "Three", "Four");
        List<String> secondList = Arrays.asList("One", "Four", "Five", "Six");

        Set<String> firstListAsSet = FluentIterable.from(firstList).toSet();
        Set<String> secondListAsSet = FluentIterable.from(secondList).toSet();

        SetView<String> unionSet = Sets.union(firstListAsSet, secondListAsSet);
        System.out.println("Union:" + unionSet);

        SetView<String> intersectionSet = Sets.intersection(firstListAsSet, secondListAsSet);
        System.out.println("Intersection:" + intersectionSet);

        SetView<String> diffSet = Sets.difference(firstListAsSet, secondListAsSet);
        System.out.println("Difference:" + diffSet);

        SetView<String> symDiffSet = Sets.symmetricDifference(firstListAsSet, secondListAsSet);
        System.out.println("Symmetric Difference:" + symDiffSet);
    }

    @SuppressWarnings("unused")
    public static void usingMaps() {
        // assuming the collection of objects each has a unique attribute, in this case length (if not use Multimaps)
        List<String> strings = Arrays.asList("one", "three", "four");
        // create a Map where the key is based on the unique attribute
        ImmutableMap<Integer, String> stringsByIndex = Maps.uniqueIndex(strings, new Function<String, Integer> () {
            @Override
            public Integer apply(String string) {
              return string.length();
            }
        });

        // Venn diagram comparison of maps
        Map<String, Integer> left = ImmutableMap.of("a", 1, "b", 2, "c", 3);
        Map<String, Integer> right = ImmutableMap.of("b", 2, "c", 4, "d", 5);
        MapDifference<String, Integer> diff = Maps.difference(left, right);

        diff.entriesInCommon(); // {"b" => 2}
        diff.entriesDiffering(); // {"c" => (3, 4)}
        diff.entriesOnlyOnLeft(); // {"a" => 1}
        diff.entriesOnlyOnRight(); // {"d" => 5}
    }

    @SuppressWarnings("unused")
    public static void usingMultiMaps() {
        ImmutableSet<String> digits = ImmutableSet.of("zero", "one", "two", "three", "four",
                "five", "six", "seven", "eight", "nine");
        Function<String, Integer> lengthFunction = new Function<String, Integer>() {
            @Override
            public Integer apply(String string) {
                return string.length();
            }
        };

        // create a MultiMap where the key is based on the length of the string
        ImmutableListMultimap<Integer, String> digitsByLength = Multimaps.index(digits, lengthFunction);
        /*
         * digitsByLength maps:
         *  3 => {"one", "two", "six"}
         *  4 => {"zero", "four", "five", "nine"}
         *  5 => {"three", "seven", "eight"}
         */


        Map<String, Integer> map = ImmutableMap.of("a", 1, "b", 1, "c", 2);
        // View of a Map as a Multimap!!!
        SetMultimap<String, Integer> multimap = Multimaps.forMap(map);
        // multimap maps ["a" => {1}, "b" => {1}, "c" => {2}]
        Multimap<Integer, String> inverse = Multimaps.invertFrom(multimap, HashMultimap.<Integer, String> create());
        // inverse maps [1 => {"a", "b"}, 2 => {"c"}]
    }

    @SuppressWarnings("unused")
    public static void usingMultiSets() {
        Multiset<String> multiset1 = HashMultiset.create();
        multiset1.add("a", 2);

        Multiset<String> multiset2 = HashMultiset.create();
        multiset2.add("a", 5);

        multiset1.containsAll(multiset2); // returns true: all unique elements are contained,
          // even though multiset1.count("a") == 2 < multiset2.count("a") == 5
        Multisets.containsOccurrences(multiset1, multiset2); // returns false
        Multisets.removeOccurrences(multiset2, multiset1); // multiset2 now contains 3 occurrences of "a"
        multiset2.removeAll(multiset1); // removes all occurrences of "a" from multiset2, even though multiset1.count("a") == 2
        multiset2.isEmpty(); // returns true


        Multiset<String> multiset = HashMultiset.create();
        multiset.add("a", 3);
        multiset.add("b", 5);
        multiset.add("c", 1);

        // highestCountFirst, like its entrySet and elementSet, iterates over the elements in order {"b", "a", "c"}
        ImmutableMultiset<String> highestCountFirst = Multisets.copyHighestCountFirst(multiset);
    }
}
