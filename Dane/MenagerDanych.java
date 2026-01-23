package Dane;


import osoba.Osoba;
import osoba.pracownik.PracownikAdministracyjny;
import osoba.pracownik.PracownikBadawczoDydaktyczny;
import osoba.student.Kurs;
import osoba.student.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MenagerDanych {

    private final String nazwaPliku;

    public MenagerDanych(String nazwaPliku) {
        this.nazwaPliku = nazwaPliku;
    }

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
    public KontenerDanych wczytajBazeZTXT(String nazwaPliku)
    {
        KontenerDanych kontener = new KontenerDanych();
        try (Scanner sc = new Scanner(new File(nazwaPliku))) {
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                if(line.trim().isEmpty()) continue;
                String[] dane = line.split(";");
                rozczytaj(dane, kontener);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku " + e.getMessage());
        }
        return kontener;
    }
    private void rozczytaj(String[] dane, KontenerDanych kontener) {
        String typ = dane[0];
        switch (typ) {
            case "S","SK":
                ArrayList<Kurs> kursy = new ArrayList<>();
                if (typ.equals("SK")) {
                    for(int i=13;i+2<dane.length;i+=3) {
                        Kurs k = new Kurs(dane[i],dane[i+1],Integer.parseInt(dane[i+2].trim()));
                        kursy.add(k);
                        kontener.dodajKurs(k);
                    }
                }
                Student student = new Student(dane[1], dane[2], dane[3],Integer.parseInt(dane[4]),dane[5], Integer.parseInt(dane[6]),Integer.parseInt(dane[7]),kursy,
                        Boolean.parseBoolean(dane[8]),
                        Boolean.parseBoolean(dane[9]),
                        Boolean.parseBoolean(dane[10]),
                        Boolean.parseBoolean(dane[11]),
                        Boolean.parseBoolean(dane[12]));
                kontener.dodajOsobe(student);
                break;
            case "PA":
                PracownikAdministracyjny pa = new PracownikAdministracyjny(
                        dane[1],
                        dane[2],
                        dane[3],
                        Integer.parseInt(dane[4]),
                        dane[5],
                        dane[6],
                        Double.parseDouble(dane[7]),
                        Double.parseDouble(dane[8]),
                        Integer.parseInt(dane[9]));
                kontener.dodajOsobe(pa);
                break;
            case "PBD":
                PracownikBadawczoDydaktyczny pbd = new PracownikBadawczoDydaktyczny(
                        dane[1],
                        dane[2],
                        dane[3],
                        Integer.parseInt(dane[4]),
                        dane[5],
                        dane[6],
                        Double.parseDouble(dane[7]),
                        Double.parseDouble(dane[8]),
                        Integer.parseInt(dane[9]));
                kontener.dodajOsobe(pbd);
                break;

        }
    }
    public void zapiszTXT(KontenerDanych kontener, String nazwa) {
        try (PrintWriter pw = new PrintWriter(new File(nazwa))) {
            for (Osoba o : kontener.getOsoby()) {
                if (o instanceof Student s) {
                    // Ustalamy typ: S dla braku kursów, SK jeśli jakieś ma
                    String typ = s.getKursyList().isEmpty() ? "S" : "SK";

                    // Budujemy podstawową linię (indeksy 0-12)
                    StringBuilder sb = new StringBuilder();
                    sb.append(typ).append(";")
                            .append(s.getImie()).append(";")
                            .append(s.getNazwisko()).append(";")
                            .append(s.getPesel()).append(";")
                            .append(s.getWiek()).append(";")
                            .append(s.getPlec()).append(";")
                            .append(s.getNrIndeks()).append(";")
                            .append(s.getRokStudiow()).append(";")
                            .append(s.isCzyERASMUS()).append(";")
                            .append(s.isCzyIStopien()).append(";")
                            .append(s.isCzyIIStopien()).append(";")
                            .append(s.isCzyStacjonarny()).append(";")
                            .append(s.isCzyNieStacjonarny());

                    for (Kurs k : s.getKursyList()) {
                        sb.append(";")
                                .append(k.getNazwa()).append(";")
                                .append(k.getProwadzacy()).append(";")
                                .append(k.getPunktyECTS());

                    }
                    pw.println(sb.toString());

                } else if (o instanceof PracownikAdministracyjny pa) {
                    pw.println("PA;" + pa.getImie() + ";" + pa.getNazwisko() + ";" + pa.getPesel() + ";" +
                            pa.getWiek() + ";" + pa.getPlec() + ";" + pa.getStanowisko() + ";" +
                            pa.getStazPracy() + ";" + pa.getPensja() + ";" + pa.getLiczbNadgodzin());

                } else if (o instanceof PracownikBadawczoDydaktyczny pbd) {
                    pw.println("PBD;" + pbd.getImie() + ";" + pbd.getNazwisko() + ";" + pbd.getPesel() + ";" +
                            pbd.getWiek() + ";" + pbd.getPlec() + ";" + pbd.getStanowisko() + ";" +
                            pbd.getStazPracy() + ";" + pbd.getPensja() + ";" + pbd.getLiczbaPublikacji());
                }
            }
            System.out.println("Pomyślnie wyeksportowano bazę do pliku TXT: " + nazwa);

        } catch (IOException e) {
            System.err.println("Błąd podczas zapisu TXT: " + e.getMessage());
        }
    }
}