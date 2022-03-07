import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NewMethod {

    public static List<Integer> Sorter(List<Integer> list) {
        return list.stream().sorted().collect(Collectors.toList());
    }

    public static Integer MinFinder(List<Integer> list) {
        return list.stream().sorted().collect(Collectors.toList()).get(0);
    }

    static List<Integer> CreateTopK2(List<Integer> mainRow, List<Integer> newMatrix, int k) {
        for (Integer matrix : newMatrix) {
            if (mainRow.size() < k)
                mainRow.add(matrix);
        }
        return Sorter(mainRow);
    }

    static List<Integer> CreateTopKn(List<Integer> mainRow, List<Integer> newMatrix, int k) {
        int minNumber = MinFinder(mainRow);
        int count = 0;
        for (Integer number : newMatrix) {
            if (number > minNumber) {
                mainRow.add(number);
                count++;
            }
        }
        System.out.println(Sorter(mainRow.subList(count, count + k)));
        return mainRow.subList(count, count + k);
    }

    static void TopK1AndK2Maker() {

        List<List<Integer>> allLists = new ArrayList<>();
        int kParameter = Input.getInputValue("Enter the K parameter: ");
        int listsNumber = Input.getInputValue("Enter the number of list: ");
        for (int i = 0; i < listsNumber; i++) {
            ArrayList<Integer> subList = new ArrayList<>();
            allLists.add(subList);
        }

        List<Integer> List0 = allLists.get(0);
        List<Integer> mainRow = Sorter(ListMaker(List0));
        System.out.println("The K1 : " + mainRow);

        List<Integer> List1 = allLists.get(1);
        List<Integer> sortedList1 = Sorter(ListMaker(List1));
        List<Integer> topK2 = CreateTopK2(mainRow, sortedList1, kParameter);
        System.out.println("The K2 : " + topK2);

        List<Integer> List2 = allLists.get(2);
        List<Integer> sortedList2 = Sorter(ListMaker(List2));
        topK3AndHigherMaker(topK2, sortedList2, kParameter);

    }

    static void topK3AndHigherMaker(List<Integer> mainRow, List<Integer> newMatrix, int k) {

        CreateTopKn(mainRow, newMatrix, k);
        topK3AndHigherMaker(mainRow, newMatrix, k);
    }


    private static List<Integer> ListMaker(List<Integer> inputList) {
        int listLength = Input.getInputValue("Enter list length");
        for (int i = 0; i < listLength; i++) {
            int number = Input.getInputValue("Enter number");
            inputList.add(number);
        }
        return inputList;
    }

    public static void main(String[] args) {

    }


}
