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
					quadratico();
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
		
		do {
			System.out.print("Insira o coeficente linear (Ax): ");
			A = sc.nextInt();

			if (A == 0) {
				System.out.println("\n-------Erro! O numerador não pode ser 0!-------\n");
			}
		} while (A == 0);

		do {
			System.out.print("Insira o termo constante (B): ");
			B = sc.nextInt();
			if (B == 0) {
				System.out.println("\n-------Erro! O numerador não pode ser 0!-------\n");
			}
		} while (B == 0);

		do {
			System.out.print("Insira o valor de x1: ");
			x1 = sc.nextInt();

			System.out.print("Insira o valor de x2: ");
			x2 = sc.nextInt();

			if (x1 == x2) {
				System.out.println("\n-------Erro! Os termos x1 e x2 não podem ser iguais!-------\n");
			}
		} while (x1 == x2);

		C = calculaTermoC(A, B, x1, x2);
		//System.out.println(" " + C + " " + "\n-----\nx-" + x1)

		D = calculaTermoD(A, B, x1, x2);
		//System.out.println(" " + D " " + "\n-----\nx-" + x2);

		resultadoLinear(A, B, C, D, x1, x2);
	}

	public static void resultadoLinear(int A, int B, int C, int D, int x1, int x2) {
		if (D > 0) {
			System.out.println("\nRESPOSTA:\n\t" + simboloIntegral + "  " + A + "x+" + B + "  dx   " + C + "       " + D + "\n\t ----------  = ------ + ------  = " + C + "ln|x-" + x1 + "| + " + D + "ln|x-" + x2 + "| + c\n\t (x-" + x1 + ")(x-" + x2 + ")  x - " + x1 + "   x - " + x2);
		}
		else {
			System.out.println("\nRESPOSTA:\n\t" + simboloIntegral + "   " + A + "x+" + B + "   dx    " + C + "      " + D + "\n\t ----------  = ----- + ----- = " + C + "ln|x-" + x1 + "| " + D + "ln|x-" + x2 + "| + c\n\t (x-" + x1 + ")(x-" + x2 + ")    x - " + x1 + "   x - " + x2);
		}
	}

	public static int calculaTermoC(int A, int B, int x1, int x2) {
		return (B + (A*x1)/(-x2+x1));
	}

	public static int calculaTermoD(int A, int B, int x1, int x2) {
		return A - (B + (A*x1)/(-x2+x1));
	}

	public static void quadratico() {
		int A = 0,
			B = 0,
			C = 0,
			D = 0,
			a = 0,
			b = 0,
			c = 0;
			double x1 = 0,
				   x2 = 0;

		do {
			System.out.print("Insira o coeficiente linear (Ax): ");
			A = sc.nextInt();
			
			if (A == 0) {
				System.out.println("\n-------Erro! O numerador não pode ser 0!-------\n");
			}
		} while (A == 0);

		do {
			System.out.print("Insira o termo constante (B): ");
			B = sc.nextInt();
			if (B == 0) {
				System.out.println("\n-------Erro! O numerador não pode ser 0!-------\n");
			}
		} while (B == 0);

		System.out.print("Insira o coeficiente quadrático (ax²): ");
		a = sc.nextInt();;

		System.out.print("Insira o coeficiente linear (bx): ");
		b = sc.nextInt();

		System.out.print("Insira o termo costante (c): ");
		c = sc.nextInt();

		int d = delta(a, b, c); // Delta
								//
		boolean valid = true;
		if(d < 0 || (Math.sqrt(d) % 1) != 0) valid = false;
		
		if(valid) {
			double x1temp = bhaskara(a, b, d, '+');
			double x2temp = bhaskara(a, b, d, '-');
			x1 = (int) x1temp;
			x2 = (int) x2temp;
			System.out.println("\nNOVA FÓRMULA APÓS FAZER BHASKARA:\n\t" + simboloIntegral + "    " + A + "x+" + B + "   dx\n\t ----------\n\t (x" + x1 + ")(x" + x2 + ")");
		}
		else {
			System.out.println("\n-------Delta inválido!-------\n");
		}
	}

	public static int delta(int a, int b, int c) {
		return (b*b) - (4 * a * c);
	}

	public static double bhaskara(int a, int b, int delta, char sym) {
		double x = 0;
		switch(sym) {
			case '-':
				x = ((-b) + Math.sqrt(delta))/(2 * a);
			break;
			case '+':
				x = ((-b) - Math.sqrt(delta))/(2 * a);
			break;
		}
		return x;
	}
}
