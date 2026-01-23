package model.osoba.student;


import model.osoba.Osoba;
import model.osoba.PrzedstawSie;

import java.util.List;
import java.util.Objects;

public class Student extends Osoba implements PrzedstawSie {
    private int nrIndeks;
    private int rokStudiow;
    private List<Kurs> kursList;
    private boolean czyERASMUS;
    private boolean czyIStopien;
    private boolean czyIIStopien;
    private boolean czyStacjonarny;
    private boolean czyNieStacjonarny;

    public Student(String imie, String nazwisko, String pesel, int wiek, String plec
    , int nrIndeks, int rokStudiow, List<Kurs> kursList, boolean czyERASMUS, boolean czyIStopien, boolean czyIIStopien
   , boolean czyStacjonarny, boolean czyNieStacjonarny) {
        super(imie, nazwisko, pesel, wiek, plec);
        this.nrIndeks = nrIndeks;
        this.rokStudiow = rokStudiow;
        this.kursList = kursList;
        this.czyERASMUS = czyERASMUS;
        this.czyIStopien = czyIStopien;
        this.czyIIStopien = czyIIStopien;
        this.czyStacjonarny = czyStacjonarny;
        this.czyNieStacjonarny = czyNieStacjonarny;
    }

    public int getNrIndeks() {return nrIndeks;}
    public int getRokStudiow() {return rokStudiow;}
    public List<Kurs> getKursyList() {return kursList;}
    public boolean isCzyERASMUS() {return czyERASMUS;}
    public boolean isCzyIStopien() {return czyIStopien;}
    public boolean isCzyIIStopien() {return czyIIStopien;}
    public boolean isCzyStacjonarny() {return czyStacjonarny;}
    public boolean isCzyNieStacjonarny() {return czyNieStacjonarny;}

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append(super.toString()).append("\n");
        sb.append("Numer indeksu: ").append(getNrIndeks()).append("\n");
        sb.append("Rok studiów: ").append(getRokStudiow()).append("\n");


        sb.append("Stopień: ");
        if (isCzyIStopien()) sb.append("I stopień");
        else if (isCzyIIStopien()) sb.append("II stopień");
        else sb.append("nieznany");
        sb.append("\n");


        sb.append("Tryb: ");
        if (isCzyStacjonarny()) sb.append("stacjonarne");
        if (isCzyNieStacjonarny()) sb.append(" niestacjonarne");
        sb.append("\n");


        sb.append("Erasmus: ").append(isCzyERASMUS() ? "tak" : "nie").append("\n");
        if (getKursyList() == null || getKursyList().isEmpty()) {
            sb.append("brak");
        } else {
            for (Kurs k : getKursyList()) {
                sb.append(k.toString()).append(", ");
            }

            sb.setLength(sb.length() - 2);
        }

        return sb.toString();
    }

    @Override
    public String przedstawSie() {
        return "Student: " + getImie() + " " + getNazwisko() + " Indeks: " + getNrIndeks();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return nrIndeks == student.nrIndeks;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nrIndeks);
    }
}
