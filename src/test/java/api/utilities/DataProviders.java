package api.utilities;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.DataProvider;

public class DataProviders {
	@DataProvider(name = "data")
	public static String[][] dataSender() throws EncryptedDocumentException, IOException {
		return ExcelUtility.getAllData("User");
	}
}
