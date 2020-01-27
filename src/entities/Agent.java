package entities;

import environnement.Grid;
import util.Aleatoire;

public abstract class Agent implements Entity {

    protected int idAgent;
    protected Information[] listeInformationsConnues;
    protected int x;
    protected int y;

    public Information[] getInformationConnue() {
        return listeInformationsConnues;
    }

    public void move(Grid grid) {
        boolean moved = false;
        int draw;
        while (!moved) {
            draw = (int) Aleatoire.getInstance().genererRandom(9);
            switch (draw) {
                case 0:
                    moved = tryMove(grid, -1, -1);
                    break;
                case 1:
                    moved = tryMove(grid, 0, -1);
                    break;
                case 2:
                    moved = tryMove(grid, 1, -1);
                    break;
                case 3:
                    moved = tryMove(grid, -1, 0);
                    break;
                case 4:
                    moved = tryMove(grid, 0, 0);
                    break;
                case 5:
                    moved = tryMove(grid, 1, 0);
                    break;
                case 6:
                    moved = tryMove(grid, -1, 1);
                    break;
                case 7:
                    moved = tryMove(grid, 0, 1);
                    break;
                case 8:
                    moved = tryMove(grid, 1, 1);
                    break;
            }
        }
    }

    private boolean tryMove(Grid grid, int deltaX, int deltaY) {
        try {
            if (grid.getCell(x + deltaX, y + deltaY) == null) {
                grid.setCell(x + deltaX, y + deltaY, this);
                grid.removeCell(x, y);
                x--;
                y--;
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            //e.printStackTrace();
            return false;
        }
        return false;
    }
}
