-- Les tables, contraintes, insertions sont réalisées par ordre alphabétique
-- des tables
-- Les noms de tables composés (et tables de jointure) sont par ordre alphabétique.
-- Ex : `equipe_utilisateur` et non `utilisateur_equipe`

DROP DATABASE IF EXISTS sigpc;

CREATE DATABASE IF NOT EXISTS sigpc COLLATE utf8mb4_general_ci;

USE sigpc;

CREATE TABLE IF NOT EXISTS commentaire
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    commmentaire_id BIGINT,
    utilisateur_id  BIGINT,
    contenu         VARCHAR(255) NOT NULL,
    response_to     BIGINT
);

CREATE TABLE IF NOT EXISTS compte_rendu
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    evenement_id   BIGINT NOT NULL,
    contenu        TEXT   NOT NULL, -- TODO : varchar vs text + limitations
    date_redaction DATE DEFAULT (CURRENT_DATE)
);

CREATE TABLE IF NOT EXISTS equipe
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom         VARCHAR(60) NOT NULL,
    description VARCHAR(255),
    contact     VARCHAR(60) NOT NULL -- TODO : vérifier avec Richard 255? 300?
);

CREATE TABLE IF NOT EXISTS equipe_utilisateur
(
    -- id BIGINT AUTO_INCREMENT PRIMARY KEY, -- TODO : clé composite à ajouter
    utilisateur_id BIGINT NOT NULL,
    equipe_id      BIGINT NOT NULL,
    role           ENUM ('MEMBRE', 'PROPRIETAIRE') DEFAULT 'MEMBRE'
);

CREATE TABLE IF NOT EXISTS evenement
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    projet_id BIGINT,
    sprint_id BIGINT,
    nom       VARCHAR(60)                                       NOT NULL,
    date      DATETIME                                          NOT NULL,
    -- TODO : enum type à compléter
    type      ENUM ('RETROSPECTIVE', 'REUNION', 'REVUE_SPRINT') NOT NULL
);

CREATE TABLE IF NOT EXISTS invitation
(
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    date_invitation  DATETIME                NOT NULL      DEFAULT CURRENT_TIMESTAMP,
    type_invitation  ENUM ('INVITE', 'JOIN') NOT NULL,
    statut           ENUM ('ACCEPTED', 'OPEN', 'REJECTED') DEFAULT 'OPEN',
    date_acceptation DATETIME                NOT NULL      DEFAULT CURRENT_TIMESTAMP,
    acceptee         BOOL                                  DEFAULT FALSE,
    utilisateur_id   BIGINT,
    equipe_id        BIGINT
);

CREATE TABLE IF NOT EXISTS projet
(
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom              VARCHAR(100) UNIQUE NOT NULL,
    description      VARCHAR(512),
    date_debut       DATE                NOT NULL,
    date_fin         DATE,
    date_fin_estimee DATE,
    contact          VARCHAR(60)         NOT NULL, -- TODO à valider
    equipe_id        BIGINT
);

CREATE TABLE IF NOT EXISTS sprint
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    projet_id  BIGINT,
    date_debut DATE,
    date_fin   DATE,
    nb_jour    INT NOT NULL
);

CREATE TABLE IF NOT EXISTS tache
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_story_id BIGINT,
    date_debut    DATE,
    date_fin      DATE,
    nb_jour       INT NOT NULL
);

CREATE TABLE IF NOT EXISTS user_story
(
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    projet_id        BIGINT       NOT NULL,
    libelle          VARCHAR(100) NOT NULL,
    code             VARCHAR(255) NOT NULL, -- TODO: à préciser: c'est quoi? 255 caractères?
    description      TEXT         NOT NULL,
    date_debut       DATE,
    date_fin_estimee DATE,
    -- TODO : avancement à compléter
    avancement       ENUM ('BACKLOG', 'TODO', 'IN_PROGRESS', 'REVIEW', 'DONE') DEFAULT 'BACKLOG',
    date_fin         DATE,
    nb_jour          INT          NOT NULL
);

CREATE TABLE IF NOT EXISTS utilisateur
(
    id                       BIGINT AUTO_INCREMENT PRIMARY KEY,
    email                    VARCHAR(60)  NOT NULL,
    nom                      VARCHAR(60)  NOT NULL,
    prenom                   VARCHAR(60)  NOT NULL,
    password                 VARCHAR(60)  NOT NULL,
    activation_link          BINARY(16)   NULL,
    email_verified           BOOL                  DEFAULT FALSE,
    roles                    VARCHAR(120) NOT NULL DEFAULT '[''USER'']',
    date_echeance_suspension DATETIME              DEFAULT NULL
);


-- CONTRAINTES
-- TODO : ajouter d'autres contraintes sur d'autres tables selon besoin
ALTER TABLE compte_rendu
    ADD CONSTRAINT fk_compte_rendu_evenement
        FOREIGN KEY (evenement_id) REFERENCES evenement (id)
            ON DELETE CASCADE;

ALTER TABLE equipe_utilisateur
    ADD CONSTRAINT fk_tj_equip_util_utilisateur
        FOREIGN KEY (utilisateur_id) REFERENCES utilisateur (id)
            ON DELETE CASCADE,
    ADD CONSTRAINT fk_tj_equip_util_equipe
        FOREIGN KEY (equipe_id) REFERENCES equipe (id)
            ON DELETE CASCADE;

ALTER TABLE evenement
    ADD CONSTRAINT fk_evenement_projet
        FOREIGN KEY (projet_id) REFERENCES projet (id)
            ON DELETE CASCADE,
    ADD CONSTRAINT fk_evenement_sprint
        FOREIGN KEY (sprint_id) REFERENCES sprint (id)
            ON DELETE CASCADE;

ALTER TABLE invitation
    ADD CONSTRAINT fk_equipe_invitation
        FOREIGN KEY (equipe_id) REFERENCES equipe (id)
            ON DELETE CASCADE,
    ADD CONSTRAINT fk_invitation_utilisateur
        FOREIGN KEY (utilisateur_id) REFERENCES utilisateur (id)
            ON DELETE CASCADE;

ALTER TABLE projet
    ADD CONSTRAINT fk_equipe_projet
        FOREIGN KEY (equipe_id) REFERENCES equipe (id)
            ON DELETE CASCADE;

ALTER TABLE sprint
    ADD CONSTRAINT fk_projet_sprint
        FOREIGN KEY (projet_id) REFERENCES projet (id)
            ON DELETE CASCADE;

ALTER TABLE tache
    ADD CONSTRAINT fk_user_story_tache
        FOREIGN KEY (user_story_id) REFERENCES user_story (id)
            ON DELETE CASCADE;

ALTER TABLE user_story
    ADD CONSTRAINT fk_projet_user_story
        FOREIGN KEY (projet_id) REFERENCES projet (id)
            ON DELETE CASCADE;

# ALTER TABLE
#     ADD CONSTRAINT
#         FOREIGN KEY  REFERENCES
#             ON DELETE CASCADE;


-- TODO : insérer d'autres données dans d'autres tables selon besoin
-- INSERTION DE DONNÉES
INSERT INTO projet (nom, description, date_debut, date_fin, contact)
VALUES ('Projet 1 - SIG', NULL, '2023-04-05', NULL,
        'contact_sig@mail.com'),
       ('Projet 2 - API Magasin', 'Conception API magasin', '2024-07-18',
        '2024-10-31',
        'contact_api@mail.com'),
       ('Projet 3 - École', 'Gestion école des Bleuets', '2025-01-15',
        '2025-05-20',
        'contact_ecole@mail.com'),
       ('Projet 4 - Ecommerce', 'Site de E-commerce', '2024-02-01',
        '2027-02-01',
        'contact_ecommerce@mail.com');

INSERT INTO utilisateur (email, nom, prenom, password, email_verified, roles)
VALUES ('abelccoli@gmail.com', 'Jean', 'John', 'PWD', TRUE, '[''USER'']'),
       ('pdupre@mail.com', 'Pierre', 'Dupré', 'PWD', FALSE, '[''USER'']'),
       ('alice_harper@mail.com', 'Alice', 'Harper', 'PWD', FALSE, '[''USER'']'),
       ('jason.lee@yahoo.com', 'Jason', 'Lee', 'PWD', FALSE, '[''USER'']');
