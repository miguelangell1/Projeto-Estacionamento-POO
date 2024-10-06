package cesupa.estacionamento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EstacionamentoGUI extends JFrame {
    private List<Veiculo> veiculos = new ArrayList<>(50);
    private JTextField placaTextField, modeloTextField;
    private JTextArea displayArea;
    private static final String FILE_NAME = "veiculos.txt"; 

    public EstacionamentoGUI() {
        setTitle("Estacionamento Argo");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        carregarVeiculosDoArquivo();

        ImageIcon icon = new ImageIcon(getClass().getResource("/cesupa/estacionamento/imagens/estacionamento.png"));
        setIconImage(icon.getImage());

        JLabel placaLabel = new JLabel("Placa:");
        placaTextField = new JTextField(10);
        JLabel modeloLabel = new JLabel("Modelo:");
        modeloTextField = new JTextField(10);

        JButton estacionarButton = new JButton("Estacionar Veículo");
        JButton retirarButton = new JButton("Retirar Veículo");
        JButton listarButton = new JButton("Listar Veículos");

        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);

        add(placaLabel);
        add(placaTextField);
        add(modeloLabel);
        add(modeloTextField);
        add(estacionarButton);
        add(retirarButton);
        add(listarButton);
        add(new JScrollPane(displayArea));

        estacionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String placa = placaTextField.getText();
                String modelo = modeloTextField.getText();
                if (!placa.isEmpty() && !modelo.isEmpty()) {
                    Veiculo novoVeiculo = new Veiculo(placa, modelo, LocalDateTime.now());
                    veiculos.add(novoVeiculo);
                    salvarVeiculosNoArquivo(); 
                    displayArea.setText("Veículo estacionado com sucesso: " + novoVeiculo);
                    limparCampos();
                } else {
                    displayArea.setText("Por favor, insira a placa e o modelo.");
                }
            }
        });

        retirarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String placa = placaTextField.getText();
                Veiculo veiculo = veiculos.stream().filter(v -> v.getPlaca().equals(placa)).findFirst().orElse(null);
                if (veiculo != null) {
                    LocalDateTime horaDeSaida = LocalDateTime.now();
                    double valorCobrado = calcularTarifa(veiculo.getHoraEntrada(), horaDeSaida);
                    veiculos.remove(veiculo);
                    salvarVeiculosNoArquivo();  // Salvar no arquivo sempre que houver alteração
                    displayArea.setText("Veículo removido: " + veiculo + "\nValor cobrado: R$ " + valorCobrado);
                    limparCampos();
                } else {
                    displayArea.setText("Veículo não encontrado.");
                }
            }
        });

        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (veiculos.isEmpty()) {
                    displayArea.setText("Nenhum veículo estacionado.");
                } else {
                    StringBuilder veiculosEstacionados = new StringBuilder();
                    for (Veiculo v : veiculos) {
                        veiculosEstacionados.append(v).append("\n");
                    }
                    displayArea.setText(veiculosEstacionados.toString());
                }
            }
        });
    }

    private void limparCampos() {
        placaTextField.setText("");
        modeloTextField.setText("");
    }

    private double calcularTarifa(LocalDateTime entrada, LocalDateTime saida) {
        long horas = java.time.Duration.between(entrada, saida).toHours();
        return 5.0 * horas;
    }

    private void salvarVeiculosNoArquivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Veiculo veiculo : veiculos) {
                writer.write(veiculo.getPlaca() + "," + veiculo.getModelo() + "," + veiculo.getHoraEntrada().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                writer.newLine();
            }
        } catch (IOException e) {
            displayArea.setText("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    private void carregarVeiculosDoArquivo() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    String[] dados = linha.split(",");
                    String placa = dados[0];
                    String modelo = dados[1];
                    LocalDateTime horaEntrada = LocalDateTime.parse(dados[2], DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    veiculos.add(new Veiculo(placa, modelo, horaEntrada));
                }
            } catch (IOException e) {
                displayArea.setText("Erro ao carregar os dados: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        EstacionamentoGUI frame = new EstacionamentoGUI();
        frame.setVisible(true);
    }
}
