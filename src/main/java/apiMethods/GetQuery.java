package apiMethods;

import helper.PropertiesReader;
import io.restassured.response.Response;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class GetQuery {

    public Response getQuery(String city) {
        return given().when().log().all()
                .header("Content-Type", "application/json")
                .header("Token", PropertiesReader.getProperty("token"))
                .baseUri(PropertiesReader.getProperty("url"))
                .get(PropertiesReader.getProperty("query-path").replace("{city}", city));
    }

    public void verifyGetQuery(Response response, int statusCode) {
        Assert.assertNotNull(response);
        response.then().log().all().assertThat().statusCode(statusCode);
    }
}
