package mypackage;

import org.json.JSONObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


//TODO: delete hardcoded API key, location
public class WeatherApiClient {

    public static String getCurrentWeather() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://api.weatherapi.com/v1/current.json" +
                        "?key=***" +
                        "&q=Saint-Petersburg"))
                .GET()
                .build();
        HttpResponse<String> resp = client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject json = new JSONObject(resp.body());
        String answer = "Currently "
                + json.getJSONObject("current").getNumber("temp_c").toString()
                + "C, "
                + json.getJSONObject("current").getJSONObject("condition").getString("text");
        return answer;
    }
}
