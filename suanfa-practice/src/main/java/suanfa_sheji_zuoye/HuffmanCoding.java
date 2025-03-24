package suanfa_sheji_zuoye;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// 哈夫曼树节点结构
class HuffmanNode {
    char ch;
    int freq;
    HuffmanNode left;
    HuffmanNode right;

    // 构造方法
    HuffmanNode(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
        this.left = null;
        this.right = null;
    }
}

// 比较节点频率的比较器
class Compare implements Comparator<HuffmanNode> {
    public int compare(HuffmanNode left, HuffmanNode right) {
        return left.freq - right.freq;
    }
}

public class HuffmanCoding {

    // 递归生成哈夫曼编码
    private static void generateHuffmanCodes(HuffmanNode root, String code, Map<Character, String> codes) {
        if (root == null) {
            return;
        }

        // 到达叶子节点，存储字符的编码
        if (root.left == null && root.right == null) {
            codes.put(root.ch, code);
            return;
        }

        // 递归左子树和右子树
        generateHuffmanCodes(root.left, code + "0", codes);
        generateHuffmanCodes(root.right, code + "1", codes);
    }

    // 统计文本中的字符频率
    private static Map<Character, Integer> countCharacterFrequency(String text) {
        Map<Character, Integer> frequency = new HashMap<>();
        for (char ch : text.toCharArray()) {
            frequency.put(ch, frequency.getOrDefault(ch, 0) + 1);
        }
        return frequency;
    }

    // 构建哈夫曼树并生成编码
    private static Map<Character, String> buildHuffmanTree(Map<Character, Integer> frequency) {
        // 使用优先队列（最小堆）构建哈夫曼树
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>(new Compare());

        // 将每个字符和频率作为单独的节点加入最小堆
        for (Map.Entry<Character, Integer> entry : frequency.entrySet()) {
            pq.offer(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        // 构建哈夫曼树
        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();

            HuffmanNode internalNode = new HuffmanNode('\0', left.freq + right.freq);
            internalNode.left = left;
            internalNode.right = right;

            pq.offer(internalNode);
        }

        // 获取哈夫曼编码
        Map<Character, String> huffmanCodes = new HashMap<>();
        generateHuffmanCodes(pq.poll(), "", huffmanCodes);

        return huffmanCodes;
    }

    // 计算哈夫曼编码文本的存储比特数
    private static int calculateHuffmanBitSize(String text, Map<Character, String> huffmanCodes) {
        int bitSize = 0;
        for (char ch : text.toCharArray()) {
            bitSize += huffmanCodes.get(ch).length();
        }
        return bitSize;
    }

    // 计算定长编码的存储比特数
    private static int calculateFixedBitSize(String text, int bitLength) {
        return text.length() * bitLength;
    }

    // 从文件读取文本，指定字符集为 GBK
    private static String readFile(String filename) {
        StringBuilder text = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), Charset.forName("GBK")))) {
            String line;
            while ((line = br.readLine()) != null) {
                text.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return text.toString();
    }

    public static void main(String[] args) {
        // 从文件中读取文本
        String filename = "D:\\java_xuexi\\java01\\suanfa-practice\\src\\main\\java\\suanfa_sheji_zuoye\\input.txt";  //文件路径
        String text = readFile(filename);

        if (text.isEmpty()) {
            return; // 如果文件为空，退出程序
        }

        // 统计字符频率
        Map<Character, Integer> frequency = countCharacterFrequency(text);

        // 构建哈夫曼树并生成编码
        Map<Character, String> huffmanCodes = buildHuffmanTree(frequency);

        // 输出符号表（字符及其频率和哈夫曼编码）
        System.out.println("下面分别是(符号, 频次, 哈夫曼编码):");
        for (Map.Entry<Character, Integer> entry : frequency.entrySet()) {
            char ch = entry.getKey();
            int freq = entry.getValue();
            String code = huffmanCodes.get(ch);
            if(ch=='\n'){
                System.out.printf("%2s : %5d : %s\n", "换行", freq, code);
            }else if(ch==' '){
                System.out.printf("%2s : %5d : %s\n", "空格", freq, code);
            }else {
                System.out.printf("%3c : %5d : %s\n", ch, freq, code);
            }
        }

        // 计算哈夫曼编码文本所需的比特数
        int huffmanBitSize = calculateHuffmanBitSize(text, huffmanCodes);
        System.out.println("\n哈夫曼编码后所需的比特数为: " + huffmanBitSize);

        // 计算定长编码的比特数 (假设使用8个比特表示每个字符)
        int fixedBitSize = calculateFixedBitSize(text, 8);
        System.out.println("使用定长编码(定长为8比特)所需的比特数为: " + fixedBitSize);
    }
}
