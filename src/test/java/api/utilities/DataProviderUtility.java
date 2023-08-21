package api.utilities;

import org.testng.annotations.DataProvider;

public class DataProviderUtility {

    @DataProvider(name = "Data")
    public String[][] getData() throws Exception {
        String path = System.getProperty("user.dir") + "/testData/TestData.xlsx";
        ExcelUtility excel = new ExcelUtility(path);
        int rowNum = excel.getRowCount("Sheet1");
        int colCount = excel.getCellCount("Sheet1", 1);
        String data[][] = new String[rowNum][colCount];
        for (int i = 1; i < rowNum + 1; i++) {
            for (int j = 0; j < colCount; j++) {
                data[i - 1][j] = excel.getCellData("Sheet1", i, j);
            }
        }
        return data;
    }

    @DataProvider(name = "Usernames")
    public String[] getUsernames() throws Exception {
        String path = System.getProperty("user.dir") + "/testData/TestData.xlsx";
        ExcelUtility excel = new ExcelUtility(path);
        int rowNum = excel.getRowCount("Sheet1");
        String usernameData[] = new String[rowNum];
        for (int i = 1; i < rowNum + 1; i++) {
            usernameData[i - 1] = excel.getCellData("Sheet1", i, 1);
        }
        return usernameData;
    }


}
