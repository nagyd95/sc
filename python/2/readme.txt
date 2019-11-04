Adott a cs1.json, ami tartalmazza egy irányítatlan gráf leírását. A gráf végpont (end-points) és switch (switches) csomópontokat
tartalmaz. Az élek (links) kapacitással rendelkeznek (valós szám). Tegyük fel, hogy egy áramkörkapcsolt hálózatban vagyunk és
valamilyen RRP-szerû erõforrás foglaló protokollt használunk. Feltesszük, hogy csak a linkek megosztandó és szûk erõforrások. A
json tartalmazza a kialakítható lehetséges útvonalakat (possible-cicuits), továbbá a rendszerbe beérkezõ, két végpontot összekötõ
áramkörigényeket kezdõ és vég idõponttal. A szimuláció a t=1 idõpillanatban kezdõdik és t=duration idõpillanatban ér véget.
Készíts programot, ami leszimulálja az erõforrások lefoglalását és felszabadítását a JSON fájlban megadott topológia, kapacitások
és igények alapján!
Script paraméterezése: python3 program.py <cs1.json>