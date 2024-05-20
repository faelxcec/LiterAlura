package com.fael.literalura.models;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "autores")
public class Autor {
    public Autor() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String name;
    private Long anoNascimento;
    private Long anoMorte;
    @OneToMany(mappedBy = "autores", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro> livros = new ArrayList<>();


    public Autor(List<DadosAutor> dados) {
        this.name = dados.get(0).name();
        this.anoNascimento = Long.parseLong(dados.get(0).anoNascimento());
        this.anoMorte = Long.parseLong(dados.get(0).anoMorte());
    }

    public void setLivros(List<Livro> livros) {
        livros.forEach(e -> e.setAutores(this));
        this.livros = livros;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Long anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Long getAnoMorte() {
        return anoMorte;
    }

    public void setAnoMorte(Long anoMorte) {
        this.anoMorte = anoMorte;
    }

    @Override
    public String toString() {
        return "Autor {   " +
                "Nome = '" + name + '\'' +
                ", anoNascimento= " + anoNascimento +
                ", anoMorte= " + anoMorte +
                ", livros=" + livros +
                '}';
    }
}
