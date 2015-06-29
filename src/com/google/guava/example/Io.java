package com.google.guava.example;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.base.Throwables;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multiset;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.google.common.io.Files;
import com.google.common.io.Resources;

public class Io {

    // Similar methods for CharStreams
    @SuppressWarnings("unused")
    public static void usingByteStreams() throws IOException {
        byte[] array = new byte[100];
        for (int i = 0; i < 100; i++) {
            array[i] = (byte) (i);
        }
        ByteArrayInputStream in = new ByteArrayInputStream(array);
        // convert a byte stream to an array, does not close the stream
        byte[] b = ByteStreams.toByteArray(in);
        // read the byte stream to fill the byte array or EOF
        byte[] b1 = new byte [10];
        ByteStreams.readFully(in, b1);
        // skip the specified number of bytes in the byte stream
        ByteStreams.skipFully(in, 10);
        // create output stream that discards all output
        ByteStreams.nullOutputStream();

        // output byte data
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.write(array);
    }

    @SuppressWarnings("unused")
    public static void usingFilesAsSource() {
        String filename = "test.txt";
        String fileText = null;
        try {
            // read in the while file into one string
            fileText = Files.toString(new File(filename), Charsets.UTF_8);
            // read the file into a list of strings
            List<String> logLines = Files.readLines(new File(filename), Charsets.UTF_8);
            // Or...
            ImmutableList<String> lines = Files.asCharSource(new File(filename), Charsets.UTF_8).readLines();
            // read only the first line
            String firstLine = Files.readFirstLine(new File(filename), Charsets.UTF_8);

            // Count distinct word occurrences in a file
            Multiset<String> wordOccurrences = HashMultiset.create(
              Splitter.on(CharMatcher.WHITESPACE)
                .trimResults()
                .omitEmptyStrings()
                .split(Files.asCharSource(new File(filename), Charsets.UTF_8).read()));

            // SHA-1 a file
            HashCode hash = Files.asByteSource(new File(filename)).hash(Hashing.sha1());

        } catch (IOException e) {
            Throwables.propagate(e);
        }
    }

    public static void usingFilesAsSink() throws IOException {
        URL url = null;
        // Copy the data from a URL to a file
        Resources.asByteSource(url).copyTo(Files.asByteSink(new File("test.txt")));
    }

}
