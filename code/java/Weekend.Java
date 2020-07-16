import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
public class Weekend{

    private static boolean isWeekend(LocalDate date){
        var dayOfWeek = date.getDayOfWeek();
        return dayOfWeek.equals(DayOfWeek.SUNDAY) || dayOfWeek.equals(DayOfWeek.SATURDAY);
    }

    public static void main(String[] args) {
        var date = LocalDate.now();
        var days = 365;
        if(date.isLeapYear()){
            days = 366;
        }
        date = LocalDate.of(date.getYear(),Month.JANUARY,1);
        var dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for(var i = 0;i<days;i++){
            System.out.printf("%s,%s\n",date.format(dtf),isWeekend(date));
            date = date.plusDays(1);
        }
    }

}