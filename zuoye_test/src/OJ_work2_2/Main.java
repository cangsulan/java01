package OJ_work2_2;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        HashSet<Integer> setA = new HashSet<>();
        for (int i = 0; i < n; i++) {
            setA.add(scanner.nextInt());
        }

        int m = scanner.nextInt();
        HashSet<Integer> setB = new HashSet<>();
        for (int i = 0; i < m; i++) {
            setB.add(scanner.nextInt());
        }
        HashSet<Integer> intersection = new HashSet<>(setA);
        intersection.retainAll(setB);

        HashSet<Integer> union = new HashSet<>(setA);
        union.addAll(setB);

        HashSet<Integer> complement = new HashSet<>(setA);
        complement.removeAll(setB);

        TreeSet<Integer> sortedSet1 = new TreeSet<>(intersection);
        for (int num : sortedSet1) {
            System.out.print(num + " ");
        }
        System.out.println();
        TreeSet<Integer> sortedSet2 = new TreeSet<>(union);
        for (int num : sortedSet2) {
            System.out.print(num + " ");
        }
        System.out.println();
        TreeSet<Integer> sortedSet3 = new TreeSet<>(complement);
        for (int num : sortedSet3) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
