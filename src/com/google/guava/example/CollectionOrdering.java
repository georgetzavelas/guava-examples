package com.google.guava.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;

public class CollectionOrdering {

    @SuppressWarnings("unused")
    private static void usingOrderingWithFunctions() {
        class Foo {
              String primary;
              Date secondary;
              public Foo (String primary, Date secondary) { this.primary = primary; this.secondary = secondary; }
            }

        List<Foo> lotsofFoo = new ArrayList<Foo>();
        lotsofFoo.add(new Foo("One", new Date()));
        lotsofFoo.add(new Foo("Two", new Date()));

        // function to apply before comparison
        Function<Foo, String> primarySortfunction = new Function<Foo, String>() {
              @Override
            public String apply(Foo foo) {
                return foo.primary;
              }
            };
        // function to apply before comparison
        Function<Foo, Long> secondarySortfunction = new Function<Foo, Long>() {
              @Override
            public Long apply(Foo foo) {
                return foo.secondary.getTime();
              }
            };

        Ordering<Foo> primaryOrdering = Ordering.natural() // as opposed to lexographical which does not ignore caps and special chars
                .nullsFirst() // sort nulls at the beginning (default at end)
                .onResultOf(primarySortfunction);
        Ordering<Foo> secondaryOrdering = Ordering.natural()
                .onResultOf(secondarySortfunction)
                .reverse();

        // return immutable list sorted by primary
        List<Foo> sortedList = primaryOrdering.immutableSortedCopy(lotsofFoo);

        // return immutable list sorted by primary than by secondary (only when two primaries are the same)
        Ordering<Foo> compound = primaryOrdering.compound(secondaryOrdering);
        sortedList = compound.immutableSortedCopy(lotsofFoo);
    }
}
