package nyc.c4q.androidtest_unit4final;

import java.util.List;

/**
 * Created by justiceo on 1/7/18.
 */

public class Sort {

  /**
   * Sorts a list using the selection sort algorithm.
   * See lecture on sorting: https://github.com/C4Q/AC-Android/tree/v2/DSA/sorting
   *
   * When `isAscending` is true, the list is sorted in ascending alphabetical order from a to z,
   * otherwise it is sorted in descending order from z to a.
   */
  public static void selectionSort(List<String> list, boolean isAscending) {
    // TODO: Implement selection sort.

    for (int i = 0; i < list.size(); i++) {
      int temp = i;
      for (int j = 0; j < list.size(); j++) {

        if (isAscending) {
          if (list.get(j).compareTo(list.get(temp)) > 0) {
            temp = j;
          }
        } else {
          if (list.get(j).compareTo(list.get(temp)) < 0) {
            temp = j;
          }
        }

        list.set(i, list.set(temp, list.get(i)));
      }
    }
  }
}
