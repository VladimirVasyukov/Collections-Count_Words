package com.efimchick.ifmo.collections.countwords;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 07.2_Generics and Collections - Vladimir Vasyukov
 * Class for working with words
 */

public class Words {
    private static final String TEXT_FILE_DELIMITER = "\\s";
    private static final String REGEX_DELIMITER =
        "([\\u0000-\\u0040\\u005B-\\u0060\\u007B-\\u00BF\\u02B0-\\u036F\\u00D7\\u00F7\\u2000-\\u2BFF])+";
    private static final String EMPTY_SYM = " ";
    private static final int MIN_WORD_LENGTH = 4;
    private static final int MIN_WORD_COUNT = 10;

    public String countWords(List<String> lines) {
        Map<String, WordCounter> map = new HashMap<>();
        String[] strings = lines.toString().replaceAll(REGEX_DELIMITER, EMPTY_SYM).split(TEXT_FILE_DELIMITER);
        for (String word : strings) {
            String lowerCaseWord = word.toLowerCase();
            if (map.containsKey(lowerCaseWord)) {
                map.get(lowerCaseWord).incrementCount();
            } else {
                map.put(lowerCaseWord, new WordCounter(lowerCaseWord));
            }
        }
        List<WordCounter> list = new ArrayList<>(map.values());
        Collections.sort(list);

        return applyFilters(list);
    }

    public String applyFilters(List<WordCounter> list) {
        StringBuilder result = new StringBuilder();
        for (WordCounter word : list) {
            if (word.getCount() >= MIN_WORD_COUNT && word.getValue().length() >= MIN_WORD_LENGTH) {
                result.append(word).append("\n");
            }
        }
        return result.toString().strip();
    }
}
