package view;

import java.io.IOException;
import controller.IArquivosController;
import controller.CArquivosController;

public class Principal {
	public static void  main (String[] args) {
	IArquivosController arqCont = new CArquivosController();
	
		String path = "C:\\TEMP";
		String nome = "generic_food.csv";
		
		try {
			arqCont.readFile(path, nome);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		

	}

}
