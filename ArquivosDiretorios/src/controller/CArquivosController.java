package controller;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CArquivosController implements IArquivosController {
	public CArquivosController() {
		
	}

	public void readFile(String path, String nome) throws IOException {
	File dir = new File(path);
	File arq = new File(path, nome);
	if (dir.exists() && dir.isDirectory()) {
	if (arq.exists() &&  arq.isFile()) {
		FileInputStream fluxo = new FileInputStream(arq);
		InputStreamReader leitor = new InputStreamReader(fluxo);
		BufferedReader buffer = new BufferedReader(leitor);
		String linha = buffer.readLine();
		List<List<String>> rows = new ArrayList<>();
			List<String> headers = Arrays.asList("Código", "Food Name", "Scientific Name", "Subgroup");
			rows.add(headers);
			int cont = 0;
		while (linha != null) {
			String[] GroupFruits = linha.split(",");
			if (GroupFruits[2].equalsIgnoreCase("Fruits")) {
					cont ++;
					String cod = Integer.toString(cont);
					rows.add(Arrays.asList(cod, GroupFruits[0], GroupFruits[1], GroupFruits[3]));
			}
			
			linha = buffer.readLine();

		}
	
		System.out.println(formatAsTable(rows));
	
		buffer.close();
	
		leitor.close();
	
		fluxo.close();
		
	} else {
		throw new IOException("Esse é Arquivo Inválido!"); 
		}

	} else {
		throw new IOException("Esse Diretório Inválido!");
		
		}

} 
	public static String formatAsTable(List<List<String>> rows) {
		int[] maxLengths = new int[rows.get(0).size()];
		for (List<String> row : rows) {
			for (int i = 0; i < row.size(); i++) {
				maxLengths[i] = Math.max(maxLengths[i], row.get(i).length());
			}
		}

		StringBuilder formatBuilder = new StringBuilder();
		
		for (int maxLength : maxLengths) {
			formatBuilder.append("%-").append(maxLength + 2).append("s");
		}
		String format = formatBuilder.toString();

		StringBuilder result = new StringBuilder();
		for (List<String> row : rows) {
			result.append(String.format(format, row.toArray(new String[0]))).append("\n");
		}
		return result.toString();
	}
}