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
    private static final String REGEX_DELIMITER = "[^A-Za-zА-Яа-я0-9 ]";
    private static final String EMPTY_SYM = "";
    private static final int MIN_WORD_LENGTH = 4;
    private static final int MIN_WORD_COUNT = 10;

    public String countWords(List<String> lines) {
        StringBuilder text = new StringBuilder();
        StringBuilder result = new StringBuilder();

        for (String line : lines) {
            text.append(line).append(" ");
        }

        Map<String, WordCounter> map = new HashMap<>();
        String[] strings = text.toString().replaceAll(REGEX_DELIMITER, EMPTY_SYM).split(TEXT_FILE_DELIMITER);
        for (String word : strings) {
            if (word.length() < MIN_WORD_LENGTH) {
                continue;
            }
            String lowerCaseWord = word.toLowerCase();
            map.put(lowerCaseWord, map.containsKey(lowerCaseWord)
                ? map.get(lowerCaseWord).addCount() : new WordCounter(lowerCaseWord));
        }

        List<WordCounter> list = new ArrayList<>(map.values());
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCount() > MIN_WORD_COUNT) {
                list.subList(i, list.size());
            }
        }
        for (WordCounter element : list) {
            result.append(element).append("\n");
            System.out.println(element);
        }
        return result.toString().stripTrailing();
    }
}
