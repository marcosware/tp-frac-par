import java.util.*;
import java.lang.Math;

public class Main {
	public static final String simboloIntegral = "∫";
	public static final Scanner sc = new Scanner(System.in);

	
	public static String formatDen(int x) {
        if (x >= 0)
            return "(x - " + x + ")"; // Se x1=3 -> (x - 3)
        else
            return "(x + " + Math.abs(x) + ")"; // Se x1=-3 -> (x + 3)
    }
	
	public static String formatDenFrac(int x) {
      	 if (x >= 0)
            return "x - " + x; // Se x1=3 -> x - 3
         else
            return "x + " + Math.abs(x); // Se x1=-3 -> x + 3
    }

 	public static String formatLn(int x) {
        if (x >= 0)
            return "ln|x - " + x + "|"; // Se x1=3 -> ln|x - 3|
        else
            return "ln|x + " + Math.abs(x) + "|"; // Se x1=-3 -> ln|x + 3|
    }

    public static String formatTerm(int n) {
        if (n >= 0)
            return String.valueOf(n); // Mantém o número, ex: 7
        else
            return String.valueOf(n); // Mantém o sinal de menos, ex: -5
    }

	public static String formatNum(int n) {
        if (n >= 0)
            return "+ " + n; // Se B=5 -> + 5
        else
            return "- " + Math.abs(n); // Se B=-5 -> - 5
    }

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
		String sA = A + "x " + formatNum(B); 
        String sC = formatTerm(C);           
        String sD = formatNum(D);            
        String denCompleto = formatDen(x1) + formatDen(x2); 
        String den1 = formatDenFrac(x1); 
        String den2 = formatDenFrac(x2); 
        String ln1 = formatLn(x1);       
        String ln2 = formatLn(x2);       

        System.out.print("\nRESPOSTA:\n\t" + simboloIntegral + " " + sA + " dx" +
                         "         " + sC + "      " + sD + " \n"); 

        System.out.print("\t -------------  = ----- + -----  =  " + C + ln1 + " " + sD + ln2 + " + c\n");

        System.out.println("\t " + denCompleto + "   " + den1 + "   " + den2);
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
								
		boolean valid = true;
		if(d < 0 || (Math.sqrt(d) % 1) != 0) valid = false;
		
		if(valid) {
			x1 = bhaskara(a, b, d, '+');
			x2 = bhaskara(a, b, d, '-');
			System.out.printf("\nNOVA FÓRMULA APÓS FAZER BHASKARA:\n\t%s   %dx+%d    dx\n\t ----------\n\t (x%.0f)(x%.0f)\n", simboloIntegral, A, B, x1, x2);

			int x1term = (int) Math.round(x1);
			int x2term = (int) Math.round(x2);
			C = calculaTermoC(A, B, x1term, x2term);
			D = calculaTermoD(A, B, x1term, x2term);

			resultadoLinear(A, B, C, D, x1term, x2term);
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
