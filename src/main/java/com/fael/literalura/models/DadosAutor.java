package com.fael.literalura.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.Year;
@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAutor(@JsonAlias("name") String name,
                         @JsonAlias("birth_year") String anoNascimento,
                         @JsonAlias("death_year") String anoMorte) {
}
