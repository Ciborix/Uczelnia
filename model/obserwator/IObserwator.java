package model.obserwator;


import model.Dane.KontenerDanych;

public interface IObserwator {
    void aktualizuj(String msg, KontenerDanych kontener);
}
