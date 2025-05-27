package utils;


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

}