package org.ipi.battleships;

import org.ipi.battleships.core.Game;
import org.ipi.battleships.core.player.ConsolePlayer;

public class Application {

    public static void main(String... args) {
        ConsolePlayer player = new ConsolePlayer("Dear player");
        Game game = new Game(player);
        game.play();
    }

}
