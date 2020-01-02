package tonels.util;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class TimeTest {

    @Test
    public void t1(){
        Date date = DateUtils.asDate(LocalDate.now());
        Date date2 = DateUtils.asDate(LocalDateTime.now());
        LocalDate localDate = DateUtils.asLocalDate(Date.from(Instant.now()));
        LocalDateTime localDateTime = DateUtils.asLocalDateTime(Date.from(Instant.now()));
        System.out.println("test scuuess");
    }

    @Test
    public void t2() throws ParseException {
        String a1 = "2019-11-14 12:13:16";
        String a2 = "2019-11-14";

        Date date1 = Date.from(Instant.now());
        LocalDate now1 = LocalDate.now();
        LocalDateTime now2 = LocalDateTime.now();

        Date dateFor1 = DateUtils.parseDate(a1, DateUtils.DATE_FORMAT_19);
        Date dateFor2 = DateUtils.parseDate(a2, DateUtils.DATE_FORMAT_10);

        LocalDate localDate = DateUtils.parseLocalDate(a1);
        LocalDate localDate2 = DateUtils.parseLocalDate(a1);

        LocalDateTime localDateTime = DateUtils.parseLocalDateTime(a1);
        LocalDateTime localDateTime2 = DateUtils.parseLocalDateTime(a2);


        String format = DateUtils.format(localDate, DateUtils.DATE_FORMAT_19);
        String format2 = DateUtils.format(localDateTime, DateUtils.DATE_FORMAT_10);


    }


}
