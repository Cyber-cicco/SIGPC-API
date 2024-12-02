create database sigpc;
create user 'sigpc'@'localhost' IDENTIFIED BY 'Sigpc1234!';
grant all privileges on sigpc.* to 'sigpc'@'localhost';