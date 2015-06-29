package com.google.guava.example;

import java.util.Date;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

public class Basic {
    /* test preconditions */
    @SuppressWarnings("unused")
    public static void usingPreconditions() {
        int i = 0;
        Object foobar = null;

        Preconditions.checkArgument(i >= 0); // throws IllegalArgumentException
        Preconditions.checkArgument(i >= 0, "Argument was %s but expected nonnegative", i); // throws IllegalArgumentException
        Preconditions.checkNotNull(foobar);  // throws NullPointerException
        Preconditions.checkNotNull(foobar, "Argument was null, looking for %s", "hello");  // throws NullPointerException
    }

    /* helpful toStringHelper method */
    public static void usingStringHelper() {
        @SuppressWarnings("unused")
        class Foo {
              String primary;
              Date secondary;

              @Override
              public String toString() {
                  // useful in overriding the toString() object method
                  return MoreObjects.toStringHelper(this).add("primary=",primary).add("secondary=", secondary).toString();
              }
        }
    }

    public enum Gender {MALE, FEMALE};
    public static void usingComprisonChain() {

        // Without Guava:
        @SuppressWarnings("unused")
        class PersonWithoutGuava implements Comparable<PersonWithoutGuava> {
            private String name;
            private String identity;
            private int zipCode;

            @Override
            public int compareTo(PersonWithoutGuava other) {
                int cmp = name.compareTo(other.name);
                if (cmp != 0) {
                    return cmp;
                }
                cmp = identity.compareTo(other.identity);
                if (cmp != 0) {
                    return cmp;
                }
                return Integer.compare(zipCode, other.zipCode);
            }
        }

        @SuppressWarnings("unused")
        // With Guava:
        class Person implements Comparable<Person> {
            private String name;
            private int zipCode;
            private Gender gender;

            @Override
            public int compareTo(Person that) {
                return ComparisonChain.start()
                    .compare(this.name, that.name)
                    .compare(this.zipCode, that.zipCode)
                    .compare(this.gender, that.gender, Ordering.natural().nullsLast())
                    .result();
            }
        }
    }
}
