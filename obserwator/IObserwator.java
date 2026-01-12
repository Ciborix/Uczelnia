package uczelnia.obserwator;

import uczelnia.Dane.KontenerDanych;

public interface IObserwator {
    void aktualizuj(String msg, KontenerDanych kontener);
}
