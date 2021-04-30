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

    private final List<Line> lines;

    public Shop(List<Line> lines) {
        Set<String> uniqueIds = lines.stream()
                .map(Line::getId)
                .collect(Collectors.toSet());

        if (lines.size() != uniqueIds.size()) {
            throw new IllegalArgumentException("Lines must have unique ids");
        }

        this.lines = Collections.unmodifiableList(lines);
    }

    public String acceptPerson() {
        Line quickestLine = lines.stream()
                .filter(Line::canAcceptPerson)
                .min(Comparator.comparing(Line::getExpectedWaitTime))
                .orElseThrow(() -> new RuntimeException("Cannot accept new person at this moment, all lines are full"));
        quickestLine.acceptPerson();
        return quickestLine.getId();
    }

    public void releasePerson(String lineId) {
        lines.stream()
                .filter(line -> line.getId().equals(lineId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Line with id: \"%s\" not found", lineId)))
                .releasePerson();
    }

    public List<Line> getLines() {
        return lines;
    }

}
