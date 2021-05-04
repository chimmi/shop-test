package ru.example;

import ru.example.shop.CheckoutLine;
import ru.example.shop.Shop;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<CheckoutLine> checkoutLines = Arrays.asList(
                new CheckoutLine("1", 10),
                new CheckoutLine("2", 13),
                new CheckoutLine("3", 15),
                new CheckoutLine("4", 17)
        );

        Shop shop = new Shop(checkoutLines);

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
