# Saadi ERP

## Live-Umgebung

// To be done

## Setup

### Backend

- Projekt oder Backend-Ordner in IntelliJ öffnen oder als Maven-Projekt importieren.
- Es sollten automatisch zwei Run-Configurations zur Auswahl stehen (`Run Backend H2` und `Run Backend Mysql`).
  - Zum Entwickeln mittels H2 einfach starten. Das Backend startet standardmäßig auf Port 8080.
  - Zum Entwickeln mittels Mysql muss ein Mysql-Server (bspw. mit XAMPP) laufen. Standardmäßig wird versucht, eine Verbindung mit `mysql://root@localhost:3306/saadi` herzustellen (User: root, leeres Passwort, Datenbankname: saadi). Siehe Kapitel zu Env-Files für mehr Infos.
- Sollten die Run-Configurations nicht zur Auswahl stehen, dann manuell eine neue "Spring-Boot"-Run-Configuration anlegen oder in der Klasse `ErpApplication` auf den grünen Play-Button drücken.
  - Standardmäßig wird dabei das Backend mit einer H2-Datenbank gestartet.
  - Um diese auf Mysql zu ändern, in der erstellten Run-Configuration beim Feld `Active Profiles` `mysql` eintragen und neu starten.

### Frontend

- Projekt oder Frontend-Ordner in Webstorm oder IntelliJ (mit VueJS Plugin) öffnen
- Im Terminal zum Frontend-Ordner navigieren und `npm install` ausführen oder alternativ die `package.json` öffnen und den Vorschlag `Install Dependencies` bestätigen.
- Zum Starten der Entwicklungsumgebung im Terminal im Frontend-Ordner `npm run serve` ausführen oder die `package.json` öffnen und auf den grünen Play-Button neben dem Serve-Script drücken.
- Standardmäßig startet der Entwicklungsserver auf Port 8081. API-Requests werden während der Entwicklung automatisch auf Port 8080 umgeleitet.

### Env-Files

Die Zugangsdaten zur Datenbank, Ports und dergleichen können sich je nach verwendetem Gerät unterscheiden und/oder sollen geheim bleiben. Da bspw. das eigene Mysql-Passwort andere Geräte und Entwickler_innen nicht beeinflussen soll, können projektweite Variablen angelegt werden, die anschließend Java oder Vue auslesen kann. Dies macht man mit einer `.env`-Datei, die von Git ausgeschlossen ist. Um daher Configs anzupassen, erstellt man manuell eine solche `.env`-Datei im Hauptverzeichnis und fügt die eigenen Werte ein. 

Als Übersicht, welche Variablen im Code verwendet wurden, gibt es die `.env.example`-Datei, welche von Java/Vue/co. ignoriert wird und auch keine Werte enthält, aber dafür alle Variablen, die irgendwo verwendet werden. Möchte man also bspw. den Datenbanknamen ändern, kopiert man sich die entsprechende Variable aus der `.env.example`-Datei in die eigene `.env`-Datei und fügt den entsprechenden Datenbanknamen als Wert ein.

Für mehr Informationen, siehe [hier](https://blog.devgenius.io/what-are-env-files-and-how-should-i-use-them-and-why-5fdb1d228016).

## Codestyle

### API

Prinzipiell **müssen** alle Backend-Routes mit dem Prefix `/api` beginnen. Also bspw. `/api/users` oder `/api/products`, usw. Das hat den Hintergrund, dass zwar beim Entwickeln das Backend und Frontend auf unterschiedlichen Ports laufen und somit dadurch eine Unterscheidung gegeben ist, beim Live-Server aber beides vereint wird. Würde man daher auf den API-Prefix verzichten und würde `/products` aufrufen, dann wüsste der Server nicht, ob damit Frontend-Seite oder Backend-Request gemeint ist. Mit dem Prefix erzeugt man daher die notwendige Unterscheidung und ist auf der sicheren Seite.  

Die API ist als klassische Rest-API zu erstellen und warten. Das bedeutet, dass der Pfad nur auf eine Ressource verweisen soll und die HTTP-Methode angibt, was mit dieser passieren soll. Pfade auf Ressourcen sind i.d.R. im Plural zu halten.

Folgende Beispiele:

| Pfad                         | Methode | Info                                                                                                                                 |
|------------------------------|---------|--------------------------------------------------------------------------------------------------------------------------------------|
| /api/stores                  | Get     | Gibt alle Stores aus                                                                                                                 |
| /api/stores                  | Post    | Erstellt einen neuen Store                                                                                                           |
| /api/stores/1                | Get     | Gibt den Store mit der ID 1 zurück. Gibt es keinen Store mit der ID 1, dann wird ein Error 404 returned                              |
| /api/stores/1                | Put     | Ändert den Store mit der ID 1. Put erwartet als Body das gesamte Store-Object inkl. Daten, die sich nicht geändert haben             |
| /api/stores/1                | Patch   | Ändert den Store mit der ID 1. Patch erwartet als Body nur jene Informationen, die geändert werden sollen                            |
| /api/stores/1                | Delete | Löscht den Store mti der ID 1                                                                                                        |
| /api/stores/1/products       | Get | Gibt alle Products zurück, welche der Store mit der ID 1 hat                                                                         |
| ~~/api/stores/1/products~~   | Get | Sofern Produkte keinen zusammengesetzten Primary-Key haben, die abhängig von der Store-ID wären, ist `/api/products` zu bevorzugen                                                                         |
| ~~/api/stores/1/products/1~~ | Get | Sofern Produkte keinen zusammengesetzten Primary-Key haben, die abhängig von der Store-ID wären, ist `/api/products/1` zu bevorzugen |

Für mehr Informationen zum Unterschied zwischen Put/Patch, siehe [hier](https://www.baeldung.com/http-put-patch-difference-spring). Wann die gesamte Ressource zu ersetzen ist oder nur teilweise Änderungen geschickt werden, ist in Absprache im Team, insb. zwischen Frontend/Backend zu klären. Meistens wird Put bevorzugt, da es den Aufwand sowohl im Backend als auch im Frontend reduziert. Patch wird dagegen eher verwendet, wenn es um sensible Themen wie Passwörter geht, oder Berechnungen im Backend angestoßen werden sollen ("erhöhe etwas vom Wert, den es aktuell hat" anstatt "setze etwas auf Wert x").

Mit dem HTTP-Code soll dem Frontend signalisiert werden, wie eine Anfrage ausgegangen ist und/oder ob es zu einem Fehler kam. Diese können im Frontend auch entsprechend ausgelesen werden und abhängig davon Erfolgs- und Fehlermeldungen anzeigen.
- 200 "Ok"; alles passt
- 201 "Created"; eine Ressource wurde erfolgreich erstellt. Ist meistens bei Post-Requests der Fall.
- 204 "No Content"; alles passt, aber die Response ist (geplant) leer. Ist meistens bei Delete-Requests der Fall. 
- 400 "Bad request"; wenn der Request-Body fehlerhaft ist (Properties fehlen, ein Property hat falschen Datentyp, ...). Macht Spring i.d.R. automatisch
- 401 "Unauthorized"; wenn man für einen Request eingeloggt sein muss, es aber nicht ist
- 404 "Not found"; wenn die angefragte Ressource nicht gefunden wurde
- 500 "Internal Server Error"; wenn es im Backend zu einem Fehler kam und dieser nicht im Frontend liegt. Kommt standardmäßig, wenn eine Exception nicht mittels try/catch abgefangen wurde

Eine gesamte Auflistung aller Status-Codes gibt es [hier](https://developer.mozilla.org/en-US/docs/Web/HTTP/Status) zu finden. Um zu bestimmen, welcher Status-Code von Spring an das Frontend zurückgeschickt werden soll, gibt es die `ResponseStatusException`. Mittels try/catch kann eine Controller-Method verschiedene Exceptions abfangen und stattdessen eine `ResponseStatusException` werfen. Siehe folgendes Beispiel: 

```java
@PostMapping("/api/user")
public UserDto createUser(@RequestBody() NewUserDto newUser) {
    try {
        userService.createUser(newUser);
    } catch (ConstraintViolationException exception) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, "Diese E-Mail Adresse wird bereits verwendet", exception);
    }
}
```

Für mehr Informationen zu allgemeinem API-Design siehe [hier](https://restfulapi.net/).

### Code

Prinzipiell sollen alle Variablen sinnvoll benannt werden, sodass durch deren Namen klar ist, wofür diese Verwendet wird. Bei der Groß-/Kleinschreibung soll klassisches camelCase bei Variablen & Methoden und UpperCamelCase bei Klassen (und Components im Frontend) verwendet werden. 

Bei Methoden und Funktionen soll durch deren Namen klar sein, was sie machen bzw. returnen.

Die Kommunikation bei Controllern (Request-Body, Response, ...) soll immer mit DTOs stattfinden. Auch wenn jene DTOs teilweise die gleichen Properties wie die dazugehörigen Entities, ist es im Hinblick auf Bug-Vermeidung und Wartbarkeit sinnvoll. Damit kann man auch kontrollieren, welche Beziehungen mitgeladen werden. Bspw. braucht es bei einem Get auf `/api/stores` nicht bei allen Stores auch gleich alle Produkte, sondern erst bei einem Get auf `/api/stores/1` soll der Store mit der ID 1 und alle dessen Produkte geladen werden. Ohne DTOs ist das nicht umsetzbar.

### Git Workflow

Als Hauptbranch soll der "develop"-Branch dienen. In diesem soll auch immer eine lauffähige Version sein, die im Idealfall auch schon getestet ist. 

Wenn man an einem Feature arbeitet, dann soll dies in einem eigenen Branch geschehen. Zwecks übersichtlichkeit ist dieser mit den eigenen Initialien geprefixed und einem Slash getrennt werden. Also bspw. `DS/implementLocationManagement`. Ist dieses Feature fertig entwickelt und funktionsfähig, dann wird zunächst lokal der develop-Branch in den Feature-Branch gemerged werden und anschließend der Feature-Branch in den develop-Branch. Dies verhindert, dass sich versehentlich Merge-Conflicts in den develop-Branch einschleichen können.

Ist ein Meilenstein erreicht, dann ist ein Merge-Requests vom develop-Branch in den master-Branch über Gitlab zu erstellen. Rechte, um diesen zu mergen, haben der Sebi und David. Sobald der Merge-Request gemerged ist, wird der Stand automatisch auf den Live-Server gespielt und ist dann öffentlich aufrufbar.

Für weitere Informationen zum Branching-Modell, siehe [hier](https://nvie.com/posts/a-successful-git-branching-model/).