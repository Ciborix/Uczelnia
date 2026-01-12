package uczelnia.obserwator;

import uczelnia.Dane.KontenerDanych;

public class KonsolaInformator implements IObserwator{
    @Override
    public void aktualizuj(String msg, KontenerDanych kontener) {
        System.out.println("[POWIADOMIENIE]" + msg);
    }
}
