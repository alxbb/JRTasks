package com.javarush.task.task19.task1916;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Отслеживаем изменения
*/
public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException {
        //        String s1 = "строка1";
//        String s2 = "строка2";
//        System.out.println(s1.compareTo(s2));
//        System.out.println(s2.compareTo(s2));
//        System.out.println(s2.compareTo(s1));


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String fname1 = br.readLine();
        String fname2 = br.readLine();
        br.close();

        if(!(new File(fname1).exists()) || !(new File(fname2).exists()) ) return;
        BufferedReader f1r = new BufferedReader(new FileReader(fname1));
        BufferedReader f2r = new BufferedReader(new FileReader(fname2));
        List<String> l1 = new ArrayList<>();
        List<String> l2 = new ArrayList<>();
        while (f1r.ready()){l1.add(f1r.readLine());}
        while (f2r.ready()){l2.add(f2r.readLine());}
        f1r.close();
        f2r.close();
        int s1 = l1.size();
        int s2 = l2.size();

        for (int i = 0, j = 0; i < s1; ) {

            switch (l1.get(i).compareTo(l2.get(j))){
                case 0:
                    lines.add(new LineItem(Type.SAME, l1.get(i)));
                    j++;i++;
                    break;
                case -1:
                    if(l1.get(i).compareTo(l2.get(j+1))==0){
                        lines.add(new LineItem(Type.ADDED, l2.get(j++)));
                    } else lines.add(new LineItem(Type.REMOVED, l1.get(i++)));
                    break;
                case 1:
                    lines.add(new LineItem(Type.ADDED, l2.get(j++)));
                    break;
            }
            if(j == s2-1 && i < s1-1)j--;
        }

        for(LineItem li : lines){
            System.out.println(li);
        }
        f1r.close();
        f2r.close();
    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }

        @Override
        public String toString() {
            return this.type + " " + this.line;
        }
    }
}

//public class Solution {
//    public static List<LineItem> lines = new ArrayList<LineItem>();
//
//    public static void main(String[] args) {
//    }
//
//
//    public static enum Type {
//        ADDED,        //добавлена новая строка
//        REMOVED,      //удалена строка
//        SAME          //без изменений
//    }
//
//    public static class LineItem {
//        public Type type;
//        public String line;
//
//        public LineItem(Type type, String line) {
//            this.type = type;
//            this.line = line;
//        }
//    }
//}
