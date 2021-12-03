package com.efimchick.ifmo.collections.countwords;

import java.util.Objects;

public class WordStore implements Comparable<WordStore> {

    private final String value;
    private Integer count;

    public WordStore(String value) {
        this.value = value;
        this.count = 1;
    }

    public String getValue() {
        return value;
    }

    public Integer getCount() {
        return count;
    }

    public final void incrementCount() {
        count++;
    }

    @Override
    public int compareTo(WordStore o) {
        int compareCount = o.getCount().compareTo(count);
        if (compareCount == 0) {
            compareCount = value.compareTo(o.getValue());
        }
        return compareCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WordStore wordStore = (WordStore) o;
        return Objects.equals(value, wordStore.value) && Objects.equals(count, wordStore.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, count);
    }

    @Override
    public String toString() {
        return value + " - " + count;
    }
}
