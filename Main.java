import java.util.*;
import java.lang.Math;

public class Main {
	public static final String simboloIntegral = "∫";
	public static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int type = menu();

		while (type != 0) {
			switch(type) {
				case 1:
					linear();
					break;
				case 2:
					break;
				case 3:
					break;
				case 0:
				 	break;
			}

			type = menu();
		}

		//quadraticFormula();
	}

	public static int menu() {
		int type = 0;
		do {
			System.out.println("\nEscolha o tipo de fatoração do denominador: ");
			System.out.println("\n[0] - Sair do programa");
			System.out.println("\n[1] -\t" + simboloIntegral + "    Ax+B    dx\n\t ------------\n\t (x-x1)(x-x2)");

			System.out.println("\n[2] -\t" + simboloIntegral + "  Ax+B  dx\n\t --------\n\t ax²+bx+c");

			System.out.println("\n[3] -\t" + simboloIntegral + "     Ax+B     dx\n\t --------------\n\t (x²+x1)(x²+x2)");

			System.out.print("\nOPÇÃO [0 a 3]: ");
			
			type = sc.nextInt();
			System.out.println();

			if (type < 0 || type > 3) {
				System.out.println("\n-------Opção inválida! Digite um número entre 0 e 3-------");
			}
		} while (type < 0 || type > 3);

		return type;
	}

	public static void linear() {
		int A = 0,
			B = 0,
			C = 0,
			D = 0,
			x1 = 0,
			x2 = 0;

		System.out.print("Digite o valor de A: ");
		A = sc.nextInt();

		System.out.print("Digite o valor de B: ");
		B = sc.nextInt();

		System.out.print("Digite o valor de x1: ");
		x1 = sc.nextInt();

		System.out.print("Digite o valor de x2: ");
		x2 = sc.nextInt();

		C = (B + (A*x1)/(-x2+x1));
		//System.out.println(" " + C + " " + "\n-----\nx-" + x1)

		D = A - (B + (A*x1)/(-x2+x1));
		//System.out.println(" " + D " " + "\n-----\nx-" + x2);

		if (D > 0) {
			System.out.println("\nRESPOSTA:\n\t" + simboloIntegral + "    " + A + "x+" + B + "   dx  " + C + "   " + D + "\n\t ----------  = ---- + ----  = " + C + "ln|x-" + x1 + "| + " + D + "ln|x-" + x2 + "+ c\n\t (x-" + x1 + ")(x-" + x2 + ")");
		}
		else {
			System.out.println("\nRESPOSTA:\n\t" + simboloIntegral + "   " + A + "x+" + B + "  dx     " + C + "      " + D + "\n\t ----------  = ----- + ----- = " + C + "ln|x-" + x1 + "| " + D + "ln|x-" + x2 + "| + c\n\t (x-" + x1 + ")(x-" + x2 + ")    x - " + x1 + "   x - " + x2);
		}
	}

	public static void quadraticFormula() {
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
