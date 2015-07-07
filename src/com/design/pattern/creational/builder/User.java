package com.design.pattern.creational.builder;

public class User {
    // required parameters
    private final String firstName;
    private final String lastName;
    // optional parameters
    private final int age;
    private final String phone;
    private final String address;

    private User(UserBuilder builder) {
         this.firstName = builder.firstName;
         this.lastName = builder.lastName;
         this.age = builder.age;
         this.phone = builder.phone;
         this.address = builder.address;
    }

    public String getFirstName() {
         return firstName;
    }

    public String getLastName() {
         return lastName;
    }

    public int getAge() {
         return age;
    }

    public String getPhone() {
         return phone;
    }

    public String getAddress() {
         return address;
    }

    public static class UserBuilder {
         // required parameters
         private final String firstName;
         private final String lastName;
         // optional parameters
         private int age;
         private String phone;
         private String address;

         public UserBuilder(String firstName, String lastName) {
              this.firstName = firstName;
              this.lastName = lastName;
         }

         public UserBuilder age(int age) {
              this.age = age;
              return this;
         }

         public UserBuilder phone(String phone) {
              this.phone = phone;
              return this;
         }

         public UserBuilder address(String address) {
              this.address = address;
              return this;
         }

         public User build() {
              return new User(this);
         }
    }
}
