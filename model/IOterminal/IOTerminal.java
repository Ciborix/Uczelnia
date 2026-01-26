package model.IOterminal;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.osoba.student.Kurs;

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
    public ArrayList<Integer> askWielokrotne(String pytanie)
    {
        boolean wybor = true;
        ArrayList<Integer> zapytania = new ArrayList<>();
        System.out.println(pytanie);
        while(wybor)
        {
            try{
                System.out.println("Wybór: ");
                Integer lin = scanner.nextInt();
                if (lin.equals(0)) {
                    wybor = false;
                    break;
                }
                zapytania.add(lin);
            }
            catch (NumberFormatException e){
                System.out.println("Błąd: Wymagana liczba całkowita.");
            }
        }
        return zapytania;
    }

    public Integer wczytajInt(String prompt) {
        System.out.print(prompt + ": ");
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                if (input.equalsIgnoreCase("null")) return null;
                return Integer.parseInt(input);
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
        return ask("\n--- SYSTEM ZARZĄDZANIA UCZELNIĄ ---\n1) Wyświetlanie\n2) Dodawanie\n3) Usuwanie\n4) Sortowanie\n5) Lista płac\n6) Wyszukiwanie\n7) Usuń duplikaty\n8) Dodaj kurs do Studenta\n10) Zapisz\n0) Wyjście");
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

    public Kurs wybierzKurs(List<Kurs> dostepneKursy)
    {
        if (dostepneKursy.isEmpty())
        {
            System.out.println("Brak dostępnych kursów");
            return null;
        }
        System.out.println("\n--- LISTA DOSTĘPNYCH KURSOW ---");
        for(int i=0;i<dostepneKursy.size();i++)
        {
            System.out.println(i + ". " + dostepneKursy.get(i).wyswietlDane());
        }

        int wybor = ask("Wybierz numer kursu (lub -1 aby anulowac)");
        if (wybor>=0 && wybor<dostepneKursy.size()) return dostepneKursy.get(wybor);
        return null;
    }

    public int spytajKryterium()
    {
        return ask("Czy chcesz wyszukac po kilku kryteriach czy tylko po jednym?" +
                "\n1) Po jednym kryterium" +
                "\n2) Po kilku");
    }
}