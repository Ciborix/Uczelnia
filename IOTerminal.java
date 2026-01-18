package uczelnia;

import java.util.Scanner;

public class IOTerminal {
    private final Scanner scanner = new Scanner(System.in);

    public int ask(String pytanie) {
        System.out.println(pytanie);
        while (true) {
            try {
                System.out.print("Wybór: ");
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Błąd: Wymagana liczba całkowita.");
            }
        }
    }

    public Integer wczytajInt(String prompt) {
        System.out.print(prompt + ": ");
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                if (input.equalsIgnoreCase("null")) return null;
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Błąd: Wymagana liczba całkowita.");
            }
        }
    }

    public Double wczytajDouble(String prompt) {
        System.out.print(prompt + ": ");
        while (true) {
            try {
                String input = scanner.nextLine().replace(',', '.');
                if (input.equalsIgnoreCase("null")) return null;
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Błąd: Wymagana liczba zmiennoprzecinkowa.");
            }
        }
    }

    public String wczytajString(String prompt) {
        System.out.print(prompt + ": ");
        String s = scanner.nextLine();
        if (s.equalsIgnoreCase("null")) return null;
        return s;
    }

    public int wyswietlMenuGlowne() {
        return ask("\n--- SYSTEM ZARZĄDZANIA UCZELNIĄ ---\n1) Wyświetlanie\n2) Dodawanie\n3) Usuwanie\n4) Sortowanie\n5) Lista płac\n6) Wyszukiwanie\n7) Usuń duplikaty\n10) Zapisz\n0) Wyjście");
    }

    public int wybierzKategorieUsuwania() {
        return ask("\n==== USUWANIE ====\n1. Student\n2. Pracownik\n3. Kurs\n0. Powrót");
    }

    public int wybierzKryteriumUsuwaniaStudenta() {
        return ask("Kryterium: 1. Nazwisko | 2. Imię | 3. Indeks | 4. Rok");
    }

    public int wybierzKryteriumUsuwaniaPracownika() {
        return ask("Kryterium: 1. Nazwisko | 2. Imię | 3. Staż | 4. Stanowisko");
    }

    public int wybierzKryteriumUsuwaniaKursu() {
        return ask("Kryterium: 1. Nazwa | 2. Prowadzący | 3. ECTS");
    }

    public int wybierzOpcjeSortowania() {
        return ask("\n--- SORTOWANIE ---\n" +
                "1. Osoby: Po nazwisku\n" +
                "2. Osoby: Nazwisko + Imię\n" +
                "3. Osoby: Nazwisko + Wiek (malejąco)\n" +
                "4. Kursy: ECTS + Nazwisko prowadzącego\n" +
                "0. Powrót");
    }

    public int wybierzOpcjeWyswietlania() {
        return ask("\n--- CO CHCESZ WYŚWIETLIĆ? ---\n" +
                "1. Wszystkich (Osoby i Kursy)\n" +
                "2. Tylko Studentów\n" +
                "3. Tylko Pracowników\n" +
                "4. Tylko Kursy\n" +
                "0. Powrót");
    }
    public int wybierzOpcjeWyszukiwania()
    {
        return ask("\n--- KOGO WYSZUKAC ---\n" +
                "1. Studenta\n" +
                "2. Pracownika\n" +
                "3. Kursy\n");

    }
    public int wybierzOpcjePlikow()
    {
        return ask("--Co chcesz zrobic z danymi--\n " +
                "1. Wczytac plik txt\n " +
                "2. Zapisac plik txt\n " +
                "3. Wczytac serializacje\n " +
                "4. Zapisac serializacje\n " +
                "5. Zapisac serializacje i txt owczesnych plików\n");
    }
}