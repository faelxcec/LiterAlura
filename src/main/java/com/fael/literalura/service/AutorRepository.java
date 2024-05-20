package com.fael.literalura.service;

import com.fael.literalura.models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Autor findByName(String name);
   @Query("SELECT a FROM Autor a WHERE a.anoNascimento <= :ano AND (a.anoMorte IS NULL OR a.anoMorte > :ano)")
    List<Autor> listarAutoresVivos(Long ano);
}
