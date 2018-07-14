package Zeeslag;

import java.util.Scanner;

class ZeeslagSpel{
    public static void main(String[] args) {
        Speelveld speelveld = new Speelveld();
        Speler jan = new Speler();
        Boot boot = new Boot();

        jan.bootplaatsen(3,3,5,5,6,6);
        speelveld.printVeld();
        jan.bombarderen(jan, boot);

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
    void bootplaatsen(int a, int b, int c, int d, int e, int f) {
        Patrouilleschip Patrouilleschip1 = new Patrouilleschip();
        Patrouilleschip1.positieHorizontaal(a,b);
        Onderzeeer onderzeeer = new Onderzeeer();
        onderzeeer.positieHorizontaal(c,d);
        onderzeeer.positieVerticaal(e,f);

    }
    void bombarderen(Speler speler, Boot boot){
        boolean doorgaan = true;
        while (doorgaan) {
            System.out.println("Bombardeer uw plek");
            System.out.println("welk nummer op de X as?");
            int antwoord = scanner.nextInt();
            if (antwoord == 9) {
                doorgaan = false;
            }
            else {
                System.out.println("welk nummer op de Y as? (toets 9 om af te sluiten)");
                int antwoord2 = scanner.nextInt();
                if (Speelveld.veld[antwoord2-1][antwoord-1] == 0) {
                    System.out.println("mis");
                }
                else {
                    System.out.println("raak!");
                    Speelveld.veld[antwoord2-1][antwoord-1] = 8;
                    if (boot.gezonken(boot, boot.lengte) == true) {
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
    int lengte;
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
    void positieHorizontaal(int plek, int plek2) {
        Speelveld.veld[plek2-1][plek-1] = 4;
        Speelveld.veld[plek2-1][plek] = 4;
    }
    void positieVerticaal(int plek, int plek2) {
        Speelveld.veld[plek2-1][plek] = 4;
        Speelveld.veld[plek2][plek] = 4;
    }
}

class Onderzeeer extends Boot {
    int lengte = 3;
    void positieHorizontaal(int plek, int plek2) {
        Speelveld.veld[plek][plek2] = 5;
        Speelveld.veld[plek][plek2+1] = 5;
        Speelveld.veld[plek][plek2+2] = 5;
    }
    void positieVerticaal(int plek, int plek2) {
        Speelveld.veld[plek2][plek] = 5;
        Speelveld.veld[plek2 + 1][plek] = 5;
        Speelveld.veld[plek2 + 2][plek] = 5;
    }
}