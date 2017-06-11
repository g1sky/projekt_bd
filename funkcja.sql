CREATE OR REPLACE FUNCTION add_to_cart
   (id_towaru IN INTEGER,
   ilosc IN NUMBER,
   kupiec IN VARCHAR)
RETURN BOOLEAN

AS

id_transakcji INTEGER;
sprzedawca VARCHAR(40);
cena_jednostkowa NUMBER;

BEGIN
    SELECT t.wlasciciel INTO sprzedawca -- poszukiwanie wlasciciela
      FROM g1_sgorski.towar t
      WHERE t.id = id_towaru;
    SELECT o.cena_jednostkowa into cena_jednostkowa -- poszukiwanie ceny
      FROM g1_sgorski.oferta o
      WHERE o.id_towaru = id_towaru;
    BEGIN -- poszukiwanie koszyka
      SELECT id into id_transakcji
        FROM g1_sgorski.transakcja t
        WHERE t.sprzedawca = sprzedawca AND t.kupiec = kupiec AND t.stan = 0;
    EXCEPTION
      WHEN NO_DATA_FOUND THEN -- nie znalazl, wiec trzeba go dodac
        INSERT INTO g1_sgorski.transakcja (kupiec, sprzedawca, stan)
          VALUES (kupiec, sprzedawca, 0);
        SELECT t.id into id_transakcji -- i jeszcze raz szukamy
          FROM g1_sgorski.transakcja t
          WHERE t.sprzedawca = sprzedawca AND t.kupiec = kupiec AND t.stan = 0;
    END;
    BEGIN -- teraz dodawanie/aktualizacja towaru
      INSERT INTO g1_sgorski.towar_w_transakcji (id_transakcji, id_towaru, ilosc, cena_jednostkowa)
          VALUES (id_transakcji, id_towaru, ilosc, cena_jednostkowa);
    EXCEPTION
      WHEN DUP_VAL_ON_INDEX THEN -- jesli juz istnieje to tylko update
        UPDATE g1_sgorski.towar_w_transakcji
        SET ilosc = ilosc, cena_jednostkowa = cena_jednostkowa;
    END;
RETURN TRUE;
EXCEPTION
  WHEN NO_DATA_FOUND THEN-- nie ma uzytkownika, ktory posiada towar o takim id lub nie ma takiego towaru, wiec trudno
    RETURN FALSE;
END add_to_cart;
