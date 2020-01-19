package pl.sdacademy.intermediate.basic.basic9;

import pl.sdacademy.intermediate.warmup.warmup1.Person;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Piotr Zietek
 */
public class Basic9Streams {

    public static void main(String[] args) {
        Stream<String> namesStream = Stream.of("John", "Marry", "George", "Paul", "Alice", "Ann");
        System.out.println("--------- Printing upper-case names starting with A (stream created with Stream.of())");
        namesStream
                .filter(e -> e.startsWith("A"))
                .map(e -> e.toUpperCase())
                .sorted()
                .forEach(System.out::println);

        List<String> namesList = Arrays.asList("John", "Marry", "George", "Paul", "Alice", "Ann");
        System.out.println("--------- Printing upper-case names starting with A (stream created from list)");
        namesList
                .stream()
                .filter(name -> name.startsWith("A"))
                .map(name -> name.toUpperCase())
                .sorted()
                .forEach(System.out::println);

        System.out.println("--------- Nothing happens (no terminal operation)");
        namesList.stream()
                 .filter(name -> {
                     System.out.println("Filtering: " + name);
                     return true;
                 });

        System.out.println("--------- Printing 2 lines for each name (added terminal operation forEach)");
        namesList.stream()
                 .filter(name -> {
                     System.out.println("Filtering: " + name);
                     return true;
                 })
                 .forEach(name -> System.out.println("Name: " + name));

        System.out.println(
                "--------- Trying to see IllegalStateException (terminal operation used 2 times on the same stream)");
        Stream<Integer> numbers = Stream.of(1, 2, 3).filter(number -> number > 1);
        numbers.forEach(System.out::println);
        System.out.println("---");
        //numbers.forEach(System.out::println);

        System.out.println("--------- Printing info about adult persons");
        Person person1 = new Person("Piotr", "Zietek", "Zgierz", 28, 793522944);
        Person person2 = new Person("Adam", "Malysz", "Wisla", 42, 123456789);
        Person person3 = new Person("Kasia", "Kowalska", "Warszawa", 14, 111222333);

        List<Person> allPersons = Arrays.asList(person1, person2, person3);
        allPersons
                .stream()
                .filter(person -> person.getAge() >= 18)
                .forEach(person -> System.out.println("Person: " + person.getName() + " is adult."));

        System.out.println("--------- Printing info about adult persons mapped to names");
        allPersons
                .stream()
                .filter(person -> person.getAge() >= 18)
                .map(person -> person.getName())
                .forEach(name -> System.out.println("Person: " + name + " is adult."));

        System.out.println("--------- Printing greeting of a person using skip operation");
        allPersons
                .stream()
                .skip(1)
                .forEach(person -> System.out.println("Hello, " + person.getName()));

        System.out.println("--------- Printing greeting of a person using limit operation");
        allPersons
                .stream()
                .limit(2)
                .forEach(person -> System.out.println("Hello, " + person.getName()));

        System.out.println("--------- Sorting people names in different ways using sorted operation");
        System.out.println("--- alphabetical order");
        allPersons
                .stream()
                .map(Person::getName)
                .sorted()
                .forEach(name -> System.out.println("Hello, " + name));
        System.out.println("--- reverse alphabetical order");
        allPersons
                .stream()
                .map(Person::getName)
                .sorted(Comparator.reverseOrder())
                .forEach(name -> System.out.println("Hello, " + name));
        System.out.println("--- from youngest person");
        allPersons
                .stream()
                .sorted(Comparator.comparingInt(Person::getAge))
                .forEach(person -> System.out.println("Hello, " + person.getName()));
        System.out.println("--- from oldest person");
        allPersons
                .stream()
                .sorted(Comparator.comparingInt(Person::getAge).reversed())
                .forEach(person -> System.out.println("Hello, " + person.getName()));
        System.out.println("--- alphabetical order, different approach");
        allPersons
                .stream()
                .sorted(Comparator.comparing(Person::getName))
                .forEach(person -> System.out.println("Hello, " + person.getName()));
        System.out.println("--- alphabetical order, different approach (IntelliJ complains)");
        allPersons
                .stream()
                .sorted((o1, o2) -> o1.getName().compareTo(o2.getName()))
                .forEach(person -> System.out.println("Hello, " + person.getName()));

        System.out.println("--------- Using flatMap operation on stream of lists");
        List<Integer> firstList = Arrays.asList(1, 2, 3);
        List<Integer> secondList = Arrays.asList(4, 5, 6);
        List<Integer> thirdList = Arrays.asList(7, 8, 9);

        Stream<List<Integer>> streamOfLists = Stream.of(firstList, secondList, thirdList);
        streamOfLists
                .flatMap(Collection::stream)
                .forEach(System.out::println);

        System.out.println("--------- Using Collectors.toList to create a new list from filtered stream of persons");
        List<Person> adultsOnly = allPersons
                .stream()
                .filter(person -> person.getAge() >= 18)
                .peek(person -> System.out.println("City: " + person.getPlaceOfBirth())) // peek operatin in use!
                .filter(person -> person.getPlaceOfBirth().equals("Zgierz")) // more than 1 filter is possible!
                .collect(Collectors.toList());
        System.out.println("Adults only: " + adultsOnly);

        System.out.println("--------- Using Collectors.joining to create a string from people names joined with hyphen");
        String peopleNamesJoinedByHyphen = allPersons.stream()
                                                     .map(Person::getName)
                                                     .collect(Collectors.joining(" - "));
        System.out.println("Joined string: " + peopleNamesJoinedByHyphen);

        System.out.println("--------- Using peek operation (which is intermediate!) to look at the element and do sth");
        allPersons
                .stream()
                .filter(person -> person.getAge() >= 18)
                .peek(person -> System.out
                        .println("Peeking person to show place of birth: " + person.getPlaceOfBirth()))
                .forEach(System.out::println);

        System.out.println("--------- Using anyMatch operation");
        boolean isAdultPersonPhoneDividableBy2 = allPersons
                .stream()
                .filter(person -> person.getAge() >= 18)
                .anyMatch(person -> person.getPhone() % 2 == 0);
        System.out.println("Is there any person older than 18, which has phone number dividable by 2? "
                                   + isAdultPersonPhoneDividableBy2);

        boolean isAdultPersonPhoneDividableBy5 = allPersons
                .stream()
                .filter(person -> person.getAge() >= 18)
                .anyMatch(person -> person.getPhone() % 5 == 0);
        System.out.println("Is there any person older than 18, which has phone number dividable by 5? "
                                   + isAdultPersonPhoneDividableBy5);

    }

}
