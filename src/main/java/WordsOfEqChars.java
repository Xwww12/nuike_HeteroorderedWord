import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class WordsOfEqChars {
    public static void main(String[] args) throws IOException {
        // 1.通过流读入数据
        URL url = new URL("http://wiki.puzzlers.org/pub/wordlists/unixdict.txt");   // 要读取的文件
        InputStreamReader isr = new InputStreamReader(url.openStream());    // 读取器
        BufferedReader reader = new BufferedReader(isr);// 缓冲读取器，每次读取一行（一行一个单词）

        // 2.通过单词排序后的顺序进行分类
        int maxSize = 0;
        HashMap<String, List<String>> map = new HashMap<>();
        String word;
        while ((word = reader.readLine()) != null) {
            char[] chs = word.toCharArray();
            Arrays.sort(chs);
            String key = new String((chs));

            // 判断key是否存在，若不存在，则添加
            map.putIfAbsent(key, new LinkedList<>());
            List<String> list = map.get(key);
            list.add(word);

            // 记录最大最大单词集的大小
            maxSize = Math.max(maxSize, list.size());
        }

        // 3.关闭流
        reader.close();

        // 4.打印包含最多单词的相同字符的单词集
        for (List<String> list : map.values()) {
            if (list.size() >= maxSize) {
                System.out.println(list.toString());
            }
        }
    }
}
