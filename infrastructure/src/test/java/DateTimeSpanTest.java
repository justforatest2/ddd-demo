import domain.vo.DateTimeSpan;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

public class DateTimeSpanTest {
    @Test
    public void containsTime() {

        // 当前是21号15:00--23号13:00，而传入的参数是22号09:00--22号19:00，那么就是包含
        DateTimeSpan leaveDateTimeSpan = new DateTimeSpan(
            LocalDateTime.of(LocalDate.of(2018, 1, 21), LocalTime.of(15, 0)),
            LocalDateTime.of(LocalDate.of(2018, 1, 23), LocalTime.of(13, 0))
        );

        DateTimeSpan otherDateTimeSpan = new DateTimeSpan(
            LocalDateTime.of(LocalDate.of(2018, 1, 22), LocalTime.of(9, 0)),
            LocalDateTime.of(LocalDate.of(2018, 1, 22), LocalTime.of(19, 0))
        );

        Assert.assertTrue(leaveDateTimeSpan.contains(leaveDateTimeSpan));
        Assert.assertTrue(leaveDateTimeSpan.contains(otherDateTimeSpan));

        otherDateTimeSpan = new DateTimeSpan(
                otherDateTimeSpan.getStartDateTime(),
                otherDateTimeSpan.getEndDateTime().withDayOfMonth(23)
        );
        Assert.assertFalse(leaveDateTimeSpan.contains(otherDateTimeSpan));

        otherDateTimeSpan = new DateTimeSpan(
                otherDateTimeSpan.getStartDateTime(),
                otherDateTimeSpan.getEndDateTime().withMonth(2)
        );
        Assert.assertFalse(leaveDateTimeSpan.contains(otherDateTimeSpan));
    }

    @Test
    public void diff() {
        // 当前是21号15:00--23号13:00，而传入的参数是22号09:00--22号19:00，那么就是包含
        DateTimeSpan leaveDateTimeSpan = new DateTimeSpan(
                LocalDateTime.of(LocalDate.of(2018, 1, 21), LocalTime.of(15, 0)),
                LocalDateTime.of(LocalDate.of(2018, 1, 23), LocalTime.of(13, 0))
        );

        DateTimeSpan otherDateTimeSpan = new DateTimeSpan(
                LocalDateTime.of(LocalDate.of(2018, 1, 22), LocalTime.of(9, 0)),
                LocalDateTime.of(LocalDate.of(2018, 1, 22), LocalTime.of(19, 0))
        );

        DateTimeSpan diffDateTimeSpan1 = new DateTimeSpan(
                LocalDateTime.of(LocalDate.of(2018, 1, 21), LocalTime.of(15, 0)),
                LocalDateTime.of(LocalDate.of(2018, 1, 22), LocalTime.of(9, 0))
        );

        DateTimeSpan diffDateTimeSpan2 = new DateTimeSpan(
                LocalDateTime.of(LocalDate.of(2018, 1, 22), LocalTime.of(19, 0)),
                LocalDateTime.of(LocalDate.of(2018, 1, 23), LocalTime.of(13, 0))
        );

        Assert.assertEquals(Arrays.asList(diffDateTimeSpan1, diffDateTimeSpan2), leaveDateTimeSpan.diff(otherDateTimeSpan));

        Assert.assertEquals(0, leaveDateTimeSpan.diff(leaveDateTimeSpan).size());
    }
}
