import java.util.*;
import java.lang.Math;

public class tp02 {
	public static void main(String[] args) {
		int type = denominatorChooser();
		quadraticFormula();
	}

	public static int denominatorChooser() {
		Scanner sc = new Scanner(System.in);
		int type = 0;
		while(type <= 0 || type > 3) {
			System.out.println("Escolha o tipo de fatoração do denominador: ");
			System.out.println("1 - Linear\n2 - Quadrático\n3 - Misto");
			System.out.print("OPÇÃO: ");
			type = sc.nextInt();
		}
		return type;
	}

	public static void quadraticFormula() {
		Scanner sc = new Scanner(System.in);
		int a, b, c;
		System.out.println("Insira o coeficiente quadrático (ax^2): ");
		a = sc.nextInt();
		System.out.println("Insira o coeficiente linear (bx): ");
		b = sc.nextInt();
		System.out.println("Insira o termo costante (c): ");
		c = sc.nextInt();
		int d = delta(a, b, c); // Delta
		boolean valid = true;
		if(d < 0 || (Math.sqrt(d) % 1) != 0) valid = false;
		double xOne, xTwo;
		if(valid) {
			xOne = bhaskara(a, b, d, '+');
			xTwo = bhaskara(a, b, d, '-');
			System.out.println("x1: " + xOne + "\nx2: " + xTwo + "\n");
		}
		else {
			System.out.println("Inválido.");
		}

	}

	public static int delta(int a, int b, int c) {
		return (b*b) - (4 * a * c);
	}

	public static double bhaskara(int a, int b, int delta, char sym) {
		double x = 0;
		switch(sym) {
			case '+':
				x = ((-b) + Math.sqrt(delta))/(2 * a);
			break;
			case '-':
				x = ((-b) - Math.sqrt(delta))/(2 * a);
			break;
		}
		return x;
	}
}
