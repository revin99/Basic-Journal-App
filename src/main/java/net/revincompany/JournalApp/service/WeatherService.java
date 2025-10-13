package net.revincompany.JournalApp.service;

import net.revincompany.JournalApp.api_response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sun.net.www.http.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;

@Service
public class WeatherService {

    private static final String apikey ="d9aaeda81888ddb72e4ec8804ec66022";

    private static final String API = "http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private static RestTemplate restTemplate;

    public static void getWeather(String city) throws IOException {
        // Replace placeholders in the API URL
        String url = "http://api.weatherstack.com/current?access_key=d9aaeda81888ddb72e4ec8804ec66022&query=Mumbai";
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            request.addHeader("User-Agent", "Mozilla/5.0");

            String response = client.execute(request, httpResponse ->
                    EntityUtils.toString(httpResponse.getEntity()));

            System.out.println(response);
        }



//        // Make the GET request and map the response directly to WeatherResponse
//        WeatherResponse body = restTemplate.getForObject(finalAPI, WeatherResponse.class);
//
//        if (body == null) {
//            System.out.println("No response from Weather API!");
//        }
//
//        return body;
    }

}
