package fr.classement;
import clavier.In;

public class Ihm {
    static final int NC = 1, _40 = 2, _30_5 = 3, _30_4 = 4, _30_3 = 5, _30_2 = 6,
            _30_1 = 7, _30 = 8, _15_5 = 9, _15_4 = 10;
    static int choiceRatingInitial, choiceRating, choiceRatingFinal, victories,
            victoriesDefault = 0, victoriesBonus = 0, victoriesBonusFormula = 0, pointsChangeable = 0, pointsInitial = 0,
            numberOfLifts = 0, defeats, equal = 0, lower = 0, large = 0;
    static String nameRating = null;
    public static void main(String[] args) {
        choice();
        System.out.print("Entrez votre classement: ");
        choiceRatingInitial = In.readInteger();

        fillRating(choiceRatingInitial);
        fillVictories(choiceRatingInitial);

        System.out.println("Votre classement est: " + nameRating);
        System.out.println("Nombre de points au depart de votre classement est: " + pointsChangeable);
        System.out.println("Nombre de victoires par default est: " + victoriesDefault);

        System.out.print("Entrez votre nombre de victoires: ");
        victories = In.readInteger();

        if (victories != 0) {
            System.out.print("Entrez votre nombre de defaites: ");
            defeats = In.readInteger();

            for (int i = 0; i < defeats; i++) {
                choice();
                System.out.print("Entrez le classement de votre defaite №" + (i + 1) + ": ");
                choiceRating = In.readInteger();

                if (choiceRating == choiceRatingInitial) {
                    equal++;
                } else if (choiceRating <= choiceRatingInitial - 2) {
                    large++;
                } else if (choiceRating <= choiceRatingInitial - 1) {
                    lower++;
                }
            }
        }
        victoriesBonusFormula = victories - equal - (2 * lower) - (5 * large);

        if (victoriesDefault == 5) {
            if (victories > 5) {
                victories = 5;
            }
        } else if (victoriesDefault == 6) {
            if (victories > 6) {
                victories = 6;
            }
        }

        if (victoriesBonusFormula >= 25) {
            victoriesBonus = 6;
            victories += victoriesBonus;
        } else if (victoriesBonusFormula >= 20) {
            victoriesBonus = 5;
            victories += victoriesBonus;
        } else if (victoriesBonusFormula >= 15) {
            victoriesBonus = 4;
            victories += victoriesBonus;
        } else if (victoriesBonusFormula >= 10) {
            victoriesBonus = 3;
            victories += victoriesBonus;
        } else if (victoriesBonusFormula >= 5) {
            victoriesBonus = 2;
            victories += victoriesBonus;
        } else if (victoriesBonusFormula > 0) {
            victoriesBonus = 1;
            victories += victoriesBonus;
        }

        System.out.println("\nVous avez " + victoriesBonus + " victoires supplementaires");
        System.out.println("Le nombre de vos victoires qui sont prises en compte est: " + victories);

        for (int i = 0; i < victories; i++) {
            choice();
            System.out.print("Entrez le classement de votre victoire N°" + (i + 1) + ": ");
            choiceRating = In.readInteger();
            if (choiceRating >= choiceRatingInitial + 2) {
                System.out.println("+150");
                pointsChangeable += 150;
            } else if (choiceRating >= choiceRatingInitial + 1) {
                System.out.println("+100");
                pointsChangeable += 100;
            } else if (choiceRating == choiceRatingInitial) {
                System.out.println("+50");
                pointsChangeable += 50;
            } else if (choiceRating <= choiceRatingInitial - 4) {
                System.out.println("+0");
            } else if (choiceRating <= choiceRatingInitial - 3) {
                System.out.println("+15");
                pointsChangeable += 15;
            } else if (choiceRating <= choiceRatingInitial - 2) {
                System.out.println("+20");
                pointsChangeable += 20;
            } else if (choiceRating <= choiceRatingInitial - 1) {
                System.out.println("+30");
                pointsChangeable += 30;
            }
            System.out.println("Le nombre de votre points totale: " + pointsChangeable);
        }

        pointsInitial = pointsChangeable;
        choiceRatingFinal = choiceRatingInitial;

        if (choiceRatingFinal == NC) {
            if (pointsChangeable >= 50) {
                choiceRatingFinal = _40;
                pointsChangeable -= 50;
                numberOfLifts += 1;
            }
        }
        if (choiceRatingFinal == _40) {
            if (pointsChangeable >= 80) {
                choiceRatingFinal = _30_5;
                pointsChangeable -= 80;
                numberOfLifts += 1;
            } else if (pointsInitial < 70) {
                choiceRatingFinal = NC;
            }
        }
        if (choiceRatingFinal == _30_5) {
            if (pointsChangeable >= 150) {
                choiceRatingFinal = _30_4;
                pointsChangeable -= 150;
                numberOfLifts += 1;
            } else if (pointsInitial < 50) {
                choiceRatingFinal = _40;
            }
        }
        if (choiceRatingFinal == _30_4) {
            if (pointsChangeable >= 260) {
                choiceRatingFinal = _30_3;
                pointsChangeable -= 260;
                numberOfLifts += 1;
            } else if (pointsInitial < 90) {
                choiceRatingFinal = _30_5;
            }
        }
        if (choiceRatingFinal == _30_3) {
            if (pointsChangeable >= 340) {
                choiceRatingFinal = _30_2;
                pointsChangeable -= 340;
                numberOfLifts += 1;
            } else if (pointsInitial < 145) {
                choiceRatingFinal = _30_4;
            }
        }
        if (choiceRatingFinal == _30_2) {
            if (pointsChangeable >= 410) {
                choiceRatingFinal = _30_1;
                pointsChangeable -= 410;
                numberOfLifts += 1;
            } else if (pointsInitial < 205) {
                choiceRatingFinal = _30_3;
            }
        }
        if (choiceRatingFinal == _30_1) {
            if (pointsChangeable >= 480) {
                choiceRatingFinal = _30;
                pointsChangeable -= 480;
                numberOfLifts += 1;
            } else if (pointsInitial < 245) {
                choiceRatingFinal = _30_2;
            }
        }
        if (choiceRatingFinal == _30) {
            if (pointsChangeable >= 510) {
                choiceRatingFinal = _15_5;
                pointsChangeable -= 510;
                numberOfLifts += 1;
            } else if (pointsInitial < 290) {
                choiceRatingFinal = _30_1;
            }
        }
        if (choiceRatingFinal == _15_5) {
            if (pointsChangeable >= 580) {
                choiceRatingFinal = _15_4;
                pointsChangeable -= 580;
                numberOfLifts += 1;
            } else if (pointsInitial < 325) {
                choiceRatingFinal = _30;
            }
        }
        if (choiceRatingFinal == _15_4) {
            if (pointsInitial < 395) {
                choiceRatingFinal = _15_5;
            }
        }

        System.out.println("Votre classement au debut: " + nameRating);
        fillRating(choiceRatingFinal);
        System.out.println("Votre classement final est: " + nameRating);

        if (numberOfLifts != 0) {
            System.out.print("Vous etes monte de " + numberOfLifts + " divisions");
        } else if (choiceRatingFinal == choiceRatingInitial) {
            System.out.print("Vous vous restez dans la meme division");
        } else {
            System.out.print("Vous etes descendu d'une division");
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
                pointsChangeable = 0;
                break;
            case _40:
                nameRating = "40";
                pointsChangeable = 2;
                break;
            case _30_5:
                nameRating = "30/5";
                pointsChangeable = 5;
                break;
            case _30_4:
                nameRating = "30/4";
                pointsChangeable = 10;
                break;
            case _30_3:
                nameRating = "30/3";
                pointsChangeable = 20;
                break;
            case _30_2:
                nameRating = "30/2";
                pointsChangeable = 30;
                break;
            case _30_1:
                nameRating = "30/1";
                pointsChangeable = 50;
                break;
            case _30:
                nameRating = "30";
                pointsChangeable = 80;
                break;
            case _15_5:
                nameRating = "15/5";
                pointsChangeable = 120;
                break;
            case _15_4:
                nameRating = "15/4";
                pointsChangeable = 160;
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