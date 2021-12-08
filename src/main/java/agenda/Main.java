package agenda;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author lise
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
        LocalDate d1 = LocalDate.of(2020,11,1);
        System.out.println(d1);

        LocalDate d2 = d1.plus(2, ChronoUnit.DAYS);
        System.out.println(d2);
    }
}

