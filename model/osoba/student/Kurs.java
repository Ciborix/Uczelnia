package model.osoba.student;


import java.io.Serializable;

public class Kurs implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nazwa;
    private String prowadzacy; //mozliwosc zmiany na zwyklego stringa
    private int punktyECTS;

    public Kurs(String nazwa, String prowadzacy, int punktyECTS) {
        this.nazwa = nazwa;
        this.prowadzacy = prowadzacy;
        this.punktyECTS = punktyECTS;
    }


    public String getNazwa() {return nazwa;}
    public String getProwadzacy() {return prowadzacy;}
    public int getPunktyECTS() {return punktyECTS;}

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n--------Kurs-----------\n");
        sb.append("Nazwa: ").append(getNazwa()).append("\n");
        sb.append("Prowadzacy: ").append(getProwadzacy()).append("\n");
        sb.append("Punkty ECTS: ").append(getPunktyECTS()).append("\n");

        return sb.toString();
    }


    public String wyswietlDane()
    {return "Nazwa: " + getNazwa() + " " + "Proawdzacy: " + getProwadzacy() + " Punkty ECTS: " + getPunktyECTS();}
}
