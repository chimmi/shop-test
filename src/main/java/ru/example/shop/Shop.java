package ru.example.shop;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Not Thread safe
 */
public class Shop {

    private final List<CheckoutLine> checkoutLines;

    public Shop(List<CheckoutLine> checkoutLines) {
        Set<String> uniqueIds = checkoutLines.stream()
                .map(CheckoutLine::getId)
                .collect(Collectors.toSet());

        if (checkoutLines.size() != uniqueIds.size()) {
            throw new IllegalArgumentException("Checkout lines must have unique ids");
        }

        this.checkoutLines = Collections.unmodifiableList(checkoutLines);
    }

    public String acceptPerson() {
        CheckoutLine quickestLine = checkoutLines.stream()
                .filter(CheckoutLine::canAcceptPerson)
                .min(Comparator.comparing(CheckoutLine::getExpectedWaitTime))
                .orElseThrow(() -> new IllegalStateException("Cannot accept new person at this moment"));
        quickestLine.acceptPerson();
        return quickestLine.getId();
    }

    public void releasePerson(String lineId) {
        checkoutLines.stream()
                .filter(line -> line.getId().equals(lineId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Line with id: \"%s\" not found", lineId)))
                .releasePerson();
    }

    public List<CheckoutLine> getCheckoutLines() {
        return checkoutLines;
    }

}
