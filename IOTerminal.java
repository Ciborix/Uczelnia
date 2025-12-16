package uczelnia;

import java.util.InputMismatchException;
import java.util.Scanner;

public class IOTerminal {

    public int wczytajInt(Scanner scanner, String prompt) {
        int liczba = 0;

        System.out.print(prompt + ": ");

        while (true) {
            try {

                if (scanner.hasNextInt()) {
                    liczba = scanner.nextInt();
                    scanner.nextLine();
                    return liczba;
                } else {

                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Błąd: Wymagana jest liczba całkowita. Spróbuj ponownie.");
                scanner.nextLine();
            }
        }
    }


    public double wczytajDouble(Scanner scanner, String prompt) {
        double liczba = 0.0;

        System.out.print(prompt + ": ");

        while (true) {
            try {
                if (scanner.hasNextDouble()) {
                    liczba = scanner.nextDouble();
                    scanner.nextLine();
                    return liczba;
                } else {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Błąd: Wymagana jest liczba zmiennoprzecinkowa. Sprawdź format regionalny (np. przecinek lub kropka). Spróbuj ponownie.");
                scanner.nextLine();
            }
        }
    }


    public String wczytajString(Scanner scanner, String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine();
    }
}