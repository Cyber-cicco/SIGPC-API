# SIGPC API

Api créee avec Java / Spring boot.

Permet d'effectuer du CRUD pour une application de gestion des différentes phases d'un
projet agile

## Auteurs

MONTCHO Gildas, VINCKIER Nicolas, CICCOLI Abel

## Commandes setup BDD

docker volume create sigpc-database
docker run -ti -v sigpc-database:/var/lib/mysql --name sigpc-database -p 3307:3306 --env MARIADB_ROOT_PASSWORD=pazo1928! -e MARIADB_DATABASE=sigpc -e  MARIADB_USER=sigpc-user mariadb
