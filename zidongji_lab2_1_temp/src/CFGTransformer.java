import java.util.*;

public class CFGTransformer {

    private static Set<String> N = new HashSet<>(); // 非终结符
    private static Set<String> T = new HashSet<>(); // 终结符
    private static Map<String, List<List<String>>> P = new HashMap<>(); // 生成式
    private static String S; // 起始符

    public static void main(String[] args) {
        inputLang();
        printLang();
        eliminateEpsilon();
        printLang();
        eliminateSingle();
        printLang();
        eliminateUseless();
        printLang();
        Scanner sc = new Scanner(System.in);
        sc.next();
    }

    private static void inputLang() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入非终结符，以空格分隔：");
        String[] nonTerminals = scanner.nextLine().split(" ");
        N.addAll(Arrays.asList(nonTerminals));

        System.out.println("请输入终结符，以空格分隔：");
        String[] terminals = scanner.nextLine().split(" ");
        T.addAll(Arrays.asList(terminals));

        while (true) {
            System.out.println("请输入一个生成式，以空格分隔每个符号，第一个符号为生成式的左端，以[empty]表示空串，以空行表示输入生成式结束：");
            String input = scanner.nextLine();
            if (input.isEmpty()) break;

            String[] state = input.split(" ");
            String left = state[0];
            List<String> right = Arrays.asList(state).subList(1, state.length);

            if (!N.contains(left)) {
                throw new IllegalArgumentException(left + " 不在非终结符集合内");
            }

            for (String symbol : right) {
                if (!N.contains(symbol) && !T.contains(symbol) && !symbol.equals("[empty]")) {
                    throw new IllegalArgumentException(symbol + " 不在符号集合内");
                }
            }

            if (!P.containsKey(left)) {
                P.put(left, new ArrayList<>());
            }
            P.get(left).add(right);
        }

        P.replaceAll((key, value) -> new ArrayList<>(new HashSet<>(value)));

        System.out.println("请输入起始符：");
        S = scanner.nextLine();
    }

    private static void printLang() {
        System.out.println("N: " + N);
        System.out.println("T: " + T);
        System.out.println("P:");
        for (Map.Entry<String, List<List<String>>> entry : P.entrySet()) {
            System.out.print("\t" + entry.getKey() + " -> ");
            for (List<String> right : entry.getValue()) {
                for (String symbol : right) {
                    System.out.print(symbol + " ");
                }
                System.out.print("| ");
            }
            System.out.println();
        }
        System.out.println("S: " + S);
    }

    private static void eliminateEpsilon() {
        System.out.println("消去epsilon产生式");
        Set<String> N0 = new HashSet<>();
        Set<String> N1 = new HashSet<>();

        for (Map.Entry<String, List<List<String>>> entry : P.entrySet()) {
            for (List<String> right : entry.getValue()) {
                if (right.size() == 1 && right.get(0).equals("[empty]")) {
                    N1.add(entry.getKey());
                    break;
                }
            }
        }

        while (!N0.equals(N1)) {
            N0 = new HashSet<>(N1);
            for (Map.Entry<String, List<List<String>>> entry : P.entrySet()) {
                for (List<String> right : entry.getValue()) {
                    if (N0.containsAll(right)) {
                        N1.add(entry.getKey());
                        break;
                    }
                }
            }
        }

        Map<String, List<List<String>>> P1 = new HashMap<>();
        for (Map.Entry<String, List<List<String>>> entry : P.entrySet()) {
            P1.put(entry.getKey(), new ArrayList<>());
            for (List<String> right : entry.getValue()) {
                int totalInN1 = 0;
                for (String symbol : right) {
                    if (N1.contains(symbol)) {
                        totalInN1++;
                    }
                }

                for (int num = 0; num < (1 << totalInN1); num++) {
                    List<String> newRight = new ArrayList<>();
                    int numInN1 = 0;
                    for (String symbol : right) {
                        if (N1.contains(symbol)) {
                            if ((num >> numInN1 & 1) == 1) {
                                newRight.add(symbol);
                            }
                            numInN1++;
                        } else {
                            newRight.add(symbol);
                        }
                    }
                    if (!newRight.isEmpty() && !newRight.get(0).equals("[empty]")) {
                        P1.get(entry.getKey()).add(newRight);
                    }
                }
            }
            P1.put(entry.getKey(), new ArrayList<>(new HashSet<>(P1.get(entry.getKey()))));
        }

        if (N1.contains(S)) {
            String newS = S + "1";
            while (N.contains(newS) || T.contains(newS)) {
                newS += "1";
            }
            P1.put(newS, Arrays.asList(Arrays.asList("[empty]"), Arrays.asList(S)));
            S = newS;
            N.add(S);
        }

        P = P1;
    }

    private static void eliminateSingle() {
        System.out.println("消去单产生式");
        Map<String, List<List<String>>> P1 = new HashMap<>();
        for (String A : N) {
            P1.put(A, new ArrayList<>());
            Set<String> N0 = new HashSet<>(Collections.singleton(A));
            Set<String> N1 = new HashSet<>(Collections.singleton(A));
            for (List<String> right : P.getOrDefault(A, new ArrayList<>())) {
                if (right.size() == 1 && N.contains(right.get(0))) {
                    N1.add(right.get(0));
                }
            }
            while (!N0.equals(N1)) {
                N0 = new HashSet<>(N1);
                for (String B : N0) {
                    for (List<String> right : P.getOrDefault(B, new ArrayList<>())) {
                        if (right.size() == 1 && N.contains(right.get(0))) {
                            N1.add(right.get(0));
                        }
                    }
                }
            }
            for (String B : N0) {
                for (List<String> right : P.getOrDefault(B, new ArrayList<>())) {
                    if (!(right.size() == 1 && N.contains(right.get(0)))) {
                        P1.get(A).add(right);
                    }
                }
            }
            P1.put(A, new ArrayList<>(new HashSet<>(P1.get(A))));
        }
        P = P1;
    }

    private static void eliminateUseless() {
        System.out.println("消去无用符号");

        // Step 1: 寻找最终可以产生终端字符串的符号
        Set<String> generatingSymbols = new HashSet<>();
        boolean added;
        do {
            added = false;
            for (Map.Entry<String, List<List<String>>> entry : P.entrySet()) {
                if (generatingSymbols.contains(entry.getKey())) continue;
                for (List<String> production : entry.getValue()) {
                    if (production.stream().allMatch(symbol -> T.contains(symbol) || generatingSymbols.contains(symbol))) {
                        generatingSymbols.add(entry.getKey());
                        added = true;
                        break;
                    }
                }
            }
        } while (added);

        // 删除非生成符号
        N.retainAll(generatingSymbols);
        P.entrySet().removeIf(entry -> !generatingSymbols.contains(entry.getKey()));
        for (List<List<String>> productions : P.values()) {
            productions.removeIf(production -> !production.stream().allMatch(symbol -> T.contains(symbol) || generatingSymbols.contains(symbol)));
        }

        // Step 2: 从起始符号查找可达符号
        Set<String> reachableSymbols = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(S);
        reachableSymbols.add(S);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (!P.containsKey(current)) continue;
            for (List<String> production : P.get(current)) {
                for (String symbol : production) {
                    if (!reachableSymbols.contains(symbol) && (N.contains(symbol) || T.contains(symbol))) {
                        reachableSymbols.add(symbol);
                        if (N.contains(symbol)) {
                            queue.add(symbol);
                        }
                    }
                }
            }
        }

        // 移除不可达符号
        N.retainAll(reachableSymbols);
        T.retainAll(reachableSymbols);
        P.entrySet().removeIf(entry -> !reachableSymbols.contains(entry.getKey()));
        for (List<List<String>> productions : P.values()) {
            productions.removeIf(production -> !production.stream().allMatch(reachableSymbols::contains));
        }
    }
}