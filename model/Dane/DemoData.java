package model.Dane;

import model.osoba.pracownik.PracownikAdministracyjny;
import model.osoba.pracownik.PracownikBadawczoDydaktyczny;
import model.osoba.student.Kurs;
import model.osoba.student.Student;
import model.strategia.BonusAdministracyjny;
import model.strategia.BonusBadawczy;

import java.util.ArrayList;
import java.util.List;

public class DemoData {

    public static KontenerDanych utworzDaneTestowe() {
        KontenerDanych kontener = new KontenerDanych();


        PracownikBadawczoDydaktyczny profesorNowak = new PracownikBadawczoDydaktyczny("Andrzej", "Nowak", "65091212345", 58, "Mężczyzna", "Profesor Zwyczajny", 32, 12500.00, 76);
        PracownikBadawczoDydaktyczny adiunktKowalska = new PracownikBadawczoDydaktyczny("Magdalena", "Kowalska", "82030567891", 42, "K", "Adiunkt", 52.0, 7800.00, 22);
        PracownikBadawczoDydaktyczny adiunktKowalskaStarsza = new PracownikBadawczoDydaktyczny("Maria", "Kowalska", "82030567891", 45, "K", "Adiunkt", 52.0, 7800.00, 22);
        PracownikAdministracyjny SpecjalistaKarol = new PracownikAdministracyjny("Karol", "Musiał", "12312312323", 42, "Mężczyzna", "Specjalista", 5.0, 4200.0, 10);
        PracownikAdministracyjny AdministracyjnyMarek = new PracownikAdministracyjny("Marek", "Nowicki", "87010378901", 38, "Mężczyzna", "Specjalista", 8.0, 4800.0, 25);
        PracownikAdministracyjny kierownikAdm = new PracownikAdministracyjny("Joanna", "Kowalczyk", "75020356789", 48, "K", "Specjalista", 20.0, 6200.0, 5);

        profesorNowak.setBonusStategia(new BonusBadawczy());
        profesorNowak.setBonusStategia(new BonusBadawczy());
        adiunktKowalska.setBonusStategia(new BonusBadawczy());
        adiunktKowalskaStarsza.setBonusStategia(new BonusBadawczy());

        SpecjalistaKarol.setBonusStategia(new BonusAdministracyjny());
        AdministracyjnyMarek.setBonusStategia(new BonusAdministracyjny());
        kierownikAdm.setBonusStategia(new BonusAdministracyjny());

        Kurs k1 = new Kurs("OSK", "Nowak", 4);
        Kurs k2 = new Kurs("ALGEBRA", "Kowalska", 4);

        List<Kurs> kursyList = new ArrayList<>();
        kursyList.add(k1);
        kursyList.add(k2);


        kontener.dodajKurs(k1);
        kontener.dodajKurs(k2);


        Student student1 = new Student("Piotr", "Kowalski", "02121212345", 20, "Mężczyzna", 12345, 1, kursyList,false, true, false, true, false);
        Student student2 = new Student("Anna", "Kwiatkowska", "03221298765", 21, "K", 23456, 2, kursyList,false, true, false, true, false);

        kontener.dodajOsobe(profesorNowak);
        kontener.dodajOsobe(adiunktKowalska);
        kontener.dodajOsobe(adiunktKowalskaStarsza);
        kontener.dodajOsobe(SpecjalistaKarol);
        kontener.dodajOsobe(AdministracyjnyMarek);
        kontener.dodajOsobe(kierownikAdm);
        kontener.dodajOsobe(student1);
        kontener.dodajOsobe(student2);


        System.out.println("Kontener danych demonstracyjnych został utworzony.");

        return kontener;
    }
}