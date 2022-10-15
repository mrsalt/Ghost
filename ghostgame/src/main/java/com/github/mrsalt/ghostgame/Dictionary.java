package com.github.mrsalt.ghostgame;

import java.util.ArrayList;
import java.util.List;

public class Dictionary {
    final List<String> words;

    public Dictionary(List<String> words) {
        this.words = words;
    }

    public List<String> getWords() {
        return this.words;
    }

    public void checkThatWordsAreInOrder() {
        String previousWord = null;
        for (String word : words) {
            if (previousWord != null && previousWord.compareTo(word) >= 0)
                throw new RuntimeException("Error: " + previousWord + " does not come before " + word);
            previousWord = word;
        }
    }

    public boolean startsWithWord(String word) {
        return isContained(word, false);
    }

    public int countWordsBeginningWith(String word) {
        int pos = firstIndexOf(word);
        if (pos == -1) return 0;
        for (int i = pos; i < words.size(); i++) {
            if (makeComparison(word, false, words.get(i)) != 0) {
                return i - pos;
            }
        }
        return words.size() - pos;
    }

    public List<String> wordsBeginningWith(String word) {
        List<String> list = new ArrayList<>();
        int pos = firstIndexOf(word);
        if (pos == -1) return list;
        for (int i = pos; i < words.size(); i++) {
            if (makeComparison(word, false, words.get(i)) != 0) {
                break;
            }
            list.add(words.get(i));
        }
        return list;
    }

    public boolean isExactWordFound(String word) {
        return isContained(word, true);
    }

    boolean isContained(String word, boolean exactMatchRequired) {
        int start = 0;
        int end = words.size();
        int mid;
        while (start < end) {
            mid = (start + end) / 2;
            String middleWord = words.get(mid);
            int comparison = makeComparison(word, exactMatchRequired, middleWord);
            if (comparison < 0) {
                end = mid;
            }
            else if (comparison > 0) {
                start = mid + 1;
            }
            else {
                return true;
            }
        }
        return false;
    }

    int firstIndexOf(String word) {
        int start = 0;
        int end = words.size();
        int mid;
        while (start < end) {
            mid = (start + end) / 2;
            String middleWord = words.get(mid);
            int comparison = makeComparison(word, false, middleWord);
            if (comparison < 0) {
                end = mid;
            }
            else if (comparison > 0) {
                start = mid + 1;
            }
            else {
                for (int i = mid; i >=0; i--) {
                     if (makeComparison(word, false, words.get(i)) != 0) {
                         return i + 1;
                     }
                }
                return 0;
            }
        }
        return -1;
    }

    // 'show'
    //
    // shoot
    // short
    // show
    // shown <--
    //

    private int makeComparison(String word, boolean exactMatchRequired, String toCompareWith) {
        if (exactMatchRequired) {
            return word.compareTo(toCompareWith);
        } else {
            int lettersToCompare = word.length();
            if (toCompareWith.length() < lettersToCompare)
                return word.compareTo(toCompareWith);
            else
                return word.compareTo(toCompareWith.substring(0, lettersToCompare));
        }
    }
}
