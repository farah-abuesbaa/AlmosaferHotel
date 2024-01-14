package apiMethods;

import helper.PropertiesReader;
import io.restassured.response.Response;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class PostSearchHotels {
    public Response hotelSearch(String body) {
        return given().when().log().all()
                .header("Content-Type", "application/json")
                .header("Token", PropertiesReader.getProperty("token"))
                .body(body)
                .baseUri(PropertiesReader.getProperty("url"))
                .post(PropertiesReader.getProperty("hotel-path"));
    }

    public void verifySearch(Response response, int statusCode) {
        Assert.assertNotNull(response);
        response.then().log().all().assertThat().statusCode(statusCode);
    }

}
