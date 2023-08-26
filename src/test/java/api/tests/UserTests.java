package api.tests;

import api.endpoints.UserEndPoints2;
import api.payloads.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests {
    Faker faker;
    User userPayload;

    public Logger logger;
    @BeforeClass
    public void setup() {
        faker = new Faker();
        userPayload = new User();
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().emailAddress());
        userPayload.setPassword(faker.internet().password(8, 12));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        //log file initialization
        logger = LogManager.getLogger(this.getClass());
        logger.info("User Tests initialized");
    }

    @Test(priority = 1)
    public void TestPostUser() {
        logger.info("Creating user: " + userPayload.getUsername());
        Response response = UserEndPoints2.createUser(userPayload);
        response.then().log().all();


        Assert.assertEquals(response.statusCode(), 200);
        logger.info("User created successfully");
    }

    @Test(priority = 2)
    public void TestGetUser() {
        logger.info("Reading user: " + userPayload.getUsername());
        Response response = UserEndPoints2.readUser(userPayload.getUsername());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("User read successfully");
    }

    @Test(priority = 3)
    public void TestPutUser() {
        logger.info("Updating user: " + userPayload.getUsername());
        //update user with new data
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().emailAddress());
        userPayload.setPassword(faker.internet().password(8, 12));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        Response response = UserEndPoints2.updateUser(userPayload.getUsername(), userPayload);
        response.then().log().all();

        Assert.assertEquals(response.statusCode(), 200);
        logger.info("User updated successfully");
    }

    @Test(priority = 4)
    public void TestLoginUser(){
        Response response = UserEndPoints2.loginUser(userPayload);
        response.then().log().all();
    }

    @Test(priority =5)
    public void TestLogoutUser(){
        Response response = UserEndPoints2.logoutUser();
        response.then().log().all();
    }


    @Test(priority = 6)
    public void TestDeleteUser() {
        logger.info("Deleting user: " + userPayload.getUsername());
        Response response = UserEndPoints2.deleteUser(userPayload.getUsername());
        response.then().log().all();

        Assert.assertEquals(response.statusCode(), 200);
        logger.info("User deleted successfully");
    }

}
