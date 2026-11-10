# TP2 — Architecture microservices Spring Cloud (pages 15 à 33)

Projet Maven Java 17 / Spring Boot 3.1.5 / Spring Cloud 2022.0.4. Ouvrir le dossier `TP2` dans IntelliJ IDEA et importer les cinq modules Maven (chaque sous-dossier possède un `pom.xml`). Connexion Internet requise pour télécharger les dépendances.

## Démarrage (dans cet ordre)
1. `discovery-service` : http://localhost:8761
2. `config-service` : http://localhost:8888/customer-service/default
3. `gateway-service` : http://localhost:9999
4. `customer-service` : http://localhost:8084/customers
5. `account-service` : http://localhost:8083/api/accounts

Dans IntelliJ, ouvrir chaque classe `*Application.java` et cliquer sur Run. Attendre que customer-service soit UP avant account-service pour initialiser les comptes. La configuration est fournie localement par config-service (profil native), sans dépendre d'un dépôt GitHub externe.

## Liens de test
- Eureka : http://localhost:8761
- Clients : http://localhost:8084/customers
- Client 1 : http://localhost:8084/customers/1
- Configuration : http://localhost:8084/configTest
- Comptes : http://localhost:8083/api/accounts
- Compte détaillé : copier un `id` depuis la liste et ouvrir `http://localhost:8083/api/accounts/ID`
- Via Gateway : http://localhost:9999/CUSTOMER-SERVICE/customers et http://localhost:9999/ACCOUNT-SERVICE/api/accounts
- Swagger clients : http://localhost:8084/swagger-ui.html

## Compilation
Dans chaque sous-dossier : `mvn clean package -DskipTests` (ou utiliser Maven dans IntelliJ). Les dossiers `target` sont volontairement exclus du ZIP.

## Remarques
- Les données H2 sont en mémoire et réinitialisées au redémarrage.
- Le fallback Resilience4j est appliqué au contrôleur des comptes détaillés, en cas d'indisponibilité du service client.
- La compilation et les tests d'intégration n'ont pas pu être exécutés dans l'environnement de génération (Maven absent). Vérifier les cinq démarrages sur votre ordinateur.
