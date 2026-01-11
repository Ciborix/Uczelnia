package uczelnia;


import uczelnia.Dane.KontenerDanych;

import java.io.*;

public class MenagerDanych {

    private final String nazwaPliku;

    public MenagerDanych(String nazwaPliku) {
        this.nazwaPliku = nazwaPliku;
    }

    // Metoda zapisu (bez zmian)
    public void zapiszBaze(KontenerDanych kontener) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(nazwaPliku))) {

            oos.writeObject(kontener);
            System.out.println("Pomyślnie zapisano całą bazę do pliku: " + nazwaPliku);

        } catch (IOException e) {
            System.err.println("Błąd podczas zapisu bazy: " + e.getMessage());
        }
    }

    public KontenerDanych wczytajBaze() {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(nazwaPliku))) {

            KontenerDanych wczytanyObiekt = (KontenerDanych) ois.readObject();
            System.out.println("Pomyślnie wczytano całą bazę z pliku: " + nazwaPliku);
            return wczytanyObiekt;

        } catch (FileNotFoundException e) {

            System.out.println("Plik bazy (" + nazwaPliku + ") nie istnieje. Ładowanie danych demonstracyjnych.");

            return DemoData.utworzDaneTestowe();

        } catch (IOException | ClassNotFoundException e) {

            System.err.println("Krytyczny błąd podczas wczytywania/deserializacji bazy: " + e.getMessage());
            e.printStackTrace();
            return new KontenerDanych();
        }
    }
}