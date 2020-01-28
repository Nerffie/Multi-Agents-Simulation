package util;

public  class Temps {

    static private int grandTour = 1;
    static private int petitTour = 1;

    public static void incGrandTour(){
        grandTour++;
    }

    public static void incPetitTour(){
        petitTour++;
    }

    public static int getGrandTour(){
        return grandTour;
    }

    public static int getPetitTour(){
        return petitTour;
    }
}
