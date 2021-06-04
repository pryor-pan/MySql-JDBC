/**
 * Remarks:
 * Author:panlai
 * :Date:2021/6/1
 */
public class Test {
    private static void testMethod(){
        System.out.println("TestMethod");
    }
    public static void main(String[] args) {
        ((Test)null).testMethod();
        String str= "aBC";
        String x = str.toUpperCase();
        String y = str.replace("A","a");
        System.out.println(y);
        System.out.println(x);
        System.out.println(str);
    }
}
