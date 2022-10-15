package com.github.mrsalt.ghostgame;

import java.util.Set;

public class IOUtil {

    public void write(String message) {
        System.out.println(message);
    }

    public String readLine() {
        return System.console().readLine();
    }

    public String readInput(Set<String> validChoices) {
        while (true) {
            String response = System.console().readLine();
            if (validChoices.contains(response))
                return response;
            write("Sorry, \"" + response + "\" is not a valid response.  You can enter one of: " + validChoices);
        }
    }

    public boolean prompt(String text) {
        write(text + " (y/n)");
        return readInput(Set.of("y", "yes", "n", "no")).startsWith("y");
    }
}
