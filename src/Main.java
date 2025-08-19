import java.util.Scanner;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option = 0;

        while (option != 7) {
            System.out.println("\n=== Conversor de Monedas ===");
            System.out.println("1. Dólar → Peso Argentino");
            System.out.println("2. Peso Argentino → Dólar");
            System.out.println("3. Dólar → Real Brasileño");
            System.out.println("4. Real Brasileño → Dólar");
            System.out.println("5. Dólar → Peso Colombiano");
            System.out.println("6. Peso Colombiano → Dólar");
            System.out.println("7. Salir");
            System.out.print("Elige una opción: ");

            try {
                option = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error: Por favor ingresa un número válido.");
                sc.next(); // Limpiar el buffer del scanner
                continue;
            }

            if (option >= 1 && option <= 6) {
                System.out.print("Ingresa el valor a convertir: ");
                try {
                    double amount = sc.nextDouble();
                    CurrencyConverter.convert(option, amount);
                } catch (InputMismatchException e) {
                    System.out.println("Error: Por favor ingresa un valor numérico válido.");
                    sc.next(); // Limpiar el buffer del scanner
                }
            } else if (option == 7) {
                System.out.println("Gracias por usar el conversor. ¡Hasta luego!");
            } else {
                System.out.println("Opción no válida. Por favor elige entre 1 y 7.");
            }
        }
        sc.close();
    }
}