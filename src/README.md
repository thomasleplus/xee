# Source

Maven sources for `xee`, an infosec demo about XML External Entity (XXE)
processing.

- `main/java/org/leplus/infosec/xee/SecureXML.java` — parsing configured to
  prevent XXE.
- `test/java/org/leplus/infosec/xee/TestSecureXML.java` — JUnit tests that
  exercise the safe vs. unsafe behaviour.

Educational/security-research use. Build with `./mvnw test`.
