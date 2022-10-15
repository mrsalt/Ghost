package com.github.mrsalt.ghostgame.model;

public class NewLetter extends TurnResponse {

    final char letter;

    public NewLetter(char letter)  {
        this.letter = Character.toLowerCase(letter);
    }

    public Character getLetter() {
        return this.letter;
    }

}
