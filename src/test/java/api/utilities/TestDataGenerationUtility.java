package api.utilities;

import api.payload.User;
import com.github.javafaker.Faker;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;

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
            userPayload = new User();
            userPayload = generateUserPayload(passwordMin, passwordMax);
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
        faker = new Faker();
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().emailAddress());
        userPayload.setPassword(faker.internet().password(passwordMin, passwordMax));
        userPayload.setPhone(faker.phoneNumber().cellPhone());
        return userPayload;
    }


    //method to create excel file if given name does not exist
    public XSSFWorkbook createExcelFile(String fileName, String sheetName) throws Exception {
        String path = System.getProperty("user.dir") + "/testdata/" + fileName + ".xlsx";
        //if path does not exist, create file
        if (!new File(path).exists()) {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet(sheetName);
            FileOutputStream fo = new FileOutputStream(path);
            workbook.write(fo);
            fo.close();
            return workbook;
        }
        else {
            System.out.println("File already exists");
            return null;
        }
    }

    public XSSFWorkbook insertDataIntoExcelWorkbook(XSSFWorkbook workbook, User[] userPayloadArray, String sheetName){
        XSSFSheet sheet = workbook.getSheet(sheetName);
        for(int i = 0; i < userPayloadArray.length; i++){
            sheet.createRow(i + 1).createCell(0).setCellValue(userPayloadArray[i].getId());
            sheet.getRow(i + 1).createCell(1).setCellValue(userPayloadArray[i].getUsername());
            sheet.getRow(i + 1).createCell(2).setCellValue(userPayloadArray[i].getFirstName());
            sheet.getRow(i + 1).createCell(3).setCellValue(userPayloadArray[i].getLastName());
            sheet.getRow(i + 1).createCell(4).setCellValue(userPayloadArray[i].getEmail());
            sheet.getRow(i + 1).createCell(5).setCellValue(userPayloadArray[i].getPassword());
            sheet.getRow(i + 1).createCell(6).setCellValue(userPayloadArray[i].getPhone());
        }
        return workbook;
    }

    public void writeExcelWorkbookToFile(XSSFWorkbook workbook, String fileName) throws Exception {
        String path = System.getProperty("user.dir") + "/testdata/" + fileName + ".xlsx";
        FileOutputStream fo = new FileOutputStream(path);
        workbook.write(fo);
        workbook.close();
        fo.close();
    }

    public void generateTestData(String fileName, String sheetName, int countOfPayloads, int passwordMin, int passwordMax) throws Exception {
        XSSFWorkbook workbook = createExcelFile(fileName, sheetName);
        userPayloadArray = new User[countOfPayloads];
        userPayloadArray = generateUserPayloads(countOfPayloads, passwordMin, passwordMax);
        workbook = insertDataIntoExcelWorkbook(workbook, userPayloadArray, sheetName);
        writeExcelWorkbookToFile(workbook, fileName);
    }

}
