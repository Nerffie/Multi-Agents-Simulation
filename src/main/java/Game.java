import entities.Agent;
import entities.Information;
import environnement.Grid;
import util.Temps;

public class Game {

    public static void main(String[] args) {
        Grid grid = new Grid(5);

        grid.initialiserGrid(8, 3);

        Agent[] agents = grid.getListeAgents();
        Information[] informations = grid.getListeInformations();

        grid.afficherGrid();

        for (Agent a : agents) {
            a.interactWithSurroundings(grid);
            a.move(grid);
            System.out.println("*****************");
            grid.afficherGrid();
            Temps.incPetitTour();
        }

        for(Agent a : agents){
            System.out.println("Agent #"+a.getIdAgent()+" has collected "+a.getInformationConnue().size()+" informations.");
        }
    }
}
