
import entities.Agent;
import entities.Information;
import environnement.Grid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Stats;
import util.Tour;

import java.io.IOException;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

/**
 * La classe principale du jeu
 *
 */
public class Game {

    private static final Logger loggerGame = LoggerFactory.getLogger(Game.class);
    private static final Logger loggerGrid = LoggerFactory.getLogger("grid");
    public static final int GRID_SIZE = 20;
    public static final int NUMBER_OF_AGENTS = 20;
    public static final int NUMBER_OF_INFORMATIONS = 100;
    public static final int NUMBER_OF_ITERATIONS = 250;


    public static void main(String[] args) {

        loggerGame.info("Start of Simulation.");
        Grid grid = new Grid(GRID_SIZE);
        grid.initialiserGrid(NUMBER_OF_AGENTS, NUMBER_OF_INFORMATIONS);
        Agent[] agents = grid.getListeAgents();
        Information[] informations = grid.getListeInformations();

        /*État Initial*/
        loggerGrid.info(grid.buildGridString());

        for(int i =0;i<NUMBER_OF_ITERATIONS;++i){
            if(gameLoop(grid,agents)){
               break;
            };
        }
        endOfSimulation(agents);
    }

    private static boolean gameLoop(Grid grid,Agent[] agents){

        Tour.tourSuivant();
        for (Agent a : agents) {
            a.interactWithSurroundings(grid);
            a.move(grid);
            loggerGrid.info(grid.buildGridString());
            Tour.miniTourSuivant();
        }
        Tour.resetMiniTour();
        return Stats.generateStats(agents,NUMBER_OF_INFORMATIONS);

    }

    private static void endOfSimulation(Agent[] agents){
        for (Agent a : agents) {
            loggerGame.info("Agent #" + a.getIdAgent() + " has collected " + a.getInformationConnue().size() + " informations.");
        }
    }


}
