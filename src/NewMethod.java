import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NewMethod {

    private List<List<Integer>> allLists;
    private int currentRow;
    private int listNumber;
    private int rowLimit;
    private int kCounter = 3;
    private int count;

    public NewMethod() {
        this.allLists = new ArrayList<>();
        currentRow = 3;
        listNumber = 3;
    }

    public void setRowLimit(int rowLimit) {
        this.rowLimit = rowLimit;
    }

    public static List<Integer> Sorter(List<Integer> list) {
        return list.stream().sorted().collect(Collectors.toList());
    }

    public static Integer MinFinder(List<Integer> list) {
        return Sorter( list).get(0);
    }

    static List<Integer> TopK2(List<Integer> mainRow, List<Integer> newMatrix, int k) {
        for (Integer matrix : newMatrix) {
            if (mainRow.size() < k) {
                mainRow.add(matrix);
            }
        }
        return Sorter(mainRow);
    }

    public void CreateTopK1AndK2() {
        int kParameter = Input.getInputValue("Enter the K parameter: ");
        int listsNumber = Input.getInputValue("Enter the number of list: ");
        int rowLimit = Input.getInputValue("Enter row limit: ");
        setRowLimit(rowLimit);

        sublistCreator(listsNumber);

        List<Integer> List0 = allLists.get(0);
        List<Integer> mainRow = Sorter(ListMaker(List0));
        System.out.println("K1 : " + mainRow);

        List<Integer> List1 = allLists.get(1);
        List<Integer> newMatrix1 = Sorter(ListMaker(List1));
        List<Integer> topK2 = TopK2(mainRow, newMatrix1, kParameter);
        System.out.println("K2 : " + topK2);

        List<Integer> List2 = allLists.get(2);
        List<Integer> newMatrix2 = Sorter(ListMaker(List2));
        CreateTopKn(topK2, newMatrix2, kParameter);
    }

    private void sublistCreator(int listsNumber) {
        for (int i = 0; i < listsNumber; i++) {
            ArrayList<Integer> subList = new ArrayList<>();
            allLists.add(subList);
        }
    }


    public void CreateTopKn(List<Integer> mainRow, List<Integer> newMatrix, int k) {
        int minNumber = MinFinder(mainRow);

        if (currentRowIsLastRow(mainRow, k)) return;

        topKMaker(mainRow, newMatrix, minNumber);
        List<Integer> newSortedTopK = Sorter(mainRow.subList(count, count + k));
        System.out.println("K" + kCounter + " : " + newSortedTopK);
        allLists.add(newSortedTopK);
        listNumber++;
        kCounter++;
        currentRow++;
        List<Integer> newSortedInputList = Sorter(ListMaker(allLists.get(listNumber)));
        CreateTopKn(newSortedTopK, newSortedInputList,k);
    }

    private void topKMaker(List<Integer> mainRow, List<Integer> newMatrix, int minNumber) {
        for (Integer number : newMatrix) {
            if (number > minNumber) {
                mainRow.add(number);
                count++;
            }
        }
    }

    private boolean currentRowIsLastRow(List<Integer> mainRow, int k) {
        if (currentRow == rowLimit) {
            List<Integer> newSortedTopK = Sorter(mainRow.subList(count, count + k));
            System.out.println("K" + kCounter + " : " + newSortedTopK);
            allLists.add(newSortedTopK);
            return true;
        }
        return false;
    }

    private static List<Integer> ListMaker(List<Integer> inputList) {
        int listLength = Input.getInputValue("Enter list length");
        for (int i = 0; i < listLength; i++) {
            int number = Input.getInputValue("Enter number");
            inputList.add(number);
        }
        return inputList;
    }

}
