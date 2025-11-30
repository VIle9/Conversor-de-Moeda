import com.google.gson.JsonObject;

public class ConversorMoeda {

    static class RateResponse{
        String result;
        String base_code;
        JsonObject conversion_rates;
    }
}
