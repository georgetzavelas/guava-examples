package com.google.guava.example;

import com.google.common.base.Charsets;
import com.google.common.hash.Funnel;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.google.common.hash.PrimitiveSink;

public class Hash {

    @SuppressWarnings({ "serial", "unused" })
    public void usingHashing() {
        int id = 0;
        Person person = new Person();
        // a funnel describes how to decompose an object into primitive field values
        Funnel<Person> personFunnel = new Funnel<Person>() {
            @Override
            public void funnel(Person person, PrimitiveSink into) {
              into
                .putInt(person.id)
                .putString(person.firstName, Charsets.UTF_8)
                .putString(person.lastName, Charsets.UTF_8)
                .putInt(person.birthYear);
            }
        };

        // hashing: md5, adler32, crc32, sha1, sha256, sha512, murmur3_128
        HashFunction hf = Hashing.murmur3_128();
        HashCode hc = hf.newHasher()
               .putLong(id)
               .putObject(person, personFunnel)
               .hash();
    }

    class Person {
        public int id;
        public String firstName;
        public String lastName;
        public int birthYear;
    }
}
