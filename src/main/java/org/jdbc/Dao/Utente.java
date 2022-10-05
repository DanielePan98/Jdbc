package org.jdbc.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Utente {
    private int id;
    private String nome;
    private String cognome;

    public Utente(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
    }
}