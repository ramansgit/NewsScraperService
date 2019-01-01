package com.news.api;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class NewsScraperSearchApiTest {


    @Test
    public void testAuthorsFound() throws ClientProtocolException, IOException {
        // Given
        final String jsonMimeType = "application/json";
        final HttpUriRequest request = new HttpGet("http://localhost:4560/api/v1/newsscraper/search/articles?author=Poorvaja");
       
        // When
        final HttpResponse response = HttpClientBuilder.create().build().execute(request);
        String out = EntityUtils.toString(response.getEntity());
        JsonElement element = new Gson().fromJson (out, JsonElement.class);
        JsonObject jsonObj = element.getAsJsonObject();
        System.out.println(jsonObj.get("code").getAsString());
        assertEquals("SUCCESS", jsonObj.get("code").getAsString());
    }
    
    @Test
    public void testArticleFoundForTitleAndDescription() throws ClientProtocolException, IOException {
        // Given
        final String jsonMimeType = "application/json";
        final HttpUriRequest request = new HttpGet("http://localhost:4560/api/v1/newsscraper/search/articles?title=Poorvaja&description=hello");
        // When
        final HttpResponse response = HttpClientBuilder.create().build().execute(request);
        String out = EntityUtils.toString(response.getEntity());
        JsonElement element = new Gson().fromJson (out, JsonElement.class);
        JsonObject jsonObj = element.getAsJsonObject();
        System.out.println(jsonObj.get("code").getAsString());
        assertEquals("SUCCESS", jsonObj.get("code").getAsString());
    }
    
    @Test
    public void testArticleFoundForGivenAuthor() throws ClientProtocolException, IOException {
        // Given
        final String jsonMimeType = "application/json";
        final HttpUriRequest request = new HttpGet("http://localhost:4560/api/v1/newsscraper/search/articles?start=0&page=10");
       

        // When
        final HttpResponse response = HttpClientBuilder.create().build().execute(request);
        String out = EntityUtils.toString(response.getEntity());
        JsonElement element = new Gson().fromJson (out, JsonElement.class);
        JsonObject jsonObj = element.getAsJsonObject();
        System.out.println(jsonObj.get("code").getAsString());
        assertEquals("SUCCESS", jsonObj.get("code").getAsString());
    }
}
