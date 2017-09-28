import com.sun.org.apache.xpath.internal.SourceTree;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

public class test {
    public static void main(String[] args) {
        Date d1 = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

        System.out.println(d1 + " " + d1.getTime() + " " +  sdf.format(d1).toUpperCase());
        List<Date> list = new ArrayList<>();
        for (long i = 1000000000L; i < 100000000000L; i+=10000000L) {
            list.add(new Date(i));
        }
//        Collections.shuffle(list);
//        Collections.sort(list,(x,y)->(y.compareTo(x)));
//        list.stream().map(s -> sdf.format(s)).forEach(System.out::println);

        System.out.println(">>>>>>>>");
        System.out.println(d1.getTime() + " <> " +  d1.getTime()/86400000*86400000);
        d1.setTime((d1.getTime()/86400000)*86400000);
        System.out.println(d1);



        Calendar cal = Calendar.getInstance();
        cal.setTime(d1);
        cal.set(Calendar.HOUR,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        System.out.println(cal.getTime());
        System.out.println(new Date(0L));
//        List<String> stringList = list.stream().map(s->sdf.format(s)).collect(Collectors.toList());
//        Collections.shuffle(stringList);
//        Collections.sort(stringList,(x,y)->(y.compareTo(x)));
//        stringList.stream().forEach(System.out::println);


        System.out.println(list.size());

    }
}
