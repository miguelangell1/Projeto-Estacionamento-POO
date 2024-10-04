package cesupa.estacionamento;

import java.text.Format;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
	public static void main(String[] args) {
		List<Veiculo> veiculos = new ArrayList<>(50);
		Scanner sc = new Scanner(System.in);
		veiculos.add(new Veiculo("QEY8739", "Volkswagen Gol", LocalDateTime.parse("2024-10-01T08:30:15")));
		veiculos.add(new Veiculo("QEJ1312", "HB20", LocalDateTime.parse("2024-10-01T08:30:15")));

		// Veiculo carro = veiculos.stream().filter(x ->
		// x.getModelo().equals("HB20")).findFirst().orElse(null);

		System.out.println("Seja bem vindo ao Estacionamento Argo");
		
		boolean condicao = true;
		
		do {
			
			System.out.println("Nossas opções de serviço são:");
			System.out.println(
					"1- Estacionar o meu veículo\n2- Retirar o meu veículo\n3- Lista de veículos estacionados\n4- Encerrar programa");
			System.out.print("O que você deseja fazer? ");
			char resposta = sc.next().charAt(0);
			switch (resposta) {
			case '1':
				System.out.println("Qual a placa do veículo?");
				String placa = sc.next();
				System.out.println("Qual o modelo do veículo?");
				String modelo = sc.next();
				Veiculo novoCarro =	entrarCarro(veiculos, placa, modelo, LocalDateTime.now());
					System.out.println("Carro registrado com sucesso!\nInformações do carro registrado: " + novoCarro );
					System.out.println("Veículos estacionados: ");
				break;
			case '2':
				boolean verificarSaida;
				do {
					System.out.print("Digite a placa: ");
					String placaSaida = sc.next();
					verificarSaida = saidaDeCarro(veiculos, placaSaida);
				} while (!verificarSaida);
				
				break;
			case '3':
				for (Veiculo carro : veiculos) {
					System.out.println(carro);
				}

				break;
			case '4':
				condicao = false;
				System.out.println("Obrigado pela preferência! Encerrando programa...");
				break;
			default:
				System.out.println("Não há essa opção");
				break;
			}

		} while (condicao);
		
		
		

		sc.close();
	}

	public static boolean saidaDeCarro(List<Veiculo> veiculos, String placaSaida) {
		Veiculo carro = veiculos.stream().filter(x -> x.getPlaca().equals(placaSaida)).findFirst().orElse(null);
		if (carro == null) {
			System.out.println("Carro não existe!");
			return false;
		} else {
			LocalDateTime horaDeEntrada = LocalDateTime.now();
			LocalDateTime horaDeSaida = LocalDateTime.now();
			Duration horaFinal = Duration.between(carro.getHoraEntrada(), horaDeSaida);
			double valorCobrado = 5.0 * horaFinal.toHours();

			System.out.println("Este carro passou " + horaFinal.toHours() + " horas");
			System.out.println("Valor com tarifa: R$" + valorCobrado);
			veiculos.remove(carro);
			return true;
		}
		
		

	}
	
	public static Veiculo entrarCarro(List<Veiculo> veiculos, String placa, String modelo, LocalDateTime entrada) {
		Veiculo novoVeiculo = new Veiculo(placa, modelo, entrada);	
		veiculos.add(novoVeiculo);
		return novoVeiculo;
		
			
	}
	
}
