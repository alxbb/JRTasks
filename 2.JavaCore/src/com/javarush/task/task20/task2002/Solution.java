package com.javarush.task.task20.task2002;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/* 
Читаем и пишем в файл: JavaRush
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            //File your_file_name = File.createTempFile("your_file_name", null);
            String your_file_name = "C:\\Temp\\outAssets.txt";
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
            for (int i = 0; i < 5; i++) {
                User user = new User();
                if( i != 2 ){
                    user.setBirthDate(new GregorianCalendar(2000, i,1).getTime());
                    user.setCountry(User.Country.RUSSIA);
                    user.setFirstName("Ivanov" + i);
                    user.setLastName("Ivan" + i);
                    user.setMale(true);
                }
                javaRush.users.add(user);
            }
            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //check here that javaRush object equals to loadedObject object - проверьте тут, что javaRush и loadedObject равны
            System.out.println(javaRush.equals(loadedObject));

            for (int i = 0; i < 5; i++) {
                User u1 = javaRush.users.get(i);
                User u2 = loadedObject.users.get(i);
                System.out.println(i + " " + u1.getFirstName() + " < - > " + u2.getFirstName());
                System.out.println(i + " " + u1.getLastName()  + " < - > " + u2.getLastName());
                System.out.println(i + " " + u1.getCountry()   + " < - > " + u2.getCountry());
                System.out.println(i + " " + u1.getBirthDate() + " < - > " + u2.getBirthDate());
                System.out.println(i + " " + u1.isMale()       + " < - > " + u2.isMale());
            }

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            PrintWriter pw = new PrintWriter(outputStream);
            if (users==null) return;
            for (int i = 0; i < users.size(); i++) {
                User u = users.get(i);
                pw.println( u.getFirstName()==null ? "null" : u.getFirstName()           );
                pw.println( u.getLastName ()==null ? "null" : u.getLastName ()           );
                pw.println( u.getCountry  ()==null ? "null" : u.getCountry  ()           );
                pw.println( u.getBirthDate()==null ? "null" : u.getBirthDate().getTime() );
                pw.println( u.isMale      ()       ?  true  : false );
            }
            pw.close();
        }

        public void load(InputStream inputStream) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            int i=0;
            User u = null;
            while(br.ready()){
                String in = br.readLine();
                switch (i){
                    case 0:
                        u = new User();
                        if (!"null".equals(in)) u.setFirstName(in);
                        break;
                    case 1:
                        if (!"null".equals(in)) u.setLastName(in);
                        break;
                    case 2:
                        if (!"null".equals(in)) u.setCountry(User.Country.valueOf(in));
                        break;
                    case 3:
                        if (!"null".equals(in)) {
                            Date dt = new Date();
                            dt.setTime(Long.parseLong(in));
                            u.setBirthDate(dt);
                        }
                        break;
                    case 4:
                        if (!"false".equals(in)) u.setMale(true);
                        users.add(u);
                        i=0;
                        continue;
                    default:
                        break;
                }
                i++;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}
