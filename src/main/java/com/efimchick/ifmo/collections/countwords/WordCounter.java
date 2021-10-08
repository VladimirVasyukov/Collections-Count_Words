package com.efimchick.ifmo.collections.countwords;

public class WordCounter implements Comparable<WordCounter> {

    private final String value;
    private Integer count;

    public WordCounter(String value) {
        this.value = value;
        this.count = 1;
    }

    public String getValue() {
        return value;
    }

    public Integer getCount() {
        return count;
    }

    public WordCounter addCount() {
        count++;
        return this;
    }

    @Override
    public int compareTo(WordCounter o) {
        int compareCount = -(count.compareTo(o.getCount()));
        return compareCount == 0 ? value.compareTo(o.getValue()) : compareCount;
    }

    @Override
    public String toString() {
        return value + " - " + count;
    }
}
