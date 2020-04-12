package util;

public class Tour {
    private static int tour = 0;
    private static int miniTour = 1;

    public static void tourSuivant(){
        tour++;
    }
    public static int getTour(){
        return tour;
    }

    public static void miniTourSuivant(){
        miniTour++;
    }

    public static void resetMiniTour(){
        miniTour=1;
    }

    public static int getMiniTour(){
        return miniTour;
    }
}
