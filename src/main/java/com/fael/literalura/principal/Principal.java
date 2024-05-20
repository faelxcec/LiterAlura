package com.fael.literalura.principal;

import com.fael.literalura.consumo.ConsumoApi;

import java.util.Scanner;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private final String ENDERECO = "http://gutendex.com/books/?search=";
    public void exibeMenu(){
        var opcao = 20;
        while (opcao != 0) {
            var menu = """
                        1 - Buscar Livros
                        0 - Sair
                        """;
            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();
            switch (opcao){
                case 1:
                    buscarLivroWeb();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void buscarLivroWeb() {
        System.out.println("Digite o nome do livro para busca");
        var busca = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + busca.replace(" ","+"));
        System.out.println(json);
    }
}
