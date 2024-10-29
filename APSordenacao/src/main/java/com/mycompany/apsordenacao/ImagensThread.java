/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apsordenacao;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.io.BufferedInputStream;

// Classe que representa uma thread para baixar e armazenar imagens
public class ImagensThread extends Thread {
    private final APIUnsplash apiUnsplash; // Instância da classe para acessar a API do Unsplash
    private final APIPexels apiPexels; // Instância da classe para acessar a API do Pexels
    private final ArrayList<String> imagens; // Lista para armazenar URLs das imagens
    private final Connection connection; // Conexão com o banco de dados

    // Construtor da classe
    public ImagensThread(Connection connection) {
        this.apiUnsplash = new APIUnsplash(); // Inicializa a API do Unsplash
        this.apiPexels = new APIPexels(); // Inicializa a API do Pexels
        this.imagens = new ArrayList<>(); // Inicializa a lista de imagens
        this.connection = connection; // Armazena a conexão com o banco de dados
    }

    // Método que é executado quando a thread é iniciada
    @Override
    public void run() {
        try {
            // Faz 50 requisições ao Unsplash
            for (int i = 0; i < 50; i++) {
                String imagemUnsplash = apiUnsplash.pegarImagens(); // Requisição ao Unsplash
                if (imagemUnsplash != null) { // Verifica se a imagem foi obtida com sucesso
                    imagens.add(imagemUnsplash); // Adiciona a URL ao ArrayList
                    enviarImagemParaBanco(ConeçãoBanco.getConnection(), imagemUnsplash); // Envia a imagem para o banco
                }
                Thread.sleep(1000); // Pausa por 1 segundo entre as requisições
            }

            // Espera 1 hora (3600000 milissegundos) após as requisições do Unsplash
            System.out.println("Pausando por 1 hora após as requisições do Unsplash...");
            Thread.sleep(3600000); // Pausa a thread por 1 hora

            // Faz 50 requisições ao Pexels
            for (int i = 0; i < 50; i++) {
                String imagemPexels = apiPexels.pegarImagens(); // Requisição ao Pexels
                if (imagemPexels != null) { // Verifica se a imagem foi obtida com sucesso
                    imagens.add(imagemPexels); // Adiciona a URL ao ArrayList
                    enviarImagemParaBanco(ConeçãoBanco.getConnection(), imagemPexels); // Envia a imagem para o banco
                }
                Thread.sleep(1000); // Pausa por 1 segundo entre as requisições
            }
        } catch (Exception e) {
            e.printStackTrace(); // Imprime o erro caso ocorra
        }
    }

    // Método para retornar a lista de imagens armazenadas
    public ArrayList<String> getImagens() {
        return imagens; // Retorna as imagens armazenadas
    }

    // Método para enviar a imagem para o banco de dados
   private void enviarImagemParaBanco(Connection connection, String urlImagem) throws SQLException {
    // Verifica se a URL da imagem não é nula
    if (urlImagem == null) {
        System.err.println("A URL da imagem é nula, não será enviada para o banco de dados.");
        return; // Retorna sem fazer nada se a URL for nula
    }

    // Tenta baixar a imagem
    byte[] imagemBytes = baixarImagem(urlImagem);
    
    // Verifica se os bytes da imagem não são nulos
    if (imagemBytes == null) {
        System.err.println("Os bytes da imagem são nulos, não será enviada para o banco de dados.");
        return; // Retorna sem fazer nada se os bytes forem nulos
    }

    // Redimensionar a imagem antes de salvar no banco
    imagemBytes = redimensionarImagem(imagemBytes, 100, 100);

    String sql = "INSERT INTO imagens (valor_randomico, imagem) VALUES (?, ?)"; // Comando SQL para inserção
    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        int valorRandomico = gerarIDAleatorio(); // Gera o valor aleatório
        preparedStatement.setInt(1, valorRandomico); // Armazena o valor aleatório
        preparedStatement.setBytes(2, imagemBytes); // Armazena a imagem como BLOB
        preparedStatement.executeUpdate(); // Executa a inserção
        System.out.println("Imagem enviada para o banco de dados com sucesso."); // Log de sucesso
    } catch (SQLException e) {
        e.printStackTrace(); // Lida com exceções de SQL
    }
}

private byte[] baixarImagem(String urlImagem) {
    byte[] imagemBytes = null;

    // Verifica se a URL é válida
    if (urlImagem == null || urlImagem.isEmpty()) {
        System.err.println("URL da imagem está vazia ou nula.");
        return null;
    }

    // Substitui 'tps://' por 'https://' para corrigir a URL
    if (urlImagem.startsWith("tps://")) {
        urlImagem = "https://" + urlImagem.substring(5);
    }

    // Corrige a URL caso ela tenha três barras (ex.: "https:///")
    if (urlImagem.startsWith("https:///")) {
        urlImagem = urlImagem.replaceFirst("https:///", "https://");
    }

    // Certifique-se de que a URL comece com "http://" ou "https://"
    if (!urlImagem.startsWith("http://") && !urlImagem.startsWith("https://")) {
        System.err.println("URL inválida: " + urlImagem);
        return null; // Retorna nulo se a URL for inválida
    }

    try {
        System.out.println("Tentando baixar imagem da URL: " + urlImagem); // Log da URL
        URL url = new URL(urlImagem); // Cria um objeto URL com a URL da imagem
        HttpURLConnection connection = (HttpURLConnection) url.openConnection(); // Abre a conexão HTTP

        connection.setRequestMethod("GET"); // Define o método de requisição como GET
        connection.connect(); // Conecta à URL

        // Verifica o código de resposta da conexão
        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            // Lê os bytes da imagem da conexão
            try (InputStream inputStream = new BufferedInputStream(connection.getInputStream())) {
                imagemBytes = inputStream.readAllBytes(); // Lê os bytes da imagem
                System.out.println("Imagem baixada com sucesso, tamanho: " + imagemBytes.length + " bytes."); // Log de sucesso
            }
        } else {
            System.err.println("Erro ao conectar à URL: " + urlImagem + ", Código de resposta: " + connection.getResponseCode());
        }

        connection.disconnect(); // Desconecta a conexão após o download
    } catch (IOException e) {
        System.err.println("Erro ao baixar a imagem: " + e.getMessage());
        e.printStackTrace(); // Lida com exceções
    }

    return imagemBytes; // Retorna os bytes da imagem
}

  // Método para redimensionar a imagem
    private byte[] redimensionarImagem(byte[] imagemBytes, int largura, int altura) {
        try {
            // Converte os bytes da imagem em um BufferedImage
            ByteArrayInputStream bais = new ByteArrayInputStream(imagemBytes);
            BufferedImage imagemOriginal = ImageIO.read(bais); // Lê a imagem original

            // Cria uma nova imagem redimensionada
            BufferedImage imagemRedimensionada = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = imagemRedimensionada.createGraphics(); // Cria o contexto gráfico para redimensionar
            g2d.drawImage(imagemOriginal, 0, 0, largura, altura, null); // Desenha a imagem redimensionada
            g2d.dispose(); // Libera os recursos gráficos

            // Converte a imagem redimensionada de volta para bytes
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(imagemRedimensionada, "jpg", baos); // Escreve a imagem no formato JPG
            return baos.toByteArray(); // Retorna os bytes da imagem redimensionada
        } catch (IOException e) {
            e.printStackTrace(); // Imprime o erro caso ocorra
            return imagemBytes; // Retorna a imagem original se houver falha no redimensionamento
        }
    }

    // Método para gerar um número aleatório com até 5 dígitos
    private int gerarIDAleatorio() {
        return (int) (Math.random() * 100000); // Gera um número aleatório até 5 dígitos (0 a 99999)
    }
}


