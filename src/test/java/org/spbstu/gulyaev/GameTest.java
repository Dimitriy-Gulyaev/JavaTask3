package org.spbstu.gulyaev;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameTest {

    private Game game = new Game();

    @BeforeEach
    public void gen(){
        game = new Game();
    }

    @Test
    public void move() {
        Assertions.assertTrue(game.move(3,5,4,4));
        Assertions.assertEquals(0, game.getDeck().getCurChip(3,5));
        Assertions.assertEquals(1, game.getDeck().getCurChip(4,4));

        Assertions.assertTrue(game.move(2,2,3,3));
        Assertions.assertEquals(0, game.getDeck().getCurChip(2,2));
        Assertions.assertEquals(-1, game.getDeck().getCurChip(3,3));

        Assertions.assertTrue(game.move(4,4,2,2));
        Assertions.assertEquals(0, game.getDeck().getCurChip(4,4));
        Assertions.assertEquals(0, game.getDeck().getCurChip(3,3));
        Assertions.assertEquals(1, game.getDeck().getCurChip(2,2));
        game.finishMove();

        Assertions.assertTrue(game.move(3,1,1,3));
        Assertions.assertEquals(0, game.getDeck().getCurChip(3,1));

        game.debugDelete(6,0);
        game.debugMove(3,7, 7,1);
        game.finishMove();

        Assertions.assertTrue(game.move(7, 1, 6, 0));
        Assertions.assertEquals(0, game.getDeck().getCurChip(7,1));
        Assertions.assertEquals(2, game.getDeck().getCurChip(6,0));
        game.finishMove();

        Assertions.assertTrue(game.move(6, 0, 3, 3));
        Assertions.assertEquals(0, game.getDeck().getCurChip(5,1));
        Assertions.assertEquals(0, game.getDeck().getCurChip(4,2));
        Assertions.assertEquals(2, game.getDeck().getCurChip(3,3));
    }
}