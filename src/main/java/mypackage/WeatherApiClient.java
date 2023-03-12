package mypackage;

import org.json.JSONObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


//TODO: delete hardcoded location
public class WeatherApiClient {

    private static String apiKey = "";

    public static void setApiKey(String weatherApiKey) {
        apiKey = weatherApiKey;
    }

    public static String getCurrentWeather() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://api.weatherapi.com/v1/current.json" +
                        "?key=" + apiKey +
                        "&q=Saint-Petersburg"))
                .GET()
                .build();
        HttpResponse<String> resp = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (resp.statusCode() != 200) {
            return "Error connecting to weather server";
        }

        JSONObject json = new JSONObject(resp.body());

        String temp = json.getJSONObject("current")
                .getNumber("temp_c").toString();
        String condition = json.getJSONObject("current")
                .getJSONObject("condition").getString("text");

        return  "Currently " + temp + "C, " + condition;
    }
}
