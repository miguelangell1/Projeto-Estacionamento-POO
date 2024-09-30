package cesupa.estacionamento;

import java.time.LocalTime;

public class Veiculo {
	
	private String placa;
	private String modelo;
	private LocalTime hora;
	
	public Veiculo(String placa, String modelo, LocalTime hora) {
		this.placa = placa;
		this.modelo = modelo;
		this.hora = hora;
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

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}
	
	@Override
	public String toString() {
		return placa + ", " + modelo + ", " + hora;
	}
	
	
	
	
	
	
}
