package com.spring;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExcelFileJoiner2 {
    public static void combinedExcel(String projectPath) {
        try {
            List<String> inputFiles = new ArrayList<>();
            inputFiles.add(projectPath+"/countFolder/Count_Local Approver_HCMEL report.xlsx");
            inputFiles.add(projectPath+"/countFolder/Count_People Manager_HCMEL report.xlsx");
            inputFiles.add(projectPath+"/countFolder/Count_Role Name_HCMEL report.xlsx");
            inputFiles.add(projectPath+"/countFolder/Count_Type Of Project_HCMEL report.xlsx");
            // Add more input file paths as needed

            // Create a new Excel workbook for the combined data
            XSSFWorkbook combinedWorkbook = new XSSFWorkbook();
            XSSFSheet combinedSheet = combinedWorkbook.createSheet("CombinedSheet");

            int rowIndex = 0;

            for (String inputFile : inputFiles) {
                FileInputStream fileInputStream = new FileInputStream(inputFile);
                XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

                // Loop through sheets in the current workbook
                for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                    XSSFSheet sheet = workbook.getSheetAt(i);

                    // Copy data from the current sheet to the combined sheet
                    for (int j = 0; j <= sheet.getLastRowNum(); j++) {
                        XSSFRow sourceRow = sheet.getRow(j);
                        XSSFRow destRow = combinedSheet.createRow(rowIndex++);

                        if (sourceRow != null) {
                            for (int k = 0; k < sourceRow.getLastCellNum(); k++) {
                                XSSFCell sourceCell = sourceRow.getCell(k);
                                if (sourceCell != null) {
                                    XSSFCell destCell = destRow.createCell(k);
                                    destCell.setCellValue(sourceCell.getRow().getCell(k).toString());
                                }
                            }
                        }
                    }
                }

                // Close the input stream for the current workbook
                fileInputStream.close();
            }

            String targetPathCountCreateFolder = projectPath + "\\combinedFolder";

            File folder = new File(targetPathCountCreateFolder);

            // Check if the folder already exists
            if (!folder.exists()) {
                boolean folderCreated = folder.mkdirs();
            } else {
                System.out.println("Folder already exists.");
            }

            String targetPathCount = folder+"\\combined2.xlsx";
            // Save the combined workbook to a new Excel file
            FileOutputStream outFile = new FileOutputStream(targetPathCount);
            combinedWorkbook.write(outFile);
            outFile.close();

            System.out.println("Excel files joined successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExcelFileJoiner2.combinedExcel("C:\\Users\\SATYASAH\\IntelliJProjects\\ExcelComparatorNew\\countFolder");
    }
}
