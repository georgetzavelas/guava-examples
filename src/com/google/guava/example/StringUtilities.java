package com.google.guava.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.escape.CharEscaperBuilder;
import com.google.common.html.HtmlEscapers;
import com.google.common.net.UrlEscapers;
import com.google.common.xml.XmlEscapers;

public class StringUtilities {

    @SuppressWarnings("unused")
    public static void joiningStrings() {
        List<String> someList = Arrays.asList("foo", "bar", "baz", "quux", null);

        String guavaResult = Joiner.on(" and ").skipNulls().join(someList);  // foo and bar and baz and quux
        guavaResult = Joiner.on(" and ").useForNull("DEFAULT").join(someList);  // foo and bar and baz and quux and DEFAULT
    }

    @SuppressWarnings("unused")
    public static void joiningHashMapAsStrings() {
        Map<Integer, String> numberWords = new HashMap<Integer, String>();
        numberWords.put(1, "One");
        numberWords.put(2, "Two");
        numberWords.put(3, "Three");
        numberWords.put(4, null);

        String guavaResult = Joiner.on(" | ").withKeyValueSeparator(" -> ").useForNull("Unknown").join(numberWords);
        // 1 -> One | 2 -> Two | 3 -> Three | 4 -> Unknown
    }

    @SuppressWarnings("unused")
    public static void splittingStrings() {
        // It's not only easier to tell what this function does - it's also more performant
        // than compiling a regex every time, which is what String.split() does.
        Splitter splitter = Splitter.on('.').trimResults().omitEmptyStrings();
        List<String> guavaResult = Lists.newArrayList(splitter.split(".foo...  bar...baz."));   // [foo, bar, baz]
    }

    public static void matchingCharacters() {
        // These are all way easier to read, and more efficient, than using regexes or rolling your own.
        // trim trailing digits from the string
        System.out.println(CharMatcher.DIGIT.trimTrailingFrom("Fahrenheit451"));    // Fahrenheit
        // figure out if every character in the string is a letter
        System.out.println(CharMatcher.JAVA_LETTER.matchesAllOf("Fahrenheit451"));  // false
        // count the number of letters in the string that are not 'e'
        System.out.println(CharMatcher.JAVA_LETTER.and(CharMatcher.isNot('e')).countIn("Fahrenheit451"));  // 8
        // replace a-g with *
        System.out.println(CharMatcher.anyOf("abcdefg").replaceFrom("Fahrenheit451", "*")); // F*hr*nh*it451
        // worried about performance?  Save your CharMatcher and compute it in advance.
        CharMatcher myCharMatcher = CharMatcher.inRange('a', 'z').and(CharMatcher.noneOf("aeiou")).precomputed();
        System.out.println(myCharMatcher.removeFrom("Fahrenheit451"));  // Faeei451
    }

    public static void escapingCharacters() {
        System.out.println(UrlEscapers.urlPathSegmentEscaper().escape("http://www.google.com?somevar=abc123"));  // http:%2F%2Fwww.google.com%3Fsomevar=abc123
        System.out.println(XmlEscapers.xmlAttributeEscaper().escape("<body>Don't forget me this weekend!</body>"));  // &lt;body&gt;Don&apos;t forget me this weekend!&lt;/body&gt;
        System.out.println(HtmlEscapers.htmlEscaper().escape("<html><p>Escape this</p></html>"));  // &lt;html&gt;&lt;p&gt;Escape this&lt;/p&gt;&lt;/html&gt;
        System.out.println(new CharEscaperBuilder().addEscape('-', "\\-").toEscaper().escape("never-the-less"));  // never\-the\-less
    }

    @SuppressWarnings("unused")
    public static void nullAndEmpty(String str) {
        if (Strings.isNullOrEmpty(str)) {
            throw new IllegalArgumentException("Invalid string!");
        }

        // can also do this
        String newStr = Strings.nullToEmpty(str); // returns the string if not null, otherwise empty
        // or this
        newStr = Strings.emptyToNull(str); // returns the string if not empty, otherwise NULL
    }

    public static void paddingRepeatingStrings() {
        String string = "Hello";
        System.out.println(Strings.padEnd(string, 20, '*'));    // Hello***************
        System.out.println(Strings.padStart(string, 20, '*'));  // ***************Hello
        System.out.println(Strings.repeat(string, 3));          // HelloHelloHello
    }
}
