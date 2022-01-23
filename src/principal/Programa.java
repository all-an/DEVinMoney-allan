package principal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entidades.Conta;

public class Programa {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		List<Conta> listaContas = new ArrayList<>();
		
		Integer test = 9;
		System.out.println(test.equals(9));
		
		System.out.print("Digite o cpf: ");
		
		
		listaContas.add(new Conta(scanner.next()));
		
		System.out.println(listaContas.get(0).toString());
		
		System.out.println(listaContas.get(0).getCpf());
	}

}
