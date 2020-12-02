package org.ciclo.model;

public class SongMySQLPRUEBA {

    enum queries {
        INSERT("INSERT INTO artista (Nombre,Nacionalidad,Foto) VALUES (?,?,?)"),
        ALL("SELECT * FROM artista"),
        GETBYID("SELECT * FROM artista WHERE ID=?"),
        FINDBYNAME("SELECT * FROM artista WHERE Nombre LIKE ?"),
        UPDATE("UPDATE artista SET Nombre = ?, Nacionalidad = ?, Foto = ? WHERE ID = ?"),
        REMOVE("DELETE FROM artista WHERE ID=?");
        private String q;

        queries(String q) {
            this.q = q;
        }

        public String getQ() {
            return this.q;
        }
    }
}
