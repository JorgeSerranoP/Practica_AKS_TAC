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
		// Definimos el rango para investigar los valores Min y Max

		BigInteger min = BigInteger.valueOf(10l);
		BigInteger max = BigInteger.valueOf(1000000000l);

		int finAlgoritmo, inicioAlgoritmo, horaActual;
		inicioAlgoritmo = (int) Calendar.getInstance().getTimeInMillis();
		finAlgoritmo = inicioAlgoritmo + 1800000;
		System.out.println("Has empezado en " + inicioAlgoritmo + ". Quedan:" + (finAlgoritmo - inicioAlgoritmo) / 60000
				+ " minutos");

		// IMPRESION DATA CSV
		File file = new File("C:\\users\\marce\\dataP1.csv");

		try {

			FileWriter outputfile = new FileWriter(file);
			CSVWriter writer = new CSVWriter(outputfile);

			List<String[]> data = new ArrayList<String[]>();

			String header[] = { "Numero", "R", "Tiempo", "isPrime" };

			data.add(header);
			BigInteger uno = BigInteger.ONE;
			//int inicio = 0;
			// Bucle for para comprobar primalidad de los numeros del rango
			for (BigInteger i = min; i.compareTo(max) == -1; i = i.add(uno)) {

			
				if (!i.isProbablePrime(100))
					continue;
				
				AKS aks = new AKS(i);

				String dataa[] = aks.hito3Prime();

				System.out.println("Numero: " +dataa[0]+ ". R: "+dataa[1] + ". Tiempo: "+dataa[2]+ ". Es primo: " +dataa[3] );
				data.add(dataa);

				horaActual = (int) Calendar.getInstance().getTimeInMillis();

				if (horaActual > inicioAlgoritmo + 900000) {
					System.out.println("Han pasado" + 15 + " minutos. Quedan: " + (finAlgoritmo - horaActual) / 60000
							+ " Minutos");
					inicioAlgoritmo += 900000;
				}
				if (horaActual > finAlgoritmo)
					break;
				//i = i.add(BigInteger.valueOf(5000l));
			}

			writer.writeAll(data);

			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
