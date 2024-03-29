# Alkalmazás futtatása
A projekt egy klasszikus háromrétegű alkalmazást tartalmaz Java Spring backenddel, REST webszolgáltatásokkal, MariaDB 
adatbázissal. Egy futónaplót modellez. Az SQL adatbázis kezelő réteg Spring Data JPA-val van megvalósítva. Az adatbázis
migrációt Flyway végzi. Az alkalmazás működését főleg integrációs tesztekkel (WebClient tesztek) ellenőriztem,
több mint 80%-os lefedettséget értem el.
A cél egy olyan alkalmazás létrehozása volt, ami futónaplóként is funkcionálhat.

Az alkalmazásunk lefordítása mavennel (a JAR állomány elkészítése):
```shell
mvn clean package 
```

Az alkalmazásból a docker image elkészítése:
```shell
docker build -t image neve .
```

A létrehozott docker image a következő Docker paranccsal indítható:

```shell
docker run -d -e SPRING_DATASOURCE_URL=jdbc:mariadb://vizsgaremek-net-mariadb/vizsgaremek -p 8080:8080 --network vizsgaremek-net spring-vizsgaremek
```

MariaDB adatbázis elindítása docker segítségével a következőképpen történik a 3307-es porton:
```shell
docker run -d -e MYSQL_DATABASE=vizsgaremek -e MYSQL_USER=remek -e MYSQL_PASSWORD=remek -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -p 3307:3306 --network vizsgaremek-net --name test-mariadb mariadb
```