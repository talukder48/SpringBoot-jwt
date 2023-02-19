package com.geo.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@RestController
public class GeoController {
	@GetMapping("/Test")
	public void Location() {
		Map<String, Double> location = new HashMap<>();
		try {
			OpenStreetMapGetLocationReverse(37.7749, -122.4194);
			OpenStreetMapGetLocation("DHAKA");
			// GoogleMapAPIGetLocation();
			// Rapid API Credential Issue
			// GEOCODING();
			// ReverseGEOCODING();
		} catch (Exception e) {
		}
	}

	void OpenStreetMapGetLocation(String Address) {
		String url = "https://nominatim.openstreetmap.org/search?" + "q=" + Address + "&format=json";
		OkHttpClient client = new OkHttpClient();
		try {
			Request request = new Request.Builder().url(url).get().build();
			Response response = client.newCall(request).execute();
			int responseCode = response.code();
			String jasonString = response.body().toString();
			System.out.println(jasonString);
			Gson gson = new GsonBuilder().setLenient().create();
			@SuppressWarnings("rawtypes")
			Map map = gson.fromJson(jasonString.substring(1, jasonString.length() - 1), Map.class);
		} catch (Exception e) {
		}
	}

	void OpenStreetMapGetLocationReverse(double lat, double lng) {
		String url = "https://nominatim.openstreetmap.org/reverse?format=json&lat=" + lat + "&lon=" + lng+ "&zoom=18&addressdetails=1";
		OkHttpClient client = new OkHttpClient();
		try {
			Request request = new Request.Builder().url(url).get().build();
			Response response = client.newCall(request).execute();
			int responseCode = response.code();
			String jasonString = response.body().toString();
			System.out.println(jasonString);
			Gson gson = new GsonBuilder().setLenient().create();
			@SuppressWarnings("rawtypes")
			Map map = gson.fromJson(jasonString.substring(1, jasonString.length() - 1), Map.class);
		} catch (Exception e) {
		}
	}

	public void GoogleMapAPIGetLocation() {
		OkHttpClient client = new OkHttpClient();
		try {
			String address = "1600 Amphitheatre Parkway, Mountain View, CA";
			String API_KEY = "";
			String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + address + "&key=" + API_KEY;
			Request request = new Request.Builder().url(url).build();
			Response response = client.newCall(request).execute();
			if (!response.isSuccessful()) {
				throw new IOException("Unexpected code " + response);
			}
			System.out.println(response.body().string());
		} catch (Exception e) {
		}
	}

	public void ReverseGEOCODING() {
		OkHttpClient client = new OkHttpClient();
		try {
			Request request = new Request.Builder()
					.url("https://google-maps-geocoding.p.rapidapi.com/geocode/json?latlng=40.714224%2C-73.96145&language=en")
					.get()
					.addHeader("X-RapidAPI-Key", "3c7dbd0b24msh8d15aa98a362e91p1e1b3fjsnf478a6b2d66b")
					.addHeader("X-RapidAPI-Host", "google-maps-geocoding.p.rapidapi.com")
					.build();
			Response response = client.newCall(request).execute();
			System.out.println(response.body().string());
		} catch (Exception e) {
		}
	}

	public void GEOCODING() {
		OkHttpClient client = new OkHttpClient();
		try {
			Request request = new Request.Builder()
					.url("https://google-maps-geocoding.p.rapidapi.com/geocode/json?address=164%20Townsend%20St.%2C%20San%20Francisco%2C%20CA&language=en")
					.get()
					.addHeader("X-RapidAPI-Key", "3c7dbd0b24msh8d15aa98a362e91p1e1b3fjsnf478a6b2d66b")
					.addHeader("X-RapidAPI-Host", "google-maps-geocoding.p.rapidapi.com")
					.build();
			Response response = client.newCall(request).execute();
			System.out.println(response.body().string());
		} catch (Exception e) {
		}
	}
}
