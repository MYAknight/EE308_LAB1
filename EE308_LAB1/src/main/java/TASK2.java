import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TASK2 {


    public static void main(String[] args) throws Exception {
        sol("C:\\Users\\huaqi\\Desktop\\LAB.c", 2);


    }


    public static void sol(String url, int level) throws IOException {
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
            sol1(s2);
            sol2(s2);
        }else if (level==3){

        }else if (level==4){

        }
    }

    /*  public static int sol1(int a, String base, String target) {
          int count = 0;
          int p = base.indexOf(target, a);
          if (p == -1) {
              return count;
          } else {
              return 1 + sol1(p + target.length(), base, target);
          }
      }*/
    public static void sol1(String base) {
        int count = 0;
        String keyword[] = {"auto", "break", "case", "char", "const", "continue", "default", "do", "double", "else", "enum", "extern",
                "float", "for", "goto", "if", "int", "long", "register", "return", "short", "signed", "sizeof", "static",
                "struct", "switch", "typedef", "unsigned", "union", "void", "volatile", "while"};
        for (int i = 0; i < keyword.length; i++) {
            String temp = "[^a-zA-Z_]"+keyword[i]+"[^a-zA-Z_]";
            int match = match(base, temp);
            count += match;
        }
        System.out.println("total num: " + count);
    }

    public static void sol2(String base) {
        System.out.println("switch num: " + match(base, "switch"));
        String[] temp = base.split("switch");
        System.out.print("case num: ");
        for (int i = 1; i < temp.length; i++) {
            System.out.print(match(temp[i], "case") + " ");
        }
        System.out.println();
    }

    public static int match(String str, String target) {
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
