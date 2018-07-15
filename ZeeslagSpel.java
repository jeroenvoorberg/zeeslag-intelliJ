package Zeeslag;

import java.util.Scanner;

class ZeeslagSpel{
    public static void main(String[] args) {
        Boot boot = new Boot();
        Speelveld speelveld = new Speelveld();
        Speler speler = new Speler();
        speler.bootplaatsen();
        speelveld.printVeld();

        speler.bombarderen(speler, boot);
        System.out.println("Game afgesloten");
    }
}
class Speelveld {
    static int [] [] veld =
            {{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0}};
    void printVeld(){
        for(int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(veld[i][j]);
            }
            System.out.println();
        }
    }
}
class Speler {
    Scanner scanner = new Scanner(System.in);
    void bootplaatsen() {
        while (true) {
            System.out.println("Welk schip wilt u plaatsen? toets P voor patrouilleschip of O voor onderzeeer. Toets Q om te stoppen met boten plaatsen en verder te gaan.");
            String i = scanner.nextLine();
            if (i.equals("Q")) {
                break;
            }
            System.out.println("Op welke X as wilt u de boot laten beginnen?");
            int j = scanner.nextInt();
            System.out.println("Op welke Y as wilt u de boot laten beginnen?");
            int l = scanner.nextInt();
            String z = scanner.nextLine();
            System.out.println("Moet de boot horizontaal (toets H) naar rechts op het veld of verticaal(toets V) naar beneden geplaatst worden?");
            String k = scanner.nextLine();

            if (k.equals("H") && i.equals("P")) {
                Patrouilleschip p1 = new Patrouilleschip();
                p1.positieHorizontaal(j, l);
                System.out.println("Horizontaal een " + p1 + " geplaatst vanaf plek " + j);
            }
            if (k.equals("V") && i.equals("P")) {
                Patrouilleschip p1 = new Patrouilleschip();
                p1.positieVerticaal(j, l);
                System.out.println("Verticaal een " + p1 + " geplaatst vanaf plek " + j);
            }
            if (k.equals("H") && i.equals("O")) {
                Onderzeeer o1 = new Onderzeeer();
                o1.positieHorizontaal(j, l);
                System.out.println("Horizontaal een " + o1 + " geplaatst vanaf plek " + j);
            }
            if (k.equals("V") && i.equals("O")) {
                Onderzeeer o1 = new Onderzeeer();
                o1.positieVerticaal(j, l);
                System.out.println("Verticaal een " + o1 + " geplaatst vanaf plek " + j);
            }
        }
    }
    void bombarderen(Speler speler, Boot boot){
        boolean doorgaan = true;
        while (doorgaan) {
            System.out.println("Bombardeer uw plek");
            System.out.println("toets 9 om af te sluiten");
            System.out.println("welk nummer op de X as?");
            int antwoord = scanner.nextInt();
            if (antwoord == 9) {
                doorgaan = false;
            }
            else {
                System.out.println("welk nummer op de Y as? ");
                int antwoord2 = scanner.nextInt();
                if (Speelveld.veld[antwoord2-1][antwoord-1] == 0) {
                    System.out.println("mis");
                }
                else {
                    System.out.println("raak!");
                    Speelveld.veld[antwoord2-1][antwoord-1] = 8;
                    if (boot.gezonken(boot, boot.lengte)){
                        System.out.println("boot gezonken");
                    }
                }
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        System.out.print(Speelveld.veld[i][j]);
                    }
                    System.out.println();
                }
            }
        }
    }
}
class Boot {
    int lengte = 2;
    boolean gezonken(Boot boot, int lengte) {
        boolean gzk = false;
        for (int i = 0; i < 10; i++) {
            int counter = 0;
            for (int j = 0; j < 10; j++) {
                if (Speelveld.veld[i][j] == 8) {
                    counter = counter + 1;
                }
            }
            if (counter == lengte) {
                gzk = true;
            }
        }
        return gzk;
    }
}

class Patrouilleschip extends Boot {
    int lengte = 2;
    static void positieHorizontaal(int plek, int plek2) {
        Speelveld.veld[plek2-1][plek-1] = 4;
        Speelveld.veld[plek2-1][plek] = 4;
    }
    static void positieVerticaal(int plek, int plek2) {
        Speelveld.veld[plek2-1][plek-1] = 4;
        Speelveld.veld[plek2][plek-1] = 4;
    }
}

class Onderzeeer extends Boot {
    int lengte = 3;
    static void positieHorizontaal(int plek, int plek2) {
        Speelveld.veld[plek2-1][plek-1] = 5;
        Speelveld.veld[plek2-1][plek] = 5;
        Speelveld.veld[plek2-1][plek+1] = 5;
    }
    static void positieVerticaal(int plek, int plek2) {
        Speelveld.veld[plek2-1][plek-1] = 5;
        Speelveld.veld[plek2][plek-1] = 5;
        Speelveld.veld[plek2+1][plek-1] = 5;
    }
}