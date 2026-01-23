package obserwator;


import Dane.KontenerDanych;

public interface IObserwator {
    void aktualizuj(String msg, KontenerDanych kontener);
}
