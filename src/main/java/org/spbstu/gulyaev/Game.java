package org.spbstu.gulyaev;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Game {
    private Deck d = new Deck();

    private int player = 1;

    private boolean isGame = true;

    private int black = 12;
    private int white = 12;

    private boolean moveOrdinar(int x1, int y1, int x2, int y2) {
        if (Math.abs(x2 - x1) == 1 && d.getCurChip(x2, y2) == 0) {
            d.moveChip(x1, y1, x2, y2);
            finishMove();
            return true;
        } else if (Math.abs(x2 - x1) % 2 == 0 && d.getCurChip(x2, y2) == 0) {
            int dx = (x2 - x1) / 2;
            int dy = (y2 - y1) / 2;
            if (d.getCurChip(x1 + dx, y1 + dy) == player * -1) {
                d.delete(x1 + dx, y1 + dy);
                d.moveChip(x1, y1, x2, y2);
                if(player == 1) black--;
                else white--;
                return true;
            }
        }
        return false;
    }

    public boolean move(int x1, int y1, LinkedHashMap<Integer, Integer> moves) {
        for(Map.Entry<Integer, Integer> entry : moves.entrySet()) {
            if(!move(x1,y1,entry.getKey(),entry.getValue())) return false;
            x1 = entry.getKey();
            y1 = entry.getValue();
        }
        finishMove();
        return true;
    }

    public boolean move(int x1, int y1, int x2, int y2) {
        if (Math.abs(x2 - x1) == Math.abs(y2 - y1) &&
                (player == d.getCurChip(x1, y1) || player * 2 == d.getCurChip(x1, y1))) {
            int del = Math.abs(y2 - y1) == 1 ? y2 - y1 : Math.abs(y2 - y1) == 2 ? (y2 - y1)/2 : -13;
            if (d.getCurChip(x1, y1) == player && del != -13)
                return moveOrdinar(x1, y1, x2, y2);
            else if (d.getCurChip(x1, y1) == player * 2) {
                return moveUp(x1, y1, x2, y2);
            }
        }
        return false;
    }

    private boolean moveUp(int x1, int y1, int x2, int y2) {
        if (d.getCurChip(x2, y2) == 0) {
            int dx = x2 - x1 > 0 ? 1 : -1;
            int dy = y2 - y1 > 0 ? 1 : -1;
            int tx = x1 + dx;
            int ty = y1 + dy;
            ArrayList<Integer> ax = new ArrayList<Integer>();
            ArrayList<Integer> ay = new ArrayList<Integer>();
            while (tx != x2 && ty != y2){
                if(d.getCurChip(tx,ty)== player * -1) {
                    ax.add(tx);
                    ay.add(ty);
                }
                tx += dx;
                ty += dy;
                if (d.getCurChip(tx,ty)== player) return false;
            }
            for (int i = 0; i < ax.size(); i++)
                d.delete(ax.get(i),ay.get(i));
            d.moveChip(x1, y1, x2, y2);
            finishMove();
            return true;
        }
        return false;
    }

    public void finishMove() {
        player *= -1;
    }

    public int getPlayer() {
        return player;
    }

    public Deck getDeck() {
        return d;
    }

    //@TODO: DEBUG ONLY

    protected void debugMove(int x1, int y1, int x2, int y2){
        d.moveChip(x1, y1, x2, y2);
    }

    protected void debugDelete(int x, int y){
        d.delete(x, y);
    }
}
