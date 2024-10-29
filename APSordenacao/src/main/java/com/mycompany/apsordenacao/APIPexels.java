/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apsordenacao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Usuário
 */
public class APIPexels {
     // Chave de acesso à API do Unsplash
    private static final String CHAVE_ACESSO = "1Vk0LZKigFegrnZhbap0Fvax4GxNTltR7RJaIBLX9TJql1nsmhFkvJRq"; 
    
    // URL base da API Unsplash (ajuste para o endpoint correto da API)
    private static final String API_PEXELS = "https://api.pexels.com/v1/search?query=cerrado&per_page=1"; 

    
    // Método para fazer a requisição e obter a URL da imagem
    public String pegarImagens() throws Exception {
        // Cria a URL completa da requisição
        URL url = new URL(API_PEXELS + "&client_id=" + CHAVE_ACESSO);

        // Abre a conexão com a API (objeto HttpURLConnection)
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        
        // Define o método de requisição como GET
        connection.setRequestMethod("GET");
        
        // Obtém o código de resposta do servidor (por exemplo, 200 para OK)
        int codigoResposta = connection.getResponseCode();
        
        // Verifica se a resposta da API foi bem-sucedida (código HTTP 200)
        if (codigoResposta == HttpURLConnection.HTTP_OK) {
            // Lê a resposta da API (fluxo de entrada)
            BufferedReader ler = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            
            // StringBuffer para armazenar a resposta linha por linha
            StringBuffer response = new StringBuffer();
            String inputLine;

            // Lê linha por linha a resposta da API
            while ((inputLine = ler.readLine()) != null) {
                response.append(inputLine);
            }
            
            // Fecha o leitor de buffer após leitura completa
            ler.close();
            
            // Processa o JSON retornado para extrair a URL da imagem
            String urlImagem = processarJSON(response.toString()); // Chama o método para processar o JSON
            return urlImagem; // Retorna a URL da imagem

        } else {
            // Se o código de resposta não for 200, lança uma exceção com a mensagem de erro
            throw new Exception("Erro na requisição da API Unsplash. Código: " + codigoResposta);
        }
    }

    // Método para processar a resposta JSON e obter a URL da imagem
  private String processarJSON(String json) {
    String urlImagem = ""; // Variável para armazenar a URL da imagem

    // Encontra a posição inicial da string "small"
    int index = json.indexOf("\"small\":\"");
    
    // Verifica se a string foi encontrada
    if (index != -1) {
        // Move o índice para o início da URL
        index += 11; // Move o índice após "small":""

        // Encontra a posição final da URL (próximo caractere que não é parte da URL)
        int endIndex = json.indexOf("\"", index);

        // Extrai a URL da imagem
        urlImagem = json.substring(index, endIndex);
    }

    return urlImagem; // Retorna a URL da imagem
}
}
