import apiMethods.GetQuery;
import apiMethods.PostSearchHotels;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class HotelsTestCases {
    JSONObject APIBody = new JSONObject();
    List<String> cities = Arrays.asList("Amman", "Dubai", "Abu Dhabi");
    Random rnd = new Random();
    PostSearchHotels postSearchHotels = new PostSearchHotels();
    GetQuery getQuery = new GetQuery();

    @Test
    public void postSearchHotelsHappyCases() throws JSONException {
        LocalDate today = LocalDate.now();
        String checkInDate = today.plusDays((long) 20).toString();
        String checkOutDate = today.plusDays((long) 24).toString();
        String city = cities.get(rnd.nextInt(cities.size()));
        JSONArray roomsInfo = new JSONArray();
        JSONObject roomsObject = new JSONObject();
        int[] kidsAges = {2, 3};
        roomsObject.put("adultsCount", 3);
        roomsObject.put("kidsAges", kidsAges);
        roomsInfo.put(roomsObject);
        APIBody.put("checkIn", checkInDate);
        APIBody.put("checkOut", checkOutDate);
        APIBody.put("roomsInfo", roomsInfo);
        APIBody.put("query", city);
        Response response = postSearchHotels.hotelSearch(APIBody.toString());
        postSearchHotels.verifySearch(response, 200);


    }

    @Test
    public void postSearchHotelsNegativeCases() throws JSONException {
        String city = cities.get(rnd.nextInt(cities.size()));
        JSONArray roomsInfo = new JSONArray();
        JSONObject roomsObject = new JSONObject();
        String[] kidsAges = {};
        roomsObject.put("adultsCount", 2);
        roomsObject.put("kidsAges", kidsAges);
        roomsInfo.put(roomsObject);
        APIBody.put("checkIn", "2024-01-23");
        APIBody.put("checkOut", "2024-01-10");
        APIBody.put("roomsInfo", roomsInfo);
        APIBody.put("query", city);
        Response response = postSearchHotels.hotelSearch(APIBody.toString());
        postSearchHotels.verifySearch(response, 400);
    }

    @Test
    public void getQueryAPIHappy() {
        String city = cities.get(rnd.nextInt(cities.size()));
        Response response = getQuery.getQuery(city);
        getQuery.verifyGetQuery(response, 200);
    }

    @Test
    public void getQueryAPINegative() {
        Response response = getQuery.getQuery("gffhhghghg");
        getQuery.verifyGetQuery(response, 200);
    }
}
