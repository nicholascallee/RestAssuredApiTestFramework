package api.endpoints;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class UserEndPoints2 {

    static ResourceBundle getUrl(){
        ResourceBundle rb = ResourceBundle.getBundle("routes");
        return rb;
    }

    public static Response createUser(User payload){
        String userUrl = getUrl().getString("USER_URL");

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(userUrl);
        return response;
    }


    public static Response readUser(String username){
        String getUserUrl = getUrl().getString("GET_USER_URL");
        Response response = given()
                .pathParam("username", username)
                .when()
                .get(getUserUrl);
        return response;
    }

    public static Response updateUser(String username, User payload){
        String updateUserUrl = getUrl().getString("UPDATE_USER_URL");
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", username)
                .body(payload)
                .when()
                .put(updateUserUrl);
        return response;
    }

    public static Response deleteUser(String username){
        String deleteUserUrl = getUrl().getString("DELETE_USER_URL");
        Response response = given()
                .pathParam("username", username)
                .when()
                .delete(deleteUserUrl);
        return response;
    }

    public static Response loginUser(User payload){
        String loginUserUrl = getUrl().getString("LOGIN_USER_URL");
        Response response = given()
                .queryParam("username",payload.getUsername())
                .queryParam("password",payload.getPassword())
                .get(loginUserUrl);
        return response;
    }

    public static Response logoutUser(){
        String logoutUserUrl = getUrl().getString("LOGOUT_USER_URL");
        Response response = given()
                .get(logoutUserUrl);
        return response;
    }

    public static Response createUsersWithArray(User[] userArrayPayload){
        String createUsersWithArrayUrl = getUrl().getString("CREATE_USERS_WITH_ARRAY_URL");
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(userArrayPayload)
                .when()
                .post(createUsersWithArrayUrl);
        return response;
    }
}