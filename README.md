# Instructions
Gotowy projekt jest w defaultowym branchu nolocal
1. mvn package tworzy w katalogu target 2 pliki wykonywalne:
   1. halma-game-server.jar
   2. halma-game-client.jar
2. Uruchamiamy serwer podając w pierwszym argumencie port, a w drugim liczbę graczy: java -jar target/halma-game-server.jar 9999 2
3. Uruchamiamy klienta podając w pierwszym argumencie adres hosta, a w drugim port: java -jar target/halma-game-client.jar localhost 9999
4. Przy dołączeniu klienta do gry wyświetla się jaki otrzyał kolor pionków.
5. Gracz jest informowany o swoim ruchu komunikatem "Your turn"
6. W grze w swoim ruchu podajemy dwa argumenty
  1. Numer pola na którym stoi pionek który chcemy przesunąć
  2. Numer pola na który chcemy przesunąć pionek z argumentu pierwszego
