package com.hat.javaadvance.string;

public class String_ {
    public static void main(String[] args) {
        StringIntern();
    }
    private static void StringIntern(){
        // 1.直接从常量池中创建两个字符串常量
        // 先从常量池中寻找有无[abc]常量，如果没有则在常量池中创建了[abc]字符串常量
        String s1 = "abc";  // 常量池无[abc]，所以在常量池中创建[abc],然后返回[abc]的引用给s1
        String s2 = "abc";  // 常量池有[abc]，返回[abc]的引用给s2
        System.out.println(s1 == s2); // true  s1与s2指向同一个

        System.out.println("------------------------------------------------");

        // new String()最终都会在堆中生成一个实例，不过它会先在常量池中寻找有无[abc]，
        // 如果有的话s3会在堆中指向常量池中的常量引用，否者会先在常量池中创建一个字符串常量，
        // 再在堆中指向这个新创的字符串常量的引用，因此new String()可能会创建一个对象，也可能会创建两个对象
        String s3 = new String("abc");
        System.out.println(s1 == s3);  // false   s1在常量池中，s3在堆中，引用肯定不相同

        // 如果常量池中没有[abc]字符串，则在常量池中创建一个[abc]字符串常量，并返回[abc]在常量池中的引用
        // 如果常量池中有[abc]字符串，则返回[abc]在常量池中的引用
        s3.intern();
        System.out.println(s1 == s3);  // false  s1的的引用在常量池，s3的引用在堆中
        System.out.println(s3 == s3);  // true   s3的引用在堆中，同一个对象
        String s4 = s3.intern();       // 执行intern并把返回的引用赋值给s4
        System.out.println(s3 == s4);  // false   s3的引用在堆中，s4的引用在常量池中
        System.out.println(s1 == s4);  // true    s1的引用与s4的引用都在常量池中，且指向同一个常量

        System.out.println("------------------------------------------------");

        // new StringBuilder()的结果与new String()一样
        String s5 = new StringBuilder("abc").toString();
        System.out.println(s1 == s5);  // false
        s5.intern();
        System.out.println(s1 == s5);  // false
        System.out.println(s5 == s5);  //true
        String s6 = s5.intern();
        System.out.println(s5 == s6);  //false
        System.out.println(s1 == s6);  //true

        System.out.println("------------------------------------------------");
        // 两个字符串常量使用+号拼接，在编译阶段会先把s7变量右边的表达式先合并，再去常量池中寻找合并后的常量，
        // 存在则返回引用，否则先在常量池中创建字符串再返回引用
        String s7 = "a" + "bc";   // 常量池中有abc，所以直接返回引用
        System.out.println(s1 == s7);  // true s1与s7在常量池中指向同一个引用

        System.out.println("------------------------------------------------");
        String s8 = new String("a") + new String("bc");
        System.out.println(s1 == s8);  // false  s8是在堆中，s1在常量池中
        s8.intern();
        System.out.println(s1 == s8);  // false  s8在堆中，s1在常量池中
        String s9 = s8.intern();
        System.out.println(s8 == s9);  // false  s8在堆中，s9在常量池中
        System.out.println(s1 == s9);  // true   s9与s1都在常量池中，且都指向同一个引用

        System.out.println("------------------------------------------------");
        String s10 = "ab";
        // 使用变量+常量来拼接字符串，实际上是调用了stringBuilder.append()来进行拼接，
        // 所以s11是在堆中生成一个对象
        String s11 = s10 + "c";
        System.out.println(s1 == s11);  // false   s11在堆中
        s11.intern();
        System.out.println(s1 == s11);  // false   s11在堆中
        String s12 = s11.intern();      // 在常量池中返回字符串常量的引用给s12
        System.out.println(s11 == s12);  // fasle  s11在堆中，s12在常量池中
        System.out.println(s1 == s12);  // true    s1与s12都在常量池中并且都指向同一个引用

        System.out.println("------------------------------------------------");
        final String s13 = "ab";
        // 用final修饰的字符串等同于字符串常量，所以s13+"c"等同于 "ab"+"c"
        String s14 = s13 + "c";
        System.out.println(s1 == s14); // true  s1与s14均在常量池中且都指向同一个引用

        final String s15 = new String("ab");
        String s16 = s15+"c";
        System.out.println(s1 == s16);  // false
    }
}
