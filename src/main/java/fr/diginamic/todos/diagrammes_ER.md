# Modifications à apporter aux diagrammes entité-relation

## Général

- [ ] Se référer au fichier `./sql/sigpc-db.sql` pour voir les tailles des
  champs
- [ ] Attention aux champs trop longs, par exemple 255 caractères pour un
  nom/prénom
-

## Equipe

- [ ] Modifier champ `contact VARCHAR(50) NOT NULL`

## Projet

- [ ] Ajouter champ `description TEXT(512)`, nullable
-

## Sprint

### Relation

- [ ] `sprint` 1..1 <-> 0..N `evenement`

## Exemple à recopier pour adaptation selon votre cas

- [ ] Faire XYZ ``, ...
- 