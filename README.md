# projekt_bd
## Wymagania:
### 3: 4 encje, 1 wiele-wielu, prosta apka z oknem nawigacji
### 4: diagramy przepływu, jakieś powiązania, procedury składowane, wyzwalacz(?)
### 5: diagram przepływu z jedną podwarstwą, procedury składowane z sekwencją(?), jakaś funkcja z poziomu javy, transakcje


## Z USOS'a:
### Na ocenę 3:
- [x] Mało skomplikowany diagram logiczny (min. 4 encje, jedna zależność wiele do wielu, poprawnie wypełnione zakładki "ogólne", atrybuty, poprawne kierunki relacji)
- [x] Opanowana umiejętność przekształcania diagramu logicznego w relacyjny
- [x] prosty wyzwalacz (np. logujący wykonanie zmiany w jakiejś tabeli - kiedy, rodzaj zmiany, itp)
- [x] prościutka aplikacja bazodanowa zawierająca możliwość wprowadzania/przeglądania danych bezpośrednio do/z tabel z diagramu logicznego (pomijając tabele wygenerowane później) oraz jedno okno nawigacyjne umożliwiające otwieranie okien wprowadzania; aplikacja nie musi obsługiwać okien wprowadzania hasła - może mieć funkcję logowania "zaszytą" w kodzie
### Na ocenę 4:
- [x] spełnienie kryteriów na 3 oraz:
- [x] w diagramie logicznym poprawnie nazwane (nazwy wyświetlone) relacje pomiędzy encjami, poprawnie ustawione ogrniczenia), diagram relacyjny, który bez błędów generuje poprawny DDL
- [x] bardziej skomplikowany wyzwalacz, który ma przynajmniej dwie instrukcje warunkowe
- [x] prosty diagram przepływu jedynie z prostymi procesami
- [x] aplikacja, która ma min. 1 okno na którym w zależności od wybranej krotki z jednej tabeli wyświetli wszystkie powiązane
### Na ocenę 5:
- [x] rozbudowany diagram przepływu z min. dwoma złożonymi "procesami"
- [x] każda z tabel ma wykorzystywać wbudowane sekwencje do autonumeracji identyfikatorów (jeżeli używają "sztucznego" id jako klucza głównego)
- [x] w kodzie jdbc użyte są nie tylko odwołania SQL do danych, ale także zarządzanie transakcjami
- [x] wykorzystanie w kodzie jdbc wbudowanej funkcji
- [x] aplikacja "ma ręce i nogi" - faktycznie spełnia diagramy przepływu, ma estetyczne GUI, reszta wedle wybranego projektu
