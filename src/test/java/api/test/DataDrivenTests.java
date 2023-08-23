package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviderUtility;
import api.utilities.TestDataGenerationUtility;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DataDrivenTests {
    TestDataGenerationUtility mine = new TestDataGenerationUtility();


    public DataDrivenTests() throws Exception {
        mine.generateTestData("TestData", "Sheet1", 10, 8, 12);
    }

    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviderUtility.class)
    public void testPostUser(String id, String username, String firstName, String lastName, String email, String password, String phone) {
        //TODO: fix the id here so it actually works
        User userPayload = new User();
        Float floatId = null;
        Integer intId = null;
        try{
            // id to float to int
            floatId = Float.parseFloat(id);
            intId = floatId.intValue();
        }
        catch (NumberFormatException e){
            System.out.println("Error converting id to integer");
            intId = 0;
        }

        userPayload.setId(intId);
        userPayload.setUsername(username);
        userPayload.setFirstName(firstName);
        userPayload.setLastName(lastName);
        userPayload.setEmail(email);
        userPayload.setPassword(password);
        userPayload.setPhone(phone);

        Response response = UserEndPoints.createUser(userPayload);
        Assert.assertEquals(response.statusCode(), 200);

    }

    @Test(priority = 2, dataProvider = "Usernames", dataProviderClass = DataProviderUtility.class)
    public void TestDeleteUser(String username) {
        Response response = UserEndPoints.deleteUser(username);
        response.then().log().all();

        Assert.assertEquals(response.statusCode(), 200);
    }
}
