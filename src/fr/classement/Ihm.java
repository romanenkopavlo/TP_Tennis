package fr.classement;
import clavier.In;

@SuppressWarnings("ALL")
public class Ihm {
    static final int NC = 1, _40 = 2, _30_5 = 3, _30_4 = 4, _30_3 = 5,
            _30_2 = 6, _30_1 = 7, _30 = 8, _15_5 = 9, _15_4 = 10;
    static int rating, ratingInitial, ratingFinal,
            victories, victoriesDefault = 0, victoriesBonus = 0, victoriesBonusFormula = 0,
            pointsOfRating = 0, pointsInitial = 0, pointsFinal = 0, pointsTotale = 0,
            numberOfLifts = 0, nombreDeDescendre = 0, nombreDeTour = 0, defeats, equal = 0, lower = 0, large = 0;
    static String nameRating = null;

    public static void main(String[] args) {
        Victory [] victories_list = null;
        while (true) {
            if (nombreDeTour == 0) {
                choice();
                System.out.print("Entrez votre classement: ");
                ratingInitial = In.readInteger();
            } else {
                ratingInitial = ratingFinal;
            }
            fillRating(ratingInitial);
            fillVictories(ratingInitial);

            System.out.println("Votre classement est: " + nameRating);
            System.out.println("Nombre de points au depart de votre classement est: " + pointsOfRating);
            System.out.println("Nombre de victoires par default est: " + victoriesDefault);

            System.out.print("Entrez votre nombre de victoires: ");
            victories = In.readInteger();

            if (victories > victoriesDefault) {
                if (victories != 0) {
                    System.out.print("Entrez votre nombre de defaites: ");
                    defeats = In.readInteger();

                    for (int i = 0; i < defeats; i++) {
                        choice();
                        System.out.print("Entrez le classement de votre defaite №" + (i + 1) + ": ");
                        rating = In.readInteger();

                        if (rating == ratingInitial) {
                            equal++;
                        } else if (rating <= ratingInitial - 2) {
                            large++;
                        } else if (rating <= ratingInitial - 1) {
                            lower++;
                        }
                    }
                    victoriesBonusFormula = victories - equal - (2 * lower) - (5 * large);
                }
            }

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

            victories_list = new Victory[victories];

            pointsInitial = pointsOfRating;
            pointsFinal += pointsInitial;

            for (int i = 0; i < victories; i++) {
                choice();
                System.out.print("Entrez le classement de votre victoire N°" + (i + 1) + ": ");
                rating = In.readInteger();

                fillRating(rating);
                victories_list[i] = new Victory();
                victories_list[i].victory_number = i + 1;
                victories_list[i].rating_name = nameRating;
                pointsOfRating = 0;

                if (rating >= ratingInitial + 2) {
                    System.out.println("+150");
                    pointsOfRating += 150;
                } else if (rating >= ratingInitial + 1) {
                    System.out.println("+100");
                    pointsOfRating += 100;
                } else if (rating == ratingInitial) {
                    System.out.println("+50");
                    pointsOfRating += 50;
                } else if (rating <= ratingInitial - 4) {
                    System.out.println("+0");
                } else if (rating <= ratingInitial - 3) {
                    System.out.println("+15");
                    pointsOfRating += 15;
                } else if (rating <= ratingInitial - 2) {
                    System.out.println("+20");
                    pointsOfRating += 20;
                } else if (rating <= ratingInitial - 1) {
                    System.out.println("+30");
                    pointsOfRating += 30;
                }
                victories_list[i].victory_points = pointsOfRating;
                pointsFinal += pointsOfRating;

                System.out.println("Le nombre de votre points totale: " + pointsFinal);
            }

            pointsInitial = pointsFinal;

            if (victories != 0) {
                fillRating(ratingInitial);
                pointsFinal = 0;
                pointsTotale += pointsOfRating;
                ratingFinal = ratingInitial;
                for (int i = 0; i < victories_list.length; i++) {
                    if (i == 0) {
                        pointsFinal += pointsOfRating;
                    }
                    pointsTotale += victories_list[i].victory_points;
                    pointsFinal += victories_list[i].victory_points;
                    if (nombreDeDescendre != 1) {
                        upgradeRating();
                        getNameRating(ratingFinal);
                        victories_list[i].rating_name_evolving = nameRating;
                    } else {
                        victories_list[i].rating_name_evolving = victories_list[i - 1].rating_name_evolving;
                    }
                    System.out.println("Le classement de victoire №" + victories_list[i].victory_number + ": " + victories_list[i].rating_name + ". Vous avez gagne " + victories_list[i].victory_points + " points. Votre nombre de points: " + pointsTotale + ". Votre classement: " + victories_list[i].rating_name_evolving);
                    if (nombreDeDescendre != 1) {
                        if (ratingFinal == ratingInitial - 1) {
                            nombreDeDescendre += 1;
                        }
                    }
                }
            } else {
                ratingFinal = ratingInitial;
                upgradeRating();
            }

            fillRating(ratingInitial);
            System.out.println("Votre classement au debut: " + nameRating);
            fillRating(ratingFinal);
            System.out.println("Votre classement final est: " + nameRating);

            if (numberOfLifts != 0) {
                System.out.print("Vous etes monte de " + numberOfLifts + " divisions");
            } else if (ratingFinal == ratingInitial) {
                System.out.print("Vous vous restez dans la meme division");
            } else {
                System.out.print("Vous etes descendu d'une division");
            }
            nombreDeTour += 1;
            pointsFinal = 0;
            pointsTotale = 0;
            equal = 0;
            lower = 0;
            large = 0;
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
                pointsOfRating = 0;
                break;
            case _40:
                nameRating = "40";
                pointsOfRating = 2;
                break;
            case _30_5:
                nameRating = "30/5";
                pointsOfRating = 5;
                break;
            case _30_4:
                nameRating = "30/4";
                pointsOfRating = 10;
                break;
            case _30_3:
                nameRating = "30/3";
                pointsOfRating = 20;
                break;
            case _30_2:
                nameRating = "30/2";
                pointsOfRating = 30;
                break;
            case _30_1:
                nameRating = "30/1";
                pointsOfRating = 50;
                break;
            case _30:
                nameRating = "30";
                pointsOfRating = 80;
                break;
            case _15_5:
                nameRating = "15/5";
                pointsOfRating = 120;
                break;
            case _15_4:
                nameRating = "15/4";
                pointsOfRating = 160;
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
    public static void upgradeRating() {
        if (ratingFinal == NC) {
            if (pointsFinal >= 50) {
                ratingFinal = _40;
                pointsFinal -= 50;
                numberOfLifts += 1;
            }
        }
        if (ratingFinal == _40) {
            if (pointsFinal >= 80) {
                ratingFinal = _30_5;
                pointsFinal -= 80;
                numberOfLifts += 1;
            } else if (pointsInitial < 70) {
                ratingFinal = NC;
            }
        }
        if (ratingFinal == _30_5) {
            if (pointsFinal >= 150) {
                ratingFinal = _30_4;
                pointsFinal -= 150;
                numberOfLifts += 1;
            } else if (pointsInitial < 50) {
                ratingFinal = _40;
            }
        }
        if (ratingFinal == _30_4) {
            if (pointsFinal >= 260) {
                ratingFinal = _30_3;
                pointsFinal -= 260;
                numberOfLifts += 1;
            } else if (pointsInitial < 90) {
                ratingFinal = _30_5;
            }
        }
        if (ratingFinal == _30_3) {
            if (pointsFinal >= 340) {
                ratingFinal = _30_2;
                pointsFinal -= 340;
                numberOfLifts += 1;
            } else if (pointsInitial < 145) {
                ratingFinal = _30_4;
            }
        }
        if (ratingFinal == _30_2) {
            if (pointsFinal >= 410) {
                ratingFinal = _30_1;
                pointsFinal -= 410;
                numberOfLifts += 1;
            } else if (pointsInitial < 205) {
                ratingFinal = _30_3;
            }
        }
        if (ratingFinal == _30_1) {
            if (pointsFinal >= 480) {
                ratingFinal = _30;
                pointsFinal -= 480;
                numberOfLifts += 1;
            } else if (pointsInitial < 245) {
                ratingFinal = _30_2;
            }
        }
        if (ratingFinal == _30) {
            if (pointsFinal >= 510) {
                ratingFinal = _15_5;
                pointsFinal -= 510;
                numberOfLifts += 1;
            } else if (pointsInitial < 290) {
                ratingFinal = _30_1;
            }
        }
        if (ratingFinal == _15_5) {
            if (pointsFinal >= 580) {
                ratingFinal = _15_4;
                pointsFinal -= 580;
                numberOfLifts += 1;
            } else if (pointsInitial < 325) {
                ratingFinal = _30;
            }
        }
        if (ratingFinal == _15_4) {
            if (pointsInitial < 395) {
                ratingFinal = _15_5;
            }
        }
    }
    public static void getNameRating(int final_rating) {
        switch (final_rating) {
            case 1:
                nameRating = "Non Classe";
                break;
            case 2:
                nameRating = "40";
                break;
            case 3:
                nameRating = "30/5";
                break;
            case 4:
                nameRating = "30/4";
                break;
            case 5:
                nameRating = "30/3";
                break;
            case 6:
                nameRating = "30/2";
                break;
            case 7:
                nameRating = "30/1";
                break;
            case 8:
                nameRating = "30";
                break;
            case 9:
                nameRating = "15/5";
                break;
            case 10:
                nameRating = "15/4";
                break;
        }
    }
}