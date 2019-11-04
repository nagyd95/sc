K�sz�ts�nk egy bark�ba alkalmaz�st. A szerver legyen k�pes kiszolg�lni
t�bb klienst. A szerver v�lasszon egy eg�sz sz�mot 1..100 k�z�tt
v�letlenszer�en. A kliensek pr�b�lj�k kital�lni a sz�mot.
� A kliens �zenete egy �sszehasonl�t� oper�tor: <, >, = �s egy eg�sz sz�m,
melyek jelent�se: kisebb-e, nagyobb-e, mint az eg�sz sz�m, illetve
r�k�rdez a sz�mra. A k�rd�sekre a szerver Igen/Nem/Nyert�l/Kiest�l/V�ge
�zenetekkel tud v�laszolni. A Nyert�l �s Kiest�l v�laszok csak a r�k�rdez�s
(=) eset�n lehets�gesek.
� Ha egy kliens kital�lta a sz�mot, akkor a szerver minden �jabb kliens
�zenetre az �V�ge� �zenetet k�ldi, amire a kliensek kil�pnek. A szerver
addig nem v�laszt �j sz�mot, am�g minden kliens ki nem l�pett.
� � Nyert�l, Kiest�l �s V�ge �zenet fogad�sa eset�n a kliens bontja a
kapcsolatot �s termin�l. Igen/Nem eset�n folytatja a k�rdezget�st.
� A kommunik�ci�hoz TCP-t haszn�ljunk!
A kliens logaritmikus keres�s seg�ts�g�vel tal�lja ki a gondolt sz�mot. A
kliens tudja, hogy milyen intervallumb�l v�lasztott a szerver.
� AZAZ a kliens NE a standard inputr�l dolgozzon.
� Minden k�rd�s k�ld�se el�tt v�letlenszer�en v�rjon 1-5 mp-et. Ezzel t�bb
kliens tesztel�se is lehets�ges lesz
�zenet form�tum:
� Klienst�l: bin�ris form�ban egy db karakter, 32 bites eg�sz sz�m
A karakter lehet: <: kisebb-e, >: nagyobb-e, =: egyenl�-e
� Szervert�l: ugyanaz a bin�ris form�tum, de a sz�mnak nincs szerepe (b�rmi
lehet)
A karakter lehet: I: Igen, N: Nem, K: Kiest�l, Y: Nyert�l, V: V�ge
� F�jlnevek �s parancssori argumentumok:
� Szerver: server.py <bind_address> <bind_port> # A bindol�s sor�n
haszn�lt p�r
� Kliens: client.py <server_address> <server_port> # A szerver el�rhet�s�ge