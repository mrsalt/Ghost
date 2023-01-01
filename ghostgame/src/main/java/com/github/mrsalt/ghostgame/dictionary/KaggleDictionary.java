package com.github.mrsalt.ghostgame.dictionary;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KaggleDictionary extends Dictionary {

    public KaggleDictionary() {
        this(-1);
    }

    public KaggleDictionary(int vocabularySize) {
        super(loadTopWords(4, vocabularySize));
    }

    static List<String> loadTopWords(int minWordLength, int vocabularySize) {
        try (InputStream in = KaggleDictionary.class.getResourceAsStream("/kaggle/unigram_freq.csv")) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
                Iterable<CSVRecord> records = CSVFormat.Builder.create()
                        .setHeader().build().parse(reader);
                List<String> words = new ArrayList<>();
                for (CSVRecord record : records) {
                    String word = record.get(0);
                    if (word.length() < minWordLength)
                        continue;
                    words.add(word);
                    if (vocabularySize != -1 && words.size() >= vocabularySize)
                        break;
                }
                Collections.sort(words);
                return words;
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
