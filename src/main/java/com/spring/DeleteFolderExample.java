package com.spring;

import java.io.File;

public class DeleteFolderExample {
    public static void main(String[] args) {
        // Specify the path of the folder you want to delete
        String folderPath = "C:\\Users\\SATYASAH\\IntelliJProjects\\ExcelComparatorNew\\countFolder";

        // Create a File object representing the folder
        File folder = new File(folderPath);

        // Check if the folder exists
        if (folder.exists()) {
            // Delete the folder and its contents recursively
            boolean folderDeleted = deleteFolder(folder);

            if (folderDeleted) {
                System.out.println("Folder deleted successfully.");
            } else {
                System.err.println("Failed to delete folder.");
            }
        } else {
            System.out.println("Folder does not exist.");
        }
    }

    private static boolean deleteFolder(File folder) {
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    boolean success = deleteFolder(file);
                    if (!success) {
                        return false;
                    }
                }
            }
        }
        return folder.delete();
    }
}
