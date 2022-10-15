package com.github.mrsalt.ghostgame.player;

import com.github.mrsalt.ghostgame.model.ChallengeResponse;
import com.github.mrsalt.ghostgame.model.TurnResponse;

public interface Player {

    String getName();

    TurnResponse takeTurn(String currentWord, int playersAlive);

    ChallengeResponse clarifyChallenge(String word);
}
