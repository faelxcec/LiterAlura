package com.fael.literalura.principal;

import com.fael.literalura.consumo.ConsumoApi;
import com.fael.literalura.models.*;
import com.fael.literalura.service.AutorRepository;
import com.fael.literalura.service.LivroRepository;
import com.fael.literalura.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private final String ENDERECO = "http://gutendex.com/books/?search=";
    private ConverteDados converteDados = new ConverteDados();
    private List<Livro> livros = new ArrayList<>();
    private List<Autor> autores = new ArrayList<>();
    private LivroRepository repositorio;
    private AutorRepository repositorioAutor;

    public Principal(LivroRepository repositorio, AutorRepository repositorioAutor) {
        this.repositorio = repositorio;
        this.repositorioAutor = repositorioAutor;
    }

    public void exibeMenu(){
        var opcao = 20;
        while (opcao != 0) {
            var menu = """
                        1 - Buscar Livros
                        2 - Listar Livros Registrados
                        3 - Listar Autores
                        4 - Listar Autores Vivos Em Um Determinado Ano
                        5 - Listar Livros Por Idioma
                        
                        0 - Sair
                        """;
            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();
            switch (opcao){
                case 1:
                    buscarLivroWeb();
                    break;
                case 2:
                    listarLivros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    listarAutoresVivosEm();
                    break;
                case 5:
                    livrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void livrosPorIdioma() {
        var menu2 = """
                Insira o idioma para busca:
                en- ingles
                pt- portugues
                """;
        System.out.println(menu2);
        var idioma = leitura.nextLine();
        List<Livro> livroList = repositorio.livroPorIdioma(idioma);
        if (livroList.size() > 0) {
            livroList.forEach(System.out::println);
        }
        else{
            System.out.println("Livro não encontrado");
        }
    }

    private void listarAutoresVivosEm() {
        System.out.println("Digite um ano");
        var ano = leitura.nextLong();
        List<Autor> list = repositorioAutor.listarAutoresVivos(ano);
        list.forEach(System.out::println);
    }

    private void listarAutores() {
        List<Autor> autorList = repositorioAutor.findAll();
        autorList.forEach(System.out::println);
    }

    private void listarLivros() {
        List<Livro> livroList = repositorio.findAll();
        livroList.forEach(System.out::println);
    }

    private void buscarLivroWeb() {
        System.out.println("Digite o nome do livro para busca");
        var busca = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + busca.replace(" ","+"));
        DadosBusca dados = converteDados.obterDados(json, DadosBusca.class);
        if (dados.count() > 0) {
            DadosLivro primeiroLivro = dados.livros().get(0);
            String autorNome = primeiroLivro.autores().get(0).name(); // Assumindo que apenas um autor é retornado
            Autor autorExistente = repositorioAutor.findByName(autorNome);
            if (autorExistente == null) {
                autorExistente = new Autor(dados.livros().get(0).autores());
                repositorioAutor.save(autorExistente);
            }
            Livro livro = new Livro(primeiroLivro);
            livro.setAutores(autorExistente);
            repositorio.save(livro);
        }
        else{
            System.out.println("Livro nao encontrado");
        }
    }
}
