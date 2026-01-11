package uczelnia.app;

import uczelnia.IOTerminal;
import uczelnia.Dane.KontenerDanych;
import uczelnia.KreatorDanych;
import uczelnia.MenagerDanych;
import uczelnia.osoba.Osoba;


import java.util.Scanner;

public class UczelniaApp {
    private final Scanner scanner;
    private final IOTerminal ioTerminal;
    private final MenagerDanych menagerDanych;
    private final KreatorDanych kreatorDanych;


    private KontenerDanych kontener;


    private static final String PLIK_BAZY = "uczelnia_baza.ser";

    public UczelniaApp() {

        this.scanner = new Scanner(System.in);
        this.ioTerminal = new IOTerminal();
        this.menagerDanych = new MenagerDanych(PLIK_BAZY);
        this.kreatorDanych = new KreatorDanych(ioTerminal);


        this.kontener = menagerDanych.wczytajBaze();

        System.out.println("\n=============================================");
        System.out.println("Aplikacja Uczelnia uruchomiona.");
        System.out.println("Wczytano: " + kontener.getOsoby().size() + " osób, " + kontener.getKursy().size() + " kursów.");
        System.out.println("=============================================");
    }


    private void wyswietlMenu() {
        System.out.println("\n--- MENU GŁÓWNE ---");
        System.out.println("1. Dodaj Studenta");
        System.out.println("2. Dodaj Pracownika Badawczo-Dydaktycznego");
        System.out.println("3. Dodaj Pracownika Administracyjnego ");
        System.out.println("4. Dodaj Kurs");
        System.out.println("5. Wyświetl wszystkie Osoby");
        System.out.println("6. Wyświetl wszystkie Kursy");
        System.out.println("9. Zapisz całą bazę");
        System.out.println("0. Zakończ program");
        System.out.print("Wybierz opcję: ");
    }

//    private void wyswietlMenu() tak ma wygladac
//    {
//        System.out.println("\n==== MENU GŁÓWNE ====");
//        System.out.println("1. Wczytaj z pliku");
//        System.out.println("2. Wyświetl");
//        System.out.println("3. Wyszukaj istnieje danej klasy");
//        System.out.println("4. Dodaj dany obiekt");
//        System.out.println("5. Sortuj względem danego parametru");
//        System.out.println("6. Usuń obiekt");
//        System.out.println("10. Zapisz do pliku");
//    }

    public void uruchom() {
        int wybor = -1;
        do {
            wyswietlMenu();


            if (scanner.hasNextInt()) {
                wybor = scanner.nextInt();
                scanner.nextLine();

                switch (wybor) {
                    case 1:
                        Osoba Student = kreatorDanych.utworzStudenta(scanner);
                        kontener.dodajOsobe(Student);
                        System.out.println("Dodano: " + Student.getImie() + " " + Student.getNazwisko());
                        break;
                    case 2:
                        Osoba nowyPracownik = kreatorDanych.utworzPracownikaBadawczoDydaktycznego(scanner);
                        kontener.dodajOsobe(nowyPracownik);
                        System.out.println("Dodano: " + nowyPracownik.getImie() + " " + nowyPracownik.getNazwisko());
                        break;
                    case 3:
                        System.out.println("Potrzeba zmiany bo nie ma czegos takiego jak filtruj Prowadzacych bo usunalem w kursach odwolanie");
//                        List<PracownikBadawczoDydaktyczny> prowadzacy = KreatorDanych.filtrujProwadzacych(kontener.getOsoby());
//                        Kurs nowyKurs = kreatorDanych.utworzKurs(scanner, prowadzacy);
//                        if (nowyKurs != null) {
//                            kontener.dodajKurs(nowyKurs);
//                            System.out.println("Dodano Kurs: " + nowyKurs.getNazwa());
//                        }
                        break;
                    case 4:
                        Osoba nowyPracownikAdm = kreatorDanych.utworzPracownikaAdministracyjnego(scanner);
                        kontener.dodajOsobe(nowyPracownikAdm);
                        System.out.println("Dodano: " + nowyPracownikAdm.getImie() + " " + nowyPracownikAdm.getNazwisko());
                        break;
                    case 5:
                        kontener.pokazOsoby();
                        break;
                    case 6:
                        kontener.pokazWszystkieKursy();
                        break;
                    case 9:
                        menagerDanych.zapiszBaze(kontener);
                        break;

                    case 0:
                        System.out.println("\nZamykanie aplikacji...");
                        scanner.close();
                        break;
                    default:
                        System.out.println("Nieznana opcja. Spróbuj ponownie.");
                }
            } else {
                System.out.println("Niepoprawny format danych. Wprowadź numer opcji.");
                scanner.nextLine();
                wybor = -1;
            }
        } while (wybor != 0);
    }

    public static void main(String[] args) {
        new UczelniaApp().uruchom();
    }

}