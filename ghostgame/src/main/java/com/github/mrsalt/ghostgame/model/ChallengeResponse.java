package com.github.mrsalt.ghostgame.model;

public class ChallengeResponse extends TurnResponse {

    public final ChallengeType type;
    public ChallengeResponse(ChallengeType type) {
        this.type = type;
    }
}
