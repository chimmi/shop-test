package ru.example;

import ru.example.shop.Line;
import ru.example.shop.Shop;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Line> lines = Arrays.asList(
                new Line("1", 10),
                new Line("2", 13),
                new Line("3", 15),
                new Line("4", 17)
        );

        Shop shop = new Shop(lines);

        List<String> sampleInput = Arrays.asList("A", "A", "A", "A", "A");
        List<String> expectedOutput = Arrays.asList("4", "3", "2", "1", "4");

        List<String> output = sampleInput.stream().map(action -> {
            String result = "";
            if ("A".equals(action)) {
                result = shop.acceptPerson();
            } else {
                shop.releasePerson(action);
            }
            System.out.println(action + " -> " + result);
            return result;
        }).collect(Collectors.toList());

        if (String.join("", expectedOutput).equals(String.join("", output))) {
            System.out.println("Outputs match");
        } else {
            System.err.println("Outputs does not match");
        }
    }

}
