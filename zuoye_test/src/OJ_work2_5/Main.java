package OJ_work2_5;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String re = sc.next();//正则表达式
        String str = sc.next();//要匹配的字符串
        Pattern pattern = Pattern.compile(re.replace("*",".*?"));
        Matcher matcher = pattern.matcher(str);
        String minStr="";
        if (matcher.find()) {
            minStr = matcher.group();
        }
        String MaxStr="";
        //接下来处理最大匹配：
        String newRE=re.replace("*",".*");
        pattern=Pattern.compile(newRE);
        matcher.reset();
        matcher = pattern.matcher(str);
        if(matcher.find()){
            MaxStr= matcher.group();
        }
        System.out.println(minStr);
        System.out.println(MaxStr);
    }
}
