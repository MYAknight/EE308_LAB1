import org.junit.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import java.io.*;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TASK2 {


    public static void main(String[] args) throws Exception {
        TASK2 task2 = new TASK2();
        task2.sol("C:\\Users\\huaqi\\Desktop\\LAB.c", 4);//this is your url
    }

    @Test
    public void sol(String url, int level) throws IOException {
        File file = new File(url);
        BufferedReader br
                = new BufferedReader(new FileReader(file));
        StringBuilder s1 = new StringBuilder();
        String st;
        while ((st = br.readLine()) != null) {
            s1.append(st);
//            System.out.println(st);
        }
        String s2 = s1.toString();
        if (level == 1) {
            sol1(s2);
        } else if (level == 2) {
            sol2(s2);
        } else if (level == 3) {
            sol3(s2);
        } else if (level == 4) {
            sol4(s2);
        }
    }

    @Test
    public void sol3(String base) {
        sol2(base);
        int count = match2(base, 3);
        System.out.println("if-else num: " + count);
    }

    @Test
    @ParameterizedTest
    @ValueSource(strings = {"if(){}else{}if{}else if{}else{}if{}if{}"})
    public void sol4(String base) {
        sol3(base);
        int count = match2(base, 4);
        System.out.println("if-elseif-else num: " + count);
    }

    @Test
    public int match2(String str, int level) {
        Stack<String> s1 = new Stack();
        Pattern pattern = Pattern.compile("else *if|else|if");
        Matcher matcher = pattern.matcher(str);
        int esif = 0;
        int ifes = 0;
        boolean temp = true;
        while (matcher.find()) {
            String substring = str.substring(matcher.start(), matcher.end());
            if ("if".equals(substring)) {
                s1.push(substring);
            } else if ("else".equals(substring)) {
                while (!"if".equals(s1.peek())) {
                    String pop = s1.pop();
                    if (temp) {
                        esif++;
                        temp = false;
                    }
                }
                if (temp == true) {
                    ifes++;
                }
                s1.pop();
                temp = true;
            } else {
                s1.push(substring);
            }
        }
        if (level == 3) {
            return ifes;
        } else {
            return esif;
        }
    }

    @Test
    public void sol1(String base) {
        int count = 0;
        String keyword[] = {"auto", "break", "case", "char", "const", "continue", "default", "do", "double", "else", "enum", "extern",
                "float", "for", "goto", "if", "int", "long", "register", "return", "short", "signed", "sizeof", "static",
                "struct", "switch", "typedef", "unsigned", "union", "void", "volatile", "while"};
        for (int i = 0; i < keyword.length; i++) {
            String temp = "[^a-zA-Z_]" + keyword[i] + "[^a-zA-Z_]";
            int match = match(base, temp);
            count += match;
        }
        System.out.println("total num: " + count);
    }

    @Test
    public void sol2(String base) {
        sol1(base);
        System.out.println("switch num: " + match(base, "switch"));
        String[] temp = base.split("switch");
        System.out.print("case num: ");
        for (int i = 1; i < temp.length; i++) {
            System.out.print(match(temp[i], "case") + " ");
        }
        System.out.println();
    }


    @Test
    public int match(String str, String target) {

        Pattern pattern = Pattern.compile(target);//匹配一个或多个数字字符
        Matcher matcher = pattern.matcher(str);
        int count = 0;
        while (matcher.find()) {
            ++count;
            str.substring(matcher.start(), matcher.end());
        }
        return count;
    }
}
