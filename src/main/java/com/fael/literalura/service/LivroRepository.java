package com.fael.literalura.service;

import com.fael.literalura.models.Autor;
import com.fael.literalura.models.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    @Query("SELECT l.autores FROM Livro l WHERE l.autores.name = :nome")
    Autor findByAutoresName(@Param("nome") String nome);

    @Query("SELECT l FROM Livro l WHERE l.idioma =:idioma")
    List<Livro> livroPorIdioma(String idioma);
}
