# SIGPC API

API créee avec Java / Spring boot.

Permet d'effectuer du CRUD pour une application de gestion des différentes phases d'un
projet agile

## Auteurs

- CICCOLI Abel
- MONTCHO Gildas
- VINCKIER Nicolas

## Commandes setup BDD

```bash
docker volume create sigpc-database

docker run -d -v sigpc-database:/var/lib/mysql --name sigpc-database -p 3307:3306 --env MARIADB_ROOT_PASSWORD=pazo1928! -e MARIADB_DATABASE=sigpc -e MARIADB_PASSWORD=pazo1928! -e  MARIADB_USER=sigpc-user mariadb
```
