public class CurrencyConverter {
    public static void convert(int option, double amount) {
        if (amount <= 0) {
            System.out.println("Error: El monto debe ser mayor a 0");
            return;
        }

        String from = "";
        String to = "";
        String fromName = "";
        String toName = "";

        switch (option) {
            case 1 -> { from = "USD"; to = "ARS"; fromName = "Dólares"; toName = "Pesos Argentinos"; }
            case 2 -> { from = "ARS"; to = "USD"; fromName = "Pesos Argentinos"; toName = "Dólares"; }
            case 3 -> { from = "USD"; to = "BRL"; fromName = "Dólares"; toName = "Reales Brasileños"; }
            case 4 -> { from = "BRL"; to = "USD"; fromName = "Reales Brasileños"; toName = "Dólares"; }
            case 5 -> { from = "USD"; to = "COP"; fromName = "Dólares"; toName = "Pesos Colombianos"; }
            case 6 -> { from = "COP"; to = "USD"; fromName = "Pesos Colombianos"; toName = "Dólares"; }
            default -> {
                System.out.println("Opción no válida");
                return;
            }
        }

        try {
            System.out.printf("Obteniendo tasa de conversión %s → %s...\n", fromName, toName);
            double rate = ApiService.getConversionRate(from, to);
            double result = amount * rate;

            System.out.println("═".repeat(50));
            System.out.printf("💰 CONVERSIÓN: %s → %s\n", fromName, toName);
            System.out.printf("📊 Tasa actual: 1 %s = %.4f %s\n", from, rate, to);
            System.out.printf("💵 Monto ingresado: %.2f %s\n", amount, from);
            System.out.printf("🎯 Resultado: %.2f %s\n", result, to);
            System.out.println("═".repeat(50));

        } catch (RuntimeException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}