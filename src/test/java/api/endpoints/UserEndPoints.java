package api.endpoints;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints {
    public static Response createUser(User payload)
    {
        Response response = given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .body(payload)
            .when()
            .post(Routes.USER_URL);
        return response;
    }


    public static Response readUser(String username)
    {
        Response response = given()
                .pathParam("username", username)
            .when()
            .post(Routes.GET_USER_URL);
        return response;
    }

    public static Response updateUser(String username, User payload)
    {
        Response response = given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .pathParam("username", username)
            .body(payload)
            .when()
            .put(Routes.GET_USER_URL);
        return response;
    }

    //delete user
    public static Response deleteUser(String username)
    {
        Response response = given()
            .pathParam("username", username)
            .when()
            .delete(Routes.GET_USER_URL);
        return response;
    }

}
