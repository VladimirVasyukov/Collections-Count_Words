package com.efimchick.ifmo.collections.countwords;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 07.2_Generics and Collections - Vladimir Vasyukov
 * Class for working with words
 */

public class Words {
    private static final String REGEX_PATTERN = "(\\p{L}{4,})";
    private static final Pattern PATTERN = Pattern.compile(REGEX_PATTERN);
    private static final int MIN_WORD_LENGTH = 4;
    private static final int MIN_WORD_COUNT = 10;

    public String countWords(List<String> lines) {
        Map<String, WordStore> map = new HashMap<>();
        List<String> wordsList = getWordsFromText(lines);
        for (String word : wordsList) {
            String lowerCaseWord = word.toLowerCase(Locale.ENGLISH);
            if (map.containsKey(lowerCaseWord)) {
                map.get(lowerCaseWord).incrementCount();
            } else {
                map.put(lowerCaseWord, new WordStore(lowerCaseWord));
            }
        }
        List<WordStore> list = new ArrayList<>(map.values());
        Collections.sort(list);

        return applyFilters(list);
    }

    private List<String> getWordsFromText(List<String> lines) {
        List<String> wordsList = new ArrayList<>();
        Matcher matcher = PATTERN.matcher(lines.toString());
        while (matcher.find()) {
            String word = matcher.group(0);
            wordsList.add(word);
        }
        return wordsList;
    }

    public String applyFilters(Iterable<WordStore> list) {
        StringBuilder result = new StringBuilder();
        for (WordStore word : list) {
            if (word.getCount() >= MIN_WORD_COUNT && word.getValue().length() >= MIN_WORD_LENGTH) {
                result.append(word).append("\n");
            }
        }
        return result.toString().strip();
    }
}
