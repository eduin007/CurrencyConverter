import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class ApiService {
    private static final String API_KEY = "daf6c0927891c3a158079469";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/";

    public static double getConversionRate(String from, String to) {
        try {
            String urlStr = BASE_URL + from + "/" + to;
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("Error en la API: Código HTTP " + responseCode);
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject json = new JSONObject(response.toString());

            if (!json.has("conversion_rate")) {
                throw new RuntimeException("Campo 'conversion_rate' no encontrado en la respuesta de la API");
            }

            double rate = json.getDouble("conversion_rate");

            if (rate <= 0) {
                throw new RuntimeException("Tasa de conversión inválida: " + rate);
            }

            return rate;

        } catch (Exception e) {
            System.err.println("Error al obtener tasa de conversión: " + e.getMessage());
            throw new RuntimeException("No se pudo obtener la tasa de conversión. Verifica tu conexión e intenta nuevamente.");
        }
    }
}