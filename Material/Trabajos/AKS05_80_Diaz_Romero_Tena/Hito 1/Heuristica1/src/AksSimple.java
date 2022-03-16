
import algoritmos.AKS;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.opencsv.CSVWriter;

public class AksSimple {

	public static void main(String[] args) {

		// BigInteger n = BigInteger.probablePrime(20, new SecureRandom());
		// BigInteger n = BigInteger.valueOf(97);

		// Definimos el rango para investigar los valores Min y Max

		BigInteger min = BigInteger.valueOf(1000000l);
		BigInteger max = BigInteger.valueOf(1000000000000000000l);

		int finAlgoritmo, inicioAlgoritmo, horaActual;

		// Cuenta atras para finalizacion del algoritmo
		inicioAlgoritmo = (int) Calendar.getInstance().getTimeInMillis();
		finAlgoritmo = inicioAlgoritmo + 7200000;
		System.out.println("Has empezado en " + inicioAlgoritmo + ". Quedan:" + (finAlgoritmo - inicioAlgoritmo) / 60000
				+ " minutos");

		// IMPRESION DATA CSV 
		File file = new File("C:\\users\\marce\\dataP1.csv");

		try {
			

			FileWriter outputfile = new FileWriter(file);
			CSVWriter writer = new CSVWriter(outputfile);

			List<String[]> data = new ArrayList<String[]>();

			String header[] = { "Tiempo1", "Tiempo2", "Tiempo3", "Numero", "isPrime" };

			data.add(header);
			BigInteger uno = BigInteger.ONE;

			short inicio = 20;

			// Bucle for para comprobar primalidad de los numeros del rango
			for (BigInteger i = min; i.compareTo(max) == -1; i = i.add(uno)) {

				// Creating an instance of the AKS algorithm // COMIENZO BUCLE PARA GENERAR
				AKS aks = new AKS(i);

				String dataa[] = new String[5];
				dataa = aks.isPrime();

				data.add(dataa);

				System.out.println("Numero: "+dataa[0]+"; Tiempo H1:  "+dataa[1]+"; Tiempo R: "+dataa[2]+"; Tiempo MCD: "+dataa[3]+"; Primo:"+dataa[4]+" ");

				horaActual = (int) Calendar.getInstance().getTimeInMillis();
				if (horaActual > inicioAlgoritmo + 900000) {
					System.out.println("Han pasado" + 15 + " minutos. Quedan: " + (finAlgoritmo - horaActual) / 60000
							+ " Minutos");
					inicioAlgoritmo += 900000;
				}
				if (horaActual > finAlgoritmo)
					break;
				inicio--;
				if (inicio <= 0) {
					i = i.add(i);
					inicio = 20;
				}
			}
			writer.writeAll(data);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
