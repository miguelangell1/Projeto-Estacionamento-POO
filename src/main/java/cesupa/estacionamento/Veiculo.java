package cesupa.estacionamento;

import java.time.LocalDateTime;


public class Veiculo {
	
	private String placa;
	private String modelo;
	private LocalDateTime horaEntrada;
	
	public Veiculo(String placa, String modelo, LocalDateTime hora) {
		this.placa = placa;
		this.modelo = modelo;
		this.horaEntrada = hora;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public LocalDateTime getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(LocalDateTime hora) {
		this.horaEntrada = hora;
	}
	
	
	@Override
	public String toString() {
		return placa + ", " + modelo + ", " + horaEntrada;
	}
	
	
	
	
	
	
}
