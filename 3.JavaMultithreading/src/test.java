import java.util.Date;
public class test {
    public static void main(String[] args) {
        Date d1 = new Date();
        Date d2 = new Date(117,8,23);
        Date d3 = new Date(117,8,24);
        Date d4 = new Date(1506200000000L);

        System.out.println(d1 + " " + d1.getTime());
        System.out.println(d2 + " " + d2.getTime());
        System.out.println(d3 + " " + d3.getTime());
        System.out.println(d4 + " " + d4.getTime());
        System.out.println(1506200400000L-1506114000000L);
        System.out.println(System.currentTimeMillis());
    }
}
