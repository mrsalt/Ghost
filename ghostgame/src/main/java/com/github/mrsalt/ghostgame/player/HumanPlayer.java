package com.github.mrsalt.ghostgame.player;

import com.github.mrsalt.ghostgame.IOUtil;
import com.github.mrsalt.ghostgame.model.ChallengeResponse;
import com.github.mrsalt.ghostgame.model.ChallengeType;
import com.github.mrsalt.ghostgame.model.NewLetter;
import com.github.mrsalt.ghostgame.model.TurnResponse;

import java.util.Set;

public class HumanPlayer implements Player {

    final String name;
    final IOUtil ioUtil;

    public HumanPlayer(String name, IOUtil ioUtil) {
        this.name = name;
        this.ioUtil = ioUtil; 
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public TurnResponse takeTurn(String currentWord, int playersAlive) {
        ioUtil.write(name + ", it's your turn.");


        if (!currentWord.isEmpty()) {
            ioUtil.write("The word is currently \"" + currentWord + "\".");
            ioUtil.write("Do you want to challenge or add a new letter? (c = challenge, n = new letter)");
            String response = ioUtil.readInput(Set.of("c", "n"));
            if (response.equals("c"))
                return new ChallengeResponse(ChallengeType.WORD_DOES_NOT_EXIST);
        }

        while (true) {
            if (currentWord.isEmpty())
                ioUtil.write("What letter do you want to start with?");
            else
                ioUtil.write("What letter do you want to add?");
            String letter = ioUtil.readLine();
            if (letter.length() != 1) {
                ioUtil.write("Oops.  Just enter a single letter please.");
                continue;
            }
            if (ioUtil.prompt("Is " + letter + " correct?"))
                return new NewLetter(letter.charAt(0));
        }
    }

    @Override
    public ChallengeResponse clarifyChallenge(String word) {
        return new ChallengeResponse(ChallengeType.WORD_DOES_NOT_EXIST);
    }

}
