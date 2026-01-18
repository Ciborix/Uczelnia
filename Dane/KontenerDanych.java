package uczelnia.Dane;
import uczelnia.obserwator.IObserwator;
import uczelnia.osoba.*;
import uczelnia.osoba.student.*;
import uczelnia.osoba.pracownik.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class KontenerDanych implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Osoba> osoby;
    private List<Kurs> kursy = new ArrayList<>();
    private transient List<IObserwator> obsewatorzy = new ArrayList<>();

    public KontenerDanych()
    {
        this.osoby=new ArrayList<>();
        this.kursy=new ArrayList<>();
    }

    public void dodajOsobe(Osoba osoba){
        osoby.add(osoba);
        powiadom("Dodano do bazy: " + osoba.przedstawSie());
    }

    public void dodajKurs(Kurs kurs)
    {
        this.kursy.add(kurs);
        powiadom("Dodano nowy kurs: " + kurs.wyswietlDane());
    }

    public List<Osoba> getOsoby() { return osoby; }
    public List<Kurs> getKursy() { return kursy; }


    public void pokazOsoby()
    {
        if (osoby == null || osoby.isEmpty()) {
            System.out.println("Brak osób do wyświetlenia.");
            return;
        }

        for(int i=0;i<osoby.size();i++){
            System.out.println("-------Osoba " + i + "-------");
            System.out.println(osoby.get(i).toString());
            System.out.println("------------------------------");
        }
    }
    public List<PracownikUczelni> wyszukajPracownika(
            String nazwisko,
            String imie,
            String stanowisko,
            Double stazpracy,
            Integer liczbaNadgodzin,
            Double pensja)
    {
        List<PracownikUczelni> pracownicy = new ArrayList<>();
        for(Osoba o: osoby){
            if (o instanceof PracownikUczelni) {
                PracownikUczelni p=(PracownikUczelni)o;
                boolean ok = true;
                if (nazwisko != null && !p.getNazwisko().equalsIgnoreCase(nazwisko)) ok = false;
                if (imie != null && !p.getImie().equalsIgnoreCase(imie)) ok = false;
                if (stanowisko != null && !p.getStanowisko().equalsIgnoreCase(stanowisko)) ok= false;
                if (stazpracy != null && !stazpracy.equals(p.getStazPracy())) ok=false;
                if (p instanceof PracownikAdministracyjny){
                    PracownikAdministracyjny pa =  (PracownikAdministracyjny)p;
                    if (liczbaNadgodzin!=null && !liczbaNadgodzin.equals(pa.getLiczbNadgodzin())) ok=false;
                }
                if (pensja!=null && !pensja.equals(p.getPensja())) ok=false;
                if (ok) pracownicy.add(p);
            }}
        return pracownicy;
    }

    public List<Student> wyszukajStudenta(
            String nazwisko,
            String imie,
            Integer numerIndeksu,
            Integer rokStudiow,
            String nazwaKursu)
    {
        List<Student> studenci=new ArrayList<>();
        for(Osoba o: osoby)
        {
            if (o instanceof Student)
            {
                Student s=(Student)o;
                boolean ok = true;
                if (nazwisko!=null && !s.getNazwisko().equalsIgnoreCase(nazwisko)) ok = false;
                if (imie!=null && !s.getImie().equalsIgnoreCase(imie)) ok = false;
                if (numerIndeksu!=null && !numerIndeksu.equals(s.getNrIndeks())) ok=false;
                if (rokStudiow!=null && !rokStudiow.equals(s.getRokStudiow())) ok=false;
                if (nazwaKursu!=null)
                {
                    List<Kurs> k = s.getKursyList();
                    if (k==null || k.isEmpty()) ok=false; //student ma brak kursow
                    else {
                        boolean found=false;
                        for(Kurs kurs : k)
                        {
                            String nazwa_wewnetrzna = kurs.getNazwa();
                            if (nazwa_wewnetrzna.equals(nazwaKursu))
                            {
                                found=true;
                                break;
                            }
                        }
                        if (!found) ok=false;
                    }
                }
                if (ok) studenci.add(s);
            }
        }
        return studenci;
    }

    public List<Kurs> wyszukajKurs(
            String nazwa,
            String prowadzacy,
            Integer ECTS) {

        List<Kurs> wynik = new ArrayList<>();
        for (Kurs k : kursy) {
            boolean ok = true;
            if (nazwa != null && !k.getNazwa().equalsIgnoreCase(nazwa)) ok = false;
            if (prowadzacy != null && !k.getProwadzacy().equals(prowadzacy)) ok = false;
            if (ECTS != null && !ECTS.equals(k.getPunktyECTS())) ok = false;
            if (ok) wynik.add(k);
        }
        return wynik;
    }

    public void drukujListeWyszukanych(List<? extends Osoba> k)
    {
        if (k==null || k.isEmpty()) System.out.println("Brak wyników dla podanych parametrów");
        else{
            for(Osoba o: k)
            {
                System.out.println(o.toString() + '\n');
            }
        }
    }
    public void drukujKursy(List<Kurs> kurs) {
        if (kurs == null || kurs.isEmpty()) {
            System.out.println("Brak kursów");
        } else {
            for (Kurs k : kurs) {
                System.out.println(k);
            }
        }
    }
    public void pokazWszystkichPracownikow() {
        var pracownicy = wyszukajPracownika(null, null, null, null, null, null);
        drukujListeWyszukanych(pracownicy);
    }

    public void pokazWszystkichStudentow() {
        var studenci = wyszukajStudenta(null, null, null, null, null);
        drukujListeWyszukanych(studenci);
    }

    public void pokazWszystkieKursy() {
        var kursyStudentow = wyszukajKurs(null, null, null);
        drukujKursy(kursyStudentow);
    }

    public void usunPracownika(int opcja, String wartosc)
    {
        Iterator<Osoba> it = osoby.iterator();
        while (it.hasNext()) {
            Osoba o = it.next();
            if (o instanceof PracownikUczelni p) {
                boolean doUsuniecia = false;
                switch (opcja) {
                    case 1 -> doUsuniecia = p.getNazwisko().equalsIgnoreCase(wartosc); //nazwisko
                    case 2 -> doUsuniecia = p.getImie().equalsIgnoreCase(wartosc); //imie
                    case 4 -> doUsuniecia = p.getStanowisko().equalsIgnoreCase(wartosc); //stanowisko
                    case 3 -> //staz pracy
                    {
                        try {
                            doUsuniecia = p.getStazPracy() == Double.parseDouble(wartosc);
                        } catch (NumberFormatException e) {
                            System.out.println("Bląd: Staż musi byc liczba");
                        }
                    }
                }
                if (doUsuniecia)
                {
                    it.remove();
                    powiadom("Usunięto Pracownika: " + p.przedstawSie());
                    break;
                }
                else System.out.println("Informacja: Nie znaleziono pracownika spełniającego podane kryteria.");
            }
        }
    }

    public void usunStudenta(int opcja, String wartosc) {
        Iterator<Osoba> it = osoby.iterator();
        while (it.hasNext()) {
            Osoba o = it.next();
            if (o instanceof Student s) {
                boolean doUsuniecia = false;
                switch (opcja) {
                    case 1 -> doUsuniecia = s.getNazwisko().equalsIgnoreCase(wartosc); //nazwisko
                    case 2 -> doUsuniecia = s.getImie().equalsIgnoreCase(wartosc); //imie
                    case 3 -> doUsuniecia = String.valueOf(s.getNrIndeks()).equals(wartosc); //indeks
                    case 4 -> doUsuniecia = String.valueOf(s.getRokStudiow()).equals(wartosc); //rok
                }
                if (doUsuniecia)
                {
                    it.remove();
                    powiadom("Usunieto studenta: " + s.przedstawSie());
                    break;
                }
                else System.out.println("Informacja: Nie znaleziono Studenta spełniającego podane kryteria.");
            }
        }
    }

    public void usunKurs(int opcja, String wartosc)
    {
        Iterator<Kurs> it = kursy.iterator();
        while (it.hasNext()) {
            Kurs k = it.next();
            boolean doUsuniecia = false;
            switch (opcja) {
                case 1 -> doUsuniecia = k.getNazwa().equalsIgnoreCase(wartosc);
                case 2 -> doUsuniecia = k.getProwadzacy().equalsIgnoreCase(wartosc);
                case 3 -> {
                    try{
                        doUsuniecia = k.getPunktyECTS() == Double.parseDouble(wartosc);
                    }
                    catch (NumberFormatException e){
                        System.out.println("Błąd. Punkty ECTS muszą byc liczba całkowita");
                    }
                }
            }
            if (doUsuniecia)
            {
                it.remove();
                powiadom("Usunieto kurs: " + k.wyswietlDane());
                break;
            }
            else System.out.println("Informacja: Nie znaleziono kursu spełniającego podane kryteria.");
        }
    }

    public void dodajObserwatora(IObserwator obs)
    {
        if (obsewatorzy == null) {
            obsewatorzy = new ArrayList();
        }
        obsewatorzy.add(obs);
    }

    public void powiadom(String msg)
    {
        if (obsewatorzy == null) return;
        for(IObserwator obs: obsewatorzy)
        {
            obs.aktualizuj(msg, this);
        }
    }

    public void drukujListePlac() {
        System.out.println("\n--- LISTA PŁAC PRACOWNIKÓW ---");
        boolean znaleziono = false;
        for (Osoba o : osoby) {
            if (o instanceof PracownikUczelni p) {
                znaleziono = true;
                System.out.println("------------------------------------");
                System.out.println("Pracownik: " + p.getImie() + " " + p.getNazwisko());
                System.out.println("Stanowisko: " + p.getStanowisko());
                System.out.println("Pensja zasadnicza: " + p.getPensja() + " zł");
                System.out.println("Dodatek stażowy:   " + p.getDodatekStazowy() + " zł");

                System.out.println("Suma do wypłaty:   " + p.getSumaWyplaty() + " zł");
            }
        }
        if (!znaleziono) System.out.println("Brak pracowników w systemie.");
        System.out.println("------------------------------------\n");
    }

    public void usunDuplikaty()
    {
        int rozmiarPrzed = osoby.size();
        HashSet<Osoba> setBezPowtorzen = new HashSet<>(osoby);
        osoby.clear();
        osoby.addAll(setBezPowtorzen);
        int usunieto = rozmiarPrzed - osoby.size();

        powiadom("Nastąpiło czyszczenie bazy. Usunięto daną ilośc duplikatów: " + usunieto);
    }

}