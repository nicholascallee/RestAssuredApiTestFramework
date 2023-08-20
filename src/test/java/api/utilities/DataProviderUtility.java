package api.utilities;

import org.testng.annotations.DataProvider;

public class DataProviderUtility {

    @DataProvider(name = "Data")
    public String[][] getData(String dataFileName, String sheetName) throws Exception {
        String path = System.getProperty("user.dir") + "/testdata/" + dataFileName + ".xlsx";
        ExcelUtility excel = new ExcelUtility(path);
        int rowNum = excel.getRowCount(sheetName);
        int colCount = excel.getCellCount(sheetName, 1);
        String data[][] = new String[rowNum][colCount];
        for (int i = 1; i <= rowNum; i++) {
            for (int j = 0; j < colCount; j++) {
                data[i - 1][j] = excel.getCellData(sheetName, i, j);
            }
        }
        return data;
    }

    @DataProvider(name = "Usernames")
    public String[] getUsernames(String dataFileName, String sheetName) throws Exception {
        String path = System.getProperty("user.dir") + "/testdata/" + dataFileName + ".xlsx";
        ExcelUtility excel = new ExcelUtility(path);
        int rowNum = excel.getRowCount(sheetName);
        String usernameData[] = new String[rowNum];
        for (int i = 1; i <= rowNum; i++) {
            usernameData[i - 1] = excel.getCellData(sheetName, i, 1);
        }
        return usernameData;
    }


}
