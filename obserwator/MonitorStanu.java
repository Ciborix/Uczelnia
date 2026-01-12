package uczelnia.obserwator;

import uczelnia.Dane.KontenerDanych;

public class MonitorStanu implements IObserwator{
    @Override
    public void aktualizuj(String msg, KontenerDanych kontener) {
        int liczbaosów = kontener.getOsoby().size();
        int liczbakursow = kontener.getKursy().size();

        System.out.println("[STAN]: W systemie snajduje się " + liczbaosów + " osób oraz tyle kursów: " + liczbakursow + " [STAN]" + '\n' );
    }
}
