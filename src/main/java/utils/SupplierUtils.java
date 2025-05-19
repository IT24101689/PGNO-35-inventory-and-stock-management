package utils;

import java.io.*;
import java.nio.file.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SupplierUtils {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    // Modified merge sort to work with arrays
    public static void mergeSort(String[][] items, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(items, left, mid);
            mergeSort(items, mid + 1, right);
            merge(items, left, mid, right);
        }
    }

    private static void merge(String[][] items, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        String[][] leftArray = new String[n1][];
        String[][] rightArray = new String[n2][];

        for (int i = 0; i < n1; i++) {
            leftArray[i] = items[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = items[mid + 1 + j];
        }

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            try {
                Date date1 = dateFormat.parse(leftArray[i][6]);
                Date date2 = dateFormat.parse(rightArray[j][6]);

                if (date1.compareTo(date2) <= 0) {
                    items[k++] = leftArray[i++];
                } else {
                    items[k++] = rightArray[j++];
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        while (i < n1) {
            items[k++] = leftArray[i++];
        }

        while (j < n2) {
            items[k++] = rightArray[j++];
        }
    }

     public static String[][] getSupplierStockLines(String supplierUsername, String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        boolean found = false;
        String[][] tempArray = new String[100][];
        int count = 0;

        while ((line = reader.readLine()) != null) {
            if (line.equals(supplierUsername)) {
                found = true;
                continue;
            }

            if (found) {
                if (line.trim().isEmpty()) break;
                String[] parts = line.split(",");
                if (parts.length >= 7) {
                    if (count == tempArray.length) {
                        // Resize array manually
                        String[][] newArray = new String[tempArray.length * 2][];
                        System.arraycopy(tempArray, 0, newArray, 0, tempArray.length);
                        tempArray = newArray;
                    }
                    tempArray[count++] = parts;
                }
            }
        }

        reader.close();

        // Final compacted array
        String[][] result = new String[count][];
        System.arraycopy(tempArray, 0, result, 0, count);
        return result;
    }

    public static String getExpiryStatus(String expiryDateStr) {
        try {
            Date today = new Date();
            Date expiryDate = dateFormat.parse(expiryDateStr);
            long diff = expiryDate.getTime() - today.getTime();
            long days = diff / (1000 * 60 * 60 * 24);

            if (expiryDate.before(today)) return "expired";
            else if (days <= 7) return "soon-expire";
            else return "normal";

        } catch (ParseException e) {
            return "unknown";
        }
    }
}
