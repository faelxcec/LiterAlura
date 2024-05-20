package com.fael.literalura.models;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "Livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titulo;
    @ManyToOne(cascade =  CascadeType.MERGE)
    private Autor autores ;
    private String idioma;
    private Long downloads;

    public Livro() {
    }

    public Livro(DadosLivro dados){
        this.titulo = dados.titulo();
        this.idioma = dados.idioma().get(0);
        this.downloads = dados.downloads();
    }
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutores() {
        return autores;
    }

    public void setAutores(Autor autores) {

        this.autores = autores;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Long getDownloads() {
        return downloads;
    }

    public void setDownloads(Long downloads) {
        this.downloads = downloads;
    }

    @Override
    public String toString() {
        return "  Livro  " +
                " titulo: " + titulo +
                " autores: " + autores.getName()+
                " idioma: " + idioma +
                " downloads: " + downloads;
    }
}
