package api.utilities;

import api.payload.User;
import com.github.javafaker.Faker;

public class TestDataGenerationUtility {
    public User userPayload;
    public User[] userPayloadArray;
    public Faker faker;

    public TestDataGenerationUtility(){
        faker = new Faker();
        userPayload = new User();

    }

    public User[] generateUserPayloads(int countOfPayloads, int passwordMin, int passwordMax){
        for(int i = 0; i < countOfPayloads; i++){
            userPayload = generateUserPayload(passwordMin, passwordMax);
            userPayload.setPhone(faker.phoneNumber().cellPhone());
            userPayloadArray[i] = userPayload;
        }
        return userPayloadArray;
    }
    public User[] generateUserPayloads(int countOfPayloads){
        for(int i = 0; i < countOfPayloads; i++){
            userPayload = generateUserPayload();
            userPayloadArray[i] = userPayload;
        }
        return userPayloadArray;
    }

    public User generateUserPayload(){
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().emailAddress());
        userPayload.setPassword(faker.internet().password(8, 12));
        userPayload.setPhone(faker.phoneNumber().cellPhone());
        return userPayload;
    }

    public User generateUserPayload(int passwordMin, int passwordMax){
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().emailAddress());
        userPayload.setPassword(faker.internet().password(passwordMin, passwordMax));
        userPayload.setPhone(faker.phoneNumber().cellPhone());
        return userPayload;
    }


}
