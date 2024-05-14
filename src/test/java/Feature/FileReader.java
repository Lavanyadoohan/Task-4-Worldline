package Feature;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileReader {

    public static Object[][] readData(String filePath, String sheetName) throws IOException {
        FileInputStream fileInputStream = null;
        XSSFWorkbook workbook = null;

        try {
            fileInputStream = new FileInputStream(new File(filePath));
            workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = workbook.getSheet(sheetName);

            int rowCount = sheet.getPhysicalNumberOfRows();
            int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

            Object[][] data = new Object[rowCount - 1][colCount];

            for (int i = 1; i < rowCount; i++) {
                for (int j = 0; j < colCount; j++) {
                    if (sheet.getRow(i).getCell(j).getCellType() == CellType.STRING) {
                        data[i - 1][j] = sheet.getRow(i).getCell(j).getStringCellValue();
                    } else if (sheet.getRow(i).getCell(j).getCellType() == CellType.NUMERIC) {
                        data[i - 1][j] = sheet.getRow(i).getCell(j).getNumericCellValue();
                    }
                }
            }

            return data;
        } finally {
            if (workbook != null) {
                workbook.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        }
    }
}
