/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.apsordenacao;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Usuário
 */
public class tela2 extends javax.swing.JFrame {

    /**
     * Creates new form tela2
     */
    public tela2() {
        initComponents();
        atualizarTabela();
    }

    private void atualizarTabela() {
        Connection connection = null;
        String sql = "SELECT id, imagem, valor_randomico FROM imagens"; // Seleciona todos os três campos

        DefaultTableModel model = (DefaultTableModel) tableImagens.getModel();
        model.setRowCount(0); // Limpa as linhas existentes

        try {
            connection = ConeçãoBanco.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                byte[] imagemBytes = resultSet.getBytes("imagem"); // Obtém a imagem como bytes
                double valorRandomico = resultSet.getDouble("valor_randomico"); // Obtém o valor aleatório

                // Converte os bytes da imagem em ImageIcon, se necessário
                ImageIcon imagemIcon = null;
                if (imagemBytes != null) {
                    Image image = Toolkit.getDefaultToolkit().createImage(imagemBytes);
                    imagemIcon = new ImageIcon(image);
                }

                // Adiciona a linha com os dados
                model.addRow(new Object[]{id, imagemIcon, valorRandomico});
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Bimagem = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableImagens = new javax.swing.JTable();
        BenviarImagem = new javax.swing.JButton();
        ordenacaoSort = new javax.swing.JButton();
        buscaLinear = new javax.swing.JButton();
        buscaBinaria = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("ENVIAR IMAGEM");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("SELECIONE A IMAGEM:");

        Bimagem.setText("IMAGEM");
        Bimagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BimagemActionPerformed(evt);
            }
        });

        tableImagens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Imagem", "N/IMAGEM"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableImagens);

        BenviarImagem.setText("ENVIAR");

        ordenacaoSort.setText("bubble sort");
        ordenacaoSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ordenacaoSortActionPerformed(evt);
            }
        });

        buscaLinear.setText("busca linear");
        buscaLinear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscaLinearActionPerformed(evt);
            }
        });

        buscaBinaria.setText("busca binaria");
        buscaBinaria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscaBinariaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 160, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(141, 141, 141))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(97, 97, 97)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(ordenacaoSort, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(buscaLinear, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(82, 82, 82)
                                .addComponent(buscaBinaria, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Bimagem, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(76, 76, 76)
                                .addComponent(BenviarImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(Bimagem, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BenviarImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 212, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(210, 210, 210)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buscaBinaria, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buscaLinear, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ordenacaoSort, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BimagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BimagemActionPerformed
        // TODO add your handling code here:
        Connection connection = ConeçãoBanco.getConnection();

        // Cria um novo JFileChooser para permitir que o usuário selecione um arquivo de imagem
        JFileChooser fileChooser = new JFileChooser();

        // Define o título da janela do JFileChooser
        fileChooser.setDialogTitle("Selecionar Imagem");

        // Define um filtro para o tipo de arquivo, permitindo apenas imagens com as extensões especificadas
        fileChooser.setFileFilter(new FileNameExtensionFilter("Imagens", "jpg", "jpeg", "png", "gif"));

        // Exibe a caixa de diálogo e aguarda a escolha do usuário
        int result = fileChooser.showOpenDialog(this);

        // Verifica se o usuário selecionou um arquivo
        if (result == JFileChooser.APPROVE_OPTION) {
            // Obtém o arquivo selecionado
            File selectedFile = fileChooser.getSelectedFile();

            try {
                // Lê todos os bytes do arquivo selecionado e os armazena em um array de bytes
                byte[] imagemBytes = Files.readAllBytes(selectedFile.toPath());

                // Comando SQL para inserir a imagem no banco de dados
                String sql = "INSERT INTO imagens (valor_randomico, imagem) VALUES (?, ?)";

                // Prepara a instrução SQL com parâmetros
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    // Gera um valor aleatório que será armazenado no banco de dados
                    int valorRandomico = gerarValorRandomico();

                    // Define o primeiro parâmetro da instrução SQL (valor aleatório)
                    preparedStatement.setInt(1, valorRandomico);

                    // Define o segundo parâmetro da instrução SQL (bytes da imagem)
                    preparedStatement.setBytes(2, imagemBytes);

                    // Executa a inserção no banco de dados
                    preparedStatement.executeUpdate();

                    // Exibe uma mensagem de sucesso no console
                    System.out.println("Imagem enviada para o banco de dados com sucesso.");

                } catch (SQLException e) {
                    // Trata possíveis exceções que podem ocorrer durante a execução da instrução SQL
                    e.printStackTrace();
                }
            } catch (IOException e) {
                // Trata possíveis exceções que podem ocorrer ao ler o arquivo
                e.printStackTrace();
            }
        } else {
            // Caso o usuário não selecione nenhum arquivo, exibe uma mensagem no console
            System.out.println("Nenhum arquivo selecionado.");
        }

        // Fecha a conexão com o banco de dados
        ConeçãoBanco.closeConnection(connection); // Assegura que a conexão seja fechada
    }

// Método para gerar um valor aleatório de 0 a 99999
    private int gerarValorRandomico() {
        return (int) (Math.random() * 100000); // Gera um número aleatório de 0 a 99999


    }//GEN-LAST:event_BimagemActionPerformed

    private void ordenacaoSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ordenacaoSortActionPerformed
        // TODO add your handling code here:
        // Variáveis para a conexão com o banco de dados
        Connection conexao = null;  // Usada para armazenar a conexão com o banco de dados
        Statement comando = null;   // Usada para criar e executar as consultas SQL
        ResultSet resultado = null; // Armazena os resultados das consultas SQL
        int[] valores = null;       // Array para armazenar os valores recuperados do banco de dados

        try {
            // Estabelecer a conexão com o banco de dados usando a classe ConeçãoBanco
            conexao = ConeçãoBanco.getConnection();  // Abre a conexão com o banco
            comando = conexao.createStatement();     // Cria um objeto Statement para executar as consultas

            // Primeira consulta: contar quantos registros existem na tabela 'imagens'
            resultado = comando.executeQuery("SELECT COUNT(*) AS total FROM imagens");
            int tamanhoArray = 0;  // Variável para armazenar o número total de registros
            if (resultado.next()) {  // Se houver resultados da consulta
                tamanhoArray = resultado.getInt("total");  // Obtém o valor da contagem
            }
            resultado.close(); // Fecha o ResultSet da primeira consulta, pois não será mais usado

            // Inicializar o array de inteiros com o tamanho baseado na contagem de registros
            valores = new int[tamanhoArray];

            // Segunda consulta: buscar os valores da coluna 'valor_randomico' na tabela 'imagens'
            resultado = comando.executeQuery("SELECT valor_randomico FROM imagens");
            int indice = 0;  // Variável para controlar o índice do array

            // Iterar pelos resultados da consulta e preencher o array com os valores do banco
            while (resultado.next()) {
                valores[indice] = resultado.getInt("valor_randomico");  // Atribui o valor ao array
                indice++;  // Incrementa o índice do array
            }

            // Capturar o tempo inicial antes de começar a ordenação (em nanossegundos)
            long tempoInicio = System.nanoTime();

            // Chamar o método de ordenação Bubble Sort, passando o array de valores
            bubbleSort(valores);

            // Capturar o tempo final após a ordenação
            long tempoFim = System.nanoTime();

            // Calcular a duração da ordenação em milissegundos (convertendo de nanossegundos)
            long tempoDuracao = (tempoFim - tempoInicio) / 1_000_000;

            // Criar uma JTextArea para exibir os valores ordenados
            JTextArea textArea = new JTextArea(10, 40);  // Definir um tamanho inicial de 10 linhas e 40 colunas
            textArea.setText("Valores ordenados:\n" + arrayParaString(valores));  // Preencher o JTextArea com os valores ordenados
            textArea.setEditable(false);  // Desabilitar a edição para que o usuário apenas visualize os dados

            // Adicionar o JTextArea a um JScrollPane para permitir o scroll
            JScrollPane scrollPane = new JScrollPane(textArea);  // Adiciona a área de texto ao JScrollPane

            // Exibir uma janela de diálogo com o JScrollPane e o tempo gasto na ordenação
            JOptionPane.showMessageDialog(null, scrollPane,
                    "Tempo de ordenação: " + tempoDuracao + " ms", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            // Exibir uma mensagem de erro se houver falha ao buscar os valores do banco
            JOptionPane.showMessageDialog(null, "Erro ao buscar os valores: " + e.getMessage());
        } finally {
            // Bloco 'finally' para garantir que os recursos do banco de dados sejam fechados corretamente
            try {
                if (resultado != null) {
                    resultado.close();  // Fecha o ResultSet se ele não for nulo
                }
                if (comando != null) {
                    comando.close();      // Fecha o Statement se ele não for nulo
                }
                ConeçãoBanco.closeConnection(conexao);     // Fecha a conexão com o banco de dados
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());  // Log de erro ao fechar recursos
            }
        }
    }

// Método que implementa o algoritmo de ordenação Bubble Sort
    private void bubbleSort(int[] arr) {
        int n = arr.length;  // Obtém o tamanho do array
        boolean trocado;     // Variável para verificar se houve troca durante a iteração

        // Laço externo para iterar sobre o array várias vezes
        for (int i = 0; i < n - 1; i++) {
            trocado = false;  // Inicializa como falso a cada iteração do laço externo

            // Laço interno para comparar elementos adjacentes e trocar se necessário
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Troca os elementos se o valor da posição atual for maior que o próximo
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    trocado = true;  // Sinaliza que houve uma troca
                }
            }

            // Se não houve troca, o array já está ordenado e o laço externo pode ser interrompido
            if (!trocado) {
                break;
            }
        }
    }

// Método auxiliar para converter o array de inteiros em uma String para exibição
    private String arrayParaString(int[] arr) {
        StringBuilder sb = new StringBuilder();  // Usado para construir a string final
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);  // Adiciona o valor atual ao StringBuilder
            if (i < arr.length - 1) {  // Adiciona uma vírgula e espaço entre os elementos
                sb.append(", ");
            }
        }
        return sb.toString();  // Retorna a string com os valores
    }//GEN-LAST:event_ordenacaoSortActionPerformed

    private void buscaLinearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscaLinearActionPerformed
        // TODO add your handling code here:
        Connection conexao = null;
        Statement comando = null;
        ResultSet resultado = null;
        int[] ids = null;
        byte[][] imagensBlob = null;  // Array para armazenar os blobs das imagens
        int idBuscado = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID que deseja buscar:"));  // Solicita o ID ao usuário

        try {
            // Estabelecer conexão com o banco de dados
            conexao = ConeçãoBanco.getConnection();
            comando = conexao.createStatement();

            // Primeira consulta: contar quantos registros existem na tabela 'imagens'
            resultado = comando.executeQuery("SELECT COUNT(*) AS total FROM imagens");
            int totalRegistros = 0;
            if (resultado.next()) {
                totalRegistros = resultado.getInt("total");
            }
            resultado.close();  // Fecha o ResultSet

            // Inicializar os arrays com base no número de registros
            ids = new int[totalRegistros];
            imagensBlob = new byte[totalRegistros][];  // Armazena cada imagem em formato de bytes

            // Segunda consulta: buscar os IDs e os blobs das imagens da tabela 'imagens'
            resultado = comando.executeQuery("SELECT id, imagem FROM imagens");
            int indice = 0;  // Índice para os arrays

            // Preencher os arrays com os IDs e os blobs das imagens
            while (resultado.next()) {
                ids[indice] = resultado.getInt("id");
                imagensBlob[indice] = resultado.getBytes("imagem");  // Armazena o BLOB como array de bytes
                indice++;
            }

            // Captura o tempo inicial antes de iniciar a busca linear
            long tempoInicio = System.nanoTime();

            // Busca linear pelo ID
            byte[] imagemEncontrada = null;
            for (int i = 0; i < ids.length; i++) {
                if (ids[i] == idBuscado) {
                    imagemEncontrada = imagensBlob[i];  // Pega os bytes da imagem correspondente
                    break;
                }
            }

            // Captura o tempo final após a busca linear
            long tempoFim = System.nanoTime();
            long tempoDuracao = (tempoFim - tempoInicio) / 1_000_000;  // Calcular o tempo em milissegundos

            // Verifica se o ID foi encontrado e exibe a imagem
            if (imagemEncontrada != null) {
                // Converter o array de bytes em uma imagem
                InputStream is = new ByteArrayInputStream(imagemEncontrada);  // Converte os bytes em InputStream
                BufferedImage imagem = ImageIO.read(is);  // Lê a imagem do InputStreamF

                // Criar um ImageIcon a partir da imagem BufferedImage
                ImageIcon imageIcon = new ImageIcon(imagem);
                JLabel labelImagem = new JLabel(imageIcon);  // Cria um JLabel para exibir a imagem
                JScrollPane scrollPane = new JScrollPane(labelImagem);  // Adiciona um scroll caso a imagem seja grande
                scrollPane.setPreferredSize(new Dimension(600, 600));  // Define o tamanho da janela de exibição da imagem

                // Criar um painel customizado para incluir a imagem e o tempo
                JPanel panel = new JPanel(new BorderLayout());
                panel.add(scrollPane, BorderLayout.CENTER);  // Adiciona a imagem com scroll no centro do painel
                JLabel tempoLabel = new JLabel("Tempo de busca linear: " + tempoDuracao + " ms");
                panel.add(tempoLabel, BorderLayout.SOUTH);  // Adiciona o tempo na parte inferior do painel

                // Exibe a imagem em um diálogo com o tempo gasto na busca
                JOptionPane.showMessageDialog(null, panel,
                        "ID " + idBuscado + " encontrado!", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                        "ID " + idBuscado + " não encontrado! Tempo de busca linear: " + tempoDuracao + " ms");
            }

        } catch (SQLException | IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar os valores ou ao carregar a imagem: " + e.getMessage());
        } finally {
            try {
                if (resultado != null) {
                    resultado.close();
                }
                if (comando != null) {
                    comando.close();
                }
                ConeçãoBanco.closeConnection(conexao);
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_buscaLinearActionPerformed

    private void buscaBinariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscaBinariaActionPerformed
        // TODO add your handling code here
        Connection conexao = null;
        Statement comando = null;
        ResultSet resultado = null;
        int[] ids = null;
        byte[][] imagensBlob = null;  // Array para armazenar os blobs das imagens
        int idBuscado = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID que deseja buscar:"));  // Solicita o ID ao usuário

        try {
            // Estabelecer conexão com o banco de dados
            conexao = ConeçãoBanco.getConnection();
            comando = conexao.createStatement();

            // Primeira consulta: contar quantos registros existem na tabela 'imagens'
            resultado = comando.executeQuery("SELECT COUNT(*) AS total FROM imagens");
            int totalRegistros = 0;
            if (resultado.next()) {
                totalRegistros = resultado.getInt("total");
            }
            resultado.close();  // Fecha o ResultSet

            // Inicializar os arrays com base no número de registros
            ids = new int[totalRegistros];
            imagensBlob = new byte[totalRegistros][];  // Armazena cada imagem em formato de bytes

            // Segunda consulta: buscar os IDs e os blobs das imagens da tabela 'imagens'
            resultado = comando.executeQuery("SELECT id, imagem FROM imagens ORDER BY id");
            int indice = 0;  // Índice para os arrays

            // Preencher os arrays com os IDs e os blobs das imagens
            while (resultado.next()) {
                ids[indice] = resultado.getInt("id");
                imagensBlob[indice] = resultado.getBytes("imagem");  // Armazena o BLOB como array de bytes
                indice++;
            }

            // Captura o tempo inicial antes de iniciar a busca binária
            long tempoInicio = System.nanoTime();

            // Busca binária pelo ID
            byte[] imagemEncontrada = buscaBinaria(ids, imagensBlob, idBuscado);

            // Captura o tempo final após a busca binária
            long tempoFim = System.nanoTime();
            long tempoDuracao = (tempoFim - tempoInicio) / 1_000_000;  // Calcular o tempo em milissegundos

            // Verifica se o ID foi encontrado e exibe a imagem
            if (imagemEncontrada != null) {
                // Converter o array de bytes em uma imagem
                InputStream is = new ByteArrayInputStream(imagemEncontrada);  // Converte os bytes em InputStream
                BufferedImage imagem = ImageIO.read(is);  // Lê a imagem do InputStream

                // Criar um ImageIcon a partir da imagem BufferedImage
                ImageIcon imageIcon = new ImageIcon(imagem);
                JLabel labelImagem = new JLabel(imageIcon);  // Cria um JLabel para exibir a imagem
                JScrollPane scrollPane = new JScrollPane(labelImagem);  // Adiciona um scroll caso a imagem seja grande
                scrollPane.setPreferredSize(new Dimension(600, 600));  // Define o tamanho da janela de exibição da imagem

                // Criar um painel customizado para incluir a imagem e o tempo
                JPanel panel = new JPanel(new BorderLayout());
                panel.add(scrollPane, BorderLayout.CENTER);  // Adiciona a imagem com scroll no centro do painel
                JLabel tempoLabel = new JLabel("Tempo de busca binária: " + tempoDuracao + " ms");
                panel.add(tempoLabel, BorderLayout.SOUTH);  // Adiciona o tempo na parte inferior do painel

                // Exibe a imagem em um diálogo com o tempo gasto na busca
                JOptionPane.showMessageDialog(null, panel,
                        "ID " + idBuscado + " encontrado!", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                        "ID " + idBuscado + " não encontrado! Tempo de busca binária: " + tempoDuracao + " ms");
            }

        } catch (SQLException | IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar os valores ou ao carregar a imagem: " + e.getMessage());
        } finally {
            try {
                if (resultado != null) {
                    resultado.close();
                }
                if (comando != null) {
                    comando.close();
                }
                ConeçãoBanco.closeConnection(conexao);
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }

// Método de busca binária
    private byte[] buscaBinaria(int[] ids, byte[][] imagensBlob, int idBuscado) {
        int inicio = 0;
        int fim = ids.length - 1;

        while (inicio <= fim) {
            int meio = inicio + (fim - inicio) / 2;

            if (ids[meio] == idBuscado) {
                return imagensBlob[meio];  // Retorna o BLOB da imagem correspondente
            }
            if (ids[meio] < idBuscado) {
                inicio = meio + 1;  // Ignora a metade esquerda
            } else {
                fim = meio - 1;  // Ignora a metade direita
            }
        }

        return null;  // Retorna null se o ID não foi encontrado


    }//GEN-LAST:event_buscaBinariaActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BenviarImagem;
    private javax.swing.JButton Bimagem;
    private javax.swing.JButton buscaBinaria;
    private javax.swing.JButton buscaLinear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton ordenacaoSort;
    private javax.swing.JTable tableImagens;
    // End of variables declaration//GEN-END:variables

}
