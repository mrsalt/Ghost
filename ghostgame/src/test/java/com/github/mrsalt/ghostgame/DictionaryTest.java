package com.github.mrsalt.ghostgame;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DictionaryTest {

    List<String> words = List.of("alpha", "beta", "chlorine", "doggy", "elephant", "fire", "fork", "fort", "foul", "found", "fox", "giraffe", "hippo", "iguana", "juniper", "koala", "llama", "monkey", "narwhal", "orangutang", "parrot", "quest", "racoon", "snake", "turtle");

    Dictionary dictionary = new Dictionary(words);

    @Test(expected = RuntimeException.class)
    public void checkWordsAreInOrderThrowsException() {
        Dictionary d = new Dictionary(List.of("Zebra", "Monkey", "Seahorse"));
        d.checkThatWordsAreInOrder();
    }

    @Test
    public void checkWordsAreInOrder() {
        dictionary.checkThatWordsAreInOrder();
    }

    @Test
    public void isFound() {
        // Test words in the dictionary
        for (String word : words)
            assertTrue(word + " was not found", dictionary.startsWithWord(word));

        assertTrue(dictionary.startsWithWord("mon"));

        // Test words not in the dictionary
        for (String word : List.of("milk", "cow"))
            assertFalse(word + " was found", dictionary.startsWithWord(word));
    }

    @Test
    public void isFound2() {
        // Same test as before, but add 1 more word to the dictionary.
        List<String> words2 = new ArrayList<>(words);
        words2.add("zebra");
        Dictionary dictionary2 = new Dictionary(words2);

        // Test words in the dictionary
        for (String word : words2)
            assertTrue(word + " was not found", dictionary2.startsWithWord(word));
        // Test words not in the dictionary
        for (String word : List.of("milk", "cow"))
            assertFalse(word + " was found", dictionary2.startsWithWord(word));
    }

    @Test
    public void testWordsBeginningWith() {
        assertEquals(List.of(), dictionary.wordsBeginningWith("fe"));
        assertEquals(List.of("fork", "fort", "foul", "found", "fox"), dictionary.wordsBeginningWith("fo"));
        assertEquals(List.of("fork", "fort"), dictionary.wordsBeginningWith("for"));
    }
}