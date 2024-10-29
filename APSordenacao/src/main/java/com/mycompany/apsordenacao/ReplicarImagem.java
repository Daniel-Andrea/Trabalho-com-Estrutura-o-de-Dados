/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apsordenacao;

/**
 *
 * @author Usuário
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReplicarImagem {
    private final Connection connection; // Conexão com o banco de dados

    // Construtor que recebe a conexão com o banco de dados
    public ReplicarImagem(Connection connection) {
        this.connection = connection;
    }

    // Método para replicar a imagem existente no banco de dados com base no ID
    public void replicarImagemPorID(int idImagem, int quantidadeReplicas) {
        try {
            // Passo 1: Recupera a imagem existente do banco de dados com base no ID fornecido
            String sqlRecuperarImagem = "SELECT imagem FROM imagens WHERE id = ?";
            PreparedStatement stmtRecuperar = connection.prepareStatement(sqlRecuperarImagem);
            stmtRecuperar.setInt(1, idImagem); // Define o ID da imagem a ser recuperada
            ResultSet rs = stmtRecuperar.executeQuery();

            if (rs.next()) {
                byte[] imagem = rs.getBytes("imagem"); // Obtém os bytes da imagem

                // Passo 2: Insere a imagem replicada no banco de dados com valores aleatórios
                for (int i = 0; i < quantidadeReplicas; i++) {
                    inserirImagemComValorAleatorio(imagem);
                }

                System.out.println(quantidadeReplicas + " réplicas da imagem com ID " + idImagem + " foram inseridas com sucesso.");
            } else {
                System.err.println("Nenhuma imagem encontrada com o ID fornecido: " + idImagem);
            }

            rs.close();
            stmtRecuperar.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método que insere a imagem com um valor aleatório no banco de dados
    private void inserirImagemComValorAleatorio(byte[] imagem) {
        String sqlInserir = "INSERT INTO imagens (valor_randomico, imagem) VALUES (?, ?)";

        try (PreparedStatement stmtInserir = connection.prepareStatement(sqlInserir)) {
            int valorRandomico = gerarIDAleatorio(); // Gera um valor aleatório

            // Define os parâmetros da inserção
            stmtInserir.setInt(1, valorRandomico);
            stmtInserir.setBytes(2, imagem);

            // Executa a inserção
            stmtInserir.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para gerar um número aleatório com até 5 dígitos
    private int gerarIDAleatorio() {
        return (int) (Math.random() * 100000); // Gera um número aleatório de 0 a 99999
    }
}

