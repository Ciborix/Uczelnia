package uczelnia.obserwator;

import uczelnia.Dane.KontenerDanych;

public class AutoZapisz implements IObserwator{
    @Override
    public void aktualizuj(String msg, KontenerDanych kontener) {
        kontener.zapiszDaneDoPliku();
        System.out.println("[AUTO-SAVE] Zmiany zapisane [AUTO-SAVE]");
    }
}
