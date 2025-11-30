import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        try{
            EnvLoader.load();
            String apiKey = EnvLoader.get("API_KEY");
            String baseCurrency = EnvLoader.get("BASE_CURRENCY");

            String urlStr = String.format(
                    "https://v6.exchangerate-api.com/v6/5ebefaaef58decac25752f8d/latest/BRL",
                    apiKey, baseCurrency
            );

            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();

            Gson gson = new Gson();
            ConversorMoeda.RateResponse resp = gson.fromJson(
                    new InputStreamReader(conn.getInputStream()),
                    ConversorMoeda.RateResponse.class
            );

            if ("success".equalsIgnoreCase(resp.result)) {
                double rateToUSD = resp.conversion_rates.get("USD").getAsDouble();
                System.out.println("1 " + baseCurrency + " = " + rateToUSD + " USD");
            }else {
                System.out.println("Erro na resposta da API: result=" + resp.result);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
