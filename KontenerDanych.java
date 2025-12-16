package uczelnia;
import uczelnia.osoba.*;
import uczelnia.osoba.student.*;
import uczelnia.osoba.pracownik.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class KontenerDanych implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Osoba> osoby;
    private List<Kurs> kursy = new ArrayList<>();

    public KontenerDanych()
    {
        this.osoby=new ArrayList<>();
        this.kursy=new ArrayList<>();
    }

    public void dodajOsobe(Osoba osoba){osoby.add(osoba);}
    public void usunOsobe(Osoba osoba){osoby.remove(osoba);}

    public void dodajKurs(Kurs kurs)   { this.kursy.add(kurs);  }

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
            PracownikBadawczoDydaktyczny prowadzacy,
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
                System.out.println(o.toString());
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
}
