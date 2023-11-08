package fr.classement;
import clavier.In;

public class Ihm {
    static final int NC = 1, _40 = 2, _30_5 = 3, _30_4 = 4, _30_3 = 5, _30_2 = 6,
            _30_1 = 7, _30 = 8, _15_5 = 9, _15_4 = 10;
    static int choixAuDepart, choix, victories, victoriesDefault = 0, points = 0;
    static String nameRating = null;
    public static void main(String[] args) {
        choice();
        System.out.print("Entrez votre classement: ");
        choixAuDepart = In.readInteger();

        fillRating(choixAuDepart);
        fillVictories(choixAuDepart);

        System.out.println("Votre classement est: " + nameRating);
        System.out.println("Nombre de points au depart de votre classement est: " + points);
        System.out.println("Nombre de victoires par default est: " + victoriesDefault);

        System.out.print("Entrez votre nombre de victoires: ");
        victories = In.readInteger();


        for (int i = 0; i < victories; i++) {
            choice();
            System.out.print("Entrez le classement de votre victoire N°" + i + ": ");
            choix = In.readInteger();
            fillRating(choix);
            fillVictories(choix);
            if (choix >= choixAuDepart + 2) {
                points += 150;
            } else if (choix >= choixAuDepart + 1) {
                points += 100;
            }
            System.out.println("+" + points);
        }
    }
    public static void choice() {
        System.out.println("Entrez votre classement ...");
        System.out.println("1 - Non Classe");
        System.out.println("2 - 40");
        System.out.println("3 - 30/5");
        System.out.println("4 - 30/4");
        System.out.println("5 - 30/3");
        System.out.println("6 - 30/2");
        System.out.println("7 - 30/1");
        System.out.println("8 - 30");
        System.out.println("9 - 15/5");
        System.out.println("10 - 15/4");
    }

    public static void fillRating(int choix) {
        switch (choix) {
            case NC:
                nameRating = "Non Classe";
                points = 0;
                break;
            case _40:
                nameRating = "40";
                points = 2;
                break;
            case _30_5:
                nameRating = "30/5";
                points = 5;
                break;
            case _30_4:
                nameRating = "30/4";
                points = 10;
                break;
            case _30_3:
                nameRating = "30/3";
                points = 20;
                break;
            case _30_2:
                nameRating = "30/2";
                points = 30;
                break;
            case _30_1:
                nameRating = "30/1";
                points = 50;
                break;
            case _30:
                nameRating = "30";
                points = 80;
                break;
            case _15_5:
                nameRating = "15/5";
                points = 120;
                break;
            case _15_4:
                nameRating = "15/4";
                points = 160;
                break;
        }
    }
    public static void fillVictories(int choix) {
        switch (choix) {
            case NC:
            case _40:
            case _30_5:
            case _30_4:
                victoriesDefault = 5;
                break;
            case _30_3:
            case _30_2:
            case _30_1:
            case _30:
            case _15_5:
            case _15_4:
                victoriesDefault = 6;
                break;
        }
    }
}