package com.github.mrsalt.ghostgame;

import com.github.mrsalt.ghostgame.model.ChallengeResponse;
import com.github.mrsalt.ghostgame.model.NewLetter;
import com.github.mrsalt.ghostgame.model.TurnResponse;
import com.github.mrsalt.ghostgame.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Game {

    final List<GamePlayer> players;
    final int minLetters;
    int playersAlive;
    IOUtil ioUtil;
    Dictionary dictionary = new Dictionary(List.of("pineapple", "waffle", "royal"));

    static class GamePlayer implements Player {
        public Player player;
        int lifeCount;

        GamePlayer(Player player, int lifeCount) {
            this.player = player;
            this.lifeCount = lifeCount;
        }

        boolean isAlive() {
            return lifeCount > 0;
        }

        void loseLife() {
            lifeCount--;
        }

        @Override
        public String getName() {
            return player.getName();
        }

        @Override
        public TurnResponse takeTurn(String currentWord, int playersAlive) {
            return player.takeTurn(currentWord, playersAlive);
        }

        @Override
        public ChallengeResponse clarifyChallenge(String word) {
            return player.clarifyChallenge(word);
        }
    }

    public Game(IOUtil ioUtil, List<Player> players, int lifeCount, int minLetters) {
        this.ioUtil = ioUtil;
        this.players = new ArrayList<>();
        for (Player player : players) {
            this.players.add(new GamePlayer(player, lifeCount));
        }
        playersAlive=players.size();
        this.minLetters = minLetters;
    }

    public void playGame() {
        String currentWord="";
        GamePlayer previousPlayer = null;
        while (playersAlive > 1) {
            for (int i = 0; i < players.size(); i++) {
                GamePlayer player = players.get(i);
                if (!player.isAlive())
                    continue;
                TurnResponse action = player.takeTurn(currentWord, playersAlive);
                if (action instanceof ChallengeResponse) {
                    GamePlayer whoLoses;
                    int count = dictionary.countWordsBeginningWith(currentWord);
                    if (count > 0) {
                        ioUtil.write("Sorry, " + player.getName() + ", there " + (count == 1 ? "is" : "are") + " " + count + " word" + (count == 1 ? "": "s") + " beginning with " + currentWord + " found in the dictionary.");
                        whoLoses = player;
                    }
                    else {
                        ioUtil.write("Sorry, " + previousPlayer.getName() + ", no words beginning with \"" + currentWord + "\" are found in the dictionary.");
                        whoLoses = previousPlayer;
                    }
                    loseLife(whoLoses);
                    currentWord = "";
                }
                else if (action instanceof NewLetter) {
                    currentWord += ((NewLetter) action).getLetter();
                    if (currentWord.length() > minLetters && dictionary.isExactWordFound(currentWord)){
                        loseLife(player);
                    }
                }
                previousPlayer = player;
            }
        }
        for (GamePlayer player: players) {
            if (player.isAlive()) {
                ioUtil.write(player.getName() + " is the sole survivor. *Moan*");
                break;
            }
        }
    }

    private void loseLife(GamePlayer whoLoses) {
        whoLoses.loseLife();
        if (!whoLoses.isAlive()) {
            playersAlive--;
            ioUtil.write(whoLoses.getName() + " lost 1 life and is now a ghost.");
        }
        else {
            ioUtil.write(whoLoses.getName() + " lost 1 life and now has " + whoLoses.lifeCount + " lives remaining.");
        }
    }

}