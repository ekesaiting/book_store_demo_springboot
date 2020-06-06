package com.feng.book_store_demo.script.task;

/**
 * @Author shf
 * @Date 2020/6/4 15:10
 */
public class CategoryUtil {
    public static String getCategory(String s){
        if (s.contains("算法")){
            return "算法";
        }
        else if(s.contains("Java")&&!s.contains("JavaScript")||s.contains("Spring")){
            return "Java";
        }
        else if (s.contains("C")){
            return "C/C++";
        }
        else if (s.contains("Python")){
            return "Python";
        }
        else {
            return "其他";
        }

    }
}
