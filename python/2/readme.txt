Adott a cs1.json, ami tartalmazza egy ir�ny�tatlan gr�f le�r�s�t. A gr�f v�gpont (end-points) �s switch (switches) csom�pontokat
tartalmaz. Az �lek (links) kapacit�ssal rendelkeznek (val�s sz�m). Tegy�k fel, hogy egy �ramk�rkapcsolt h�l�zatban vagyunk �s
valamilyen RRP-szer� er�forr�s foglal� protokollt haszn�lunk. Feltessz�k, hogy csak a linkek megosztand� �s sz�k er�forr�sok. A
json tartalmazza a kialak�that� lehets�ges �tvonalakat (possible-cicuits), tov�bb� a rendszerbe be�rkez�, k�t v�gpontot �sszek�t�
�ramk�rig�nyeket kezd� �s v�g id�ponttal. A szimul�ci� a t=1 id�pillanatban kezd�dik �s t=duration id�pillanatban �r v�get.
K�sz�ts programot, ami leszimul�lja az er�forr�sok lefoglal�s�t �s felszabad�t�s�t a JSON f�jlban megadott topol�gia, kapacit�sok
�s ig�nyek alapj�n!
Script param�terez�se: python3 program.py <cs1.json>