package com.geektrust.familytree.entity;



public class Person {

    private String name;
    private Gender gender;
    private Person mother;

    public Person(String name, String gender, Person mother) {
        this.name = name;
        this.gender = Gender.getGender(gender);
        this.mother = mother;
    }

    public String getName() {
        return name;
    }

    public boolean isMale() {
        return this.gender == Gender.MALE;
    }

    public Person getMother() {
        return mother;
    }

    public String toString() {
        return name;
    }

    public boolean equals(Object object) {
        if(object instanceof Person) {
            Person person = (Person) object;
            return person.name.equals(this.name);
        }
        return false;
    }

    public int hashCode() {
        return 31 * this.name.hashCode();
    }

    public enum Gender {
        MALE, FEMALE;

        public static Gender getGender(String gender) {
            switch(gender.toLowerCase()) {
                case "male":
                    return MALE;
                case "female":
                    return FEMALE;
                default:
                    return MALE;
            }
        }
    }
}
