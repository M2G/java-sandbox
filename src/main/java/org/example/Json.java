package org.example;

import jdk.incubator.json.*;

import java.util.Map;

    // Démo de l'API JSON simple du JDK (JEP 540, incubator).
    // Compilation : javac --add-modules jdk.incubator.json Json.java
    // Exécution   : java  --add-modules jdk.incubator.json Json
    public class Json {
        public static void main(String[] args) {
            String body = """
            {
              "service": "web_server",
              "id": 3,
              "active": true,
              "ports": [8080, 8443],
              "owner": { "name": "Matt", "team": "infra" }
            }
            """;

            // Parsing strict (RFC 8259)
            JsonValue json = Json.parse(body);

            // Navigation + conversions as...()
            int firstPort = json.get("ports").get(0).asInt();
            boolean active = json.get("active").asBoolean();
            long id = json.get("id").asLong();
            System.out.println("id=" + id + " active=" + active + " port=" + firstPort);

            // Pattern matching sur la hiérarchie scellée
            if (json.get("service") instanceof JsonString s) {
                System.out.println("service=" + s.asString());
            }

            // Membre optionnel : tryGet renvoie un Optional
            String nickname = json.get("owner")
                    .tryGet("nickname")
                    .map(Object::toString)
                    .orElse("(aucun)");
            System.out.println("nickname=" + nickname);

            // Erreurs : JsonValueException (mauvais accès) et JsonParseException (syntaxe)
            try {
                json.get("inexistant");
            } catch (JsonValueException e) {
                System.out.println("Membre absent : " + e.getMessage());
            }

            try {
                Json.parse("{\"a\": 1,}"); // virgule finale : rejetée
            } catch (JsonParseException e) {
                System.out.println("JSON invalide : " + e.getMessage());
            }

            // Génération : les valeurs doivent être enveloppées via les factories of(...)
            JsonObject document = JsonObject.of(Map.of(
                    "service", JsonString.of("web_server"),
                    "id", JsonNumber.of(3),
                    "active", JsonBoolean.of(true)
            ));
            System.out.println(document);                    // JSON compact
            System.out.println(Json.toDisplayString(document)); // JSON mis en forme
        }
    }