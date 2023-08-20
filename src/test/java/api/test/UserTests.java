package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests {
    Faker faker;
    User userPayload;
    @BeforeClass
    public void setupData() {
        faker = new Faker();
        userPayload = new User();
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().emailAddress());
        userPayload.setPassword(faker.internet().password(8, 12));
        userPayload.setPhone(faker.phoneNumber().cellPhone());
    }

    @Test(priority = 1)
    public void TestPostUser() {
        Response response = UserEndPoints.createUser(userPayload);
        response.then().log().all();

        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(priority = 2)
    public void TestGetUser() {
        Response response = UserEndPoints.readUser(userPayload.getUsername());
        response.then().log().all();

        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(priority = 3)
    public void TestPutUser() {
        //update user with new data
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().emailAddress());
        userPayload.setPassword(faker.internet().password(8, 12));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        Response response = UserEndPoints.updateUser(userPayload.getUsername(), userPayload);
        response.then().log().all();

        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(priority = 4)
    public void TestDeleteUser() {
        Response response = UserEndPoints.deleteUser(userPayload.getUsername());
        response.then().log().all();

        Assert.assertEquals(response.statusCode(), 200);
    }
}
