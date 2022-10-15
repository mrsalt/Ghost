package com.github.mrsalt.ghostgame;

import com.github.mrsalt.ghostgame.player.HumanPlayer;
import com.github.mrsalt.ghostgame.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ConsoleGameSetup {

    final IOUtil ioUtil;

    public ConsoleGameSetup(IOUtil ioUtil) {
        this.ioUtil = ioUtil;
    }

    public Game setup() {
        List<Player> players = new ArrayList<>();
        while(true) {
            players.add(addPlayer());
            if (players.size() >= 2 && ioUtil.prompt("Do you want another haunted soul to join the game?") == false) {
                break;
            }
        }
        ioUtil.write("How many lives should each player have?");
        String lives = ioUtil.readInput(Set.of("1","2","3","4","5"));
        return new Game(ioUtil, players, Integer.parseInt(lives), 3);
    }

    Player addPlayer() {
        while (true) {
            ioUtil.write("What is this player's name?");
            String name = ioUtil.readLine();
            if (ioUtil.prompt("Is \"" + name + "\" right?"))
                return new HumanPlayer(name, ioUtil);
        }
    }

    public static void main(String[] args) {
        Game game = new ConsoleGameSetup(new IOUtil()).setup();
        game.playGame();
    }

}
