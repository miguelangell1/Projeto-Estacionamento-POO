package cesupa.estacionamento;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class App 
{
    public static void main( String[] args )
    {
        List<Veiculo> veiculos = new ArrayList<>(50);
        veiculos.add(new Veiculo("QEY8739", "Volkswagen Gol", LocalTime.now()));
        veiculos.add(new Veiculo("QEJ1312", "HB20", LocalTime.now()));
        for (Veiculo carro : veiculos) {
        	System.out.println(carro);
        }
        
    }
}
