package agenda;

import java.util.*;
import java.time.*;
import java.time.temporal.ChronoUnit;

/**
 * Description : A repetitive Event
 */
public class RepetitiveEvent extends Event {

    protected ChronoUnit frequency;
    protected ArrayList<LocalDate> datesException = new ArrayList<>();
    /**
     * Constructs a repetitive event
     *
     * @param title the title of this event
     * @param start the start of this event
     * @param duration myDuration in seconds
     * @param frequency one of :
     * <UL>
     * <LI>ChronoUnit.DAYS for daily repetitions</LI>
     * <LI>ChronoUnit.WEEKS for weekly repetitions</LI>
     * <LI>ChronoUnit.MONTHS for monthly repetitions</LI>
     * </UL>
     */
    public RepetitiveEvent(String title, LocalDateTime start, Duration duration, ChronoUnit frequency) {
        super(title, start, duration);
        this.frequency = frequency;
    }

    /**
     * Adds an exception to the occurrence of this repetitive event
     *
     * @param date the event will not occur at this date
     */
    public void addException(LocalDate date) {
        datesException.add(date);
    }

    
    public ArrayList<LocalDate> getDatesException() {
        return datesException;
    }

    @Override
    /*
    public boolean isInDay2(LocalDate aDay){
        boolean res = false;


        //on fait une copie de l'objet courant pour ne pas le modifier
        RepetitiveEvent event = new RepetitiveEvent(this.getTitle(), this.getStart(), this.getDuration(), frequency);
        while(event.getStart().toLocalDate().isBefore(aDay)){
            
            long dureeMinutes = event.getDuration().toMinutes();
            LocalDateTime myEnd = event.getStart().plus(dureeMinutes, ChronoUnit.MINUTES);

            LocalDate jourFin = myEnd.toLocalDate();
            LocalDate jourDebut = event.getStart().toLocalDate();

            if (aDay.isBefore(jourFin) && aDay.isAfter(jourDebut)|| aDay.equals(jourDebut) || aDay.equals(jourFin)){
                res = true;
            }
            //on incremente le debut de l'evenement par la frequence
            event.setStart(event.getStart().plus(1,frequency));
        }
        
        //on regarde que le jour en parametre n'est pas un jour exception
        if(datesException.contains(aDay)){
            res = false;
        }
        return res;
    }
    */

    public boolean isInDay(LocalDate aDay){

        LocalDate myStart = getStart().toLocalDate();
        LocalDate dateSup = myStart;
        System.out.println(dateSup);

        //on vérifie que le jour passé en paramètre est après le jour de début de l'event
        if (aDay.isAfter(dateSup) || aDay.equals(myStart)){
            //pour chaque jour où se produit l'event (1 fois/semaine), on vérifie si ce jour là correspond au jour passé en paramètre
            while (aDay.isAfter(dateSup) || aDay.equals(dateSup)){
                System.out.println("boucle");
                
                if (dateSup.equals(aDay) && (!datesException.contains(aDay))){
                    return true;
                }
                System.out.println("cc hugo");
                dateSup=dateSup.plus(1, frequency);
                System.out.println(dateSup);
                
            }
            System.out.println("sortie boucle");
        }
        return false;
    }

    
    public static void main(String[] args){
        // November 1st, 2020
    LocalDate nov_1_2020 = LocalDate.of(2020, 11, 1);

    // November 1st, 2020, 22:30
    LocalDateTime nov_1__2020_22_30 = LocalDateTime.of(2020, 11, 1, 22, 30);

    // 120 minutes
    Duration min_120 = Duration.ofMinutes(120);

    // A daily repetitive event, never ending
    // November 1st, 2020, 22:30, 120 minutes
    RepetitiveEvent neverEnding = new RepetitiveEvent("Never Ending", nov_1__2020_22_30, min_120, ChronoUnit.DAYS);

    neverEnding.isInDay(nov_1_2020.plus(10, ChronoUnit.DAYS));
    }
    
    
    /*
    public static void main(String[] args) {
        LocalDate nov_1_2020 = LocalDate.of(2020, 11, 1);

        // November 1st, 2020, 22:30
        LocalDateTime nov_1__2020_22_30 = LocalDateTime.of(2020, 11, 1, 22, 30);
    
        // 120 minutes
        Duration min_120 = Duration.ofMinutes(120);
    
        // A daily repetitive event, never ending
        // November 1st, 2020, 22:30, 120 minutes
        RepetitiveEvent neverEnding = new RepetitiveEvent("Never Ending", nov_1__2020_22_30, min_120, ChronoUnit.DAYS);

        LocalDate myStart = neverEnding.getStart().toLocalDate();
        LocalDate dateSup = myStart.plus(0, neverEnding.getFrequency());

        System.out.println(dateSup);
        System.out.println(dateSup.plus(10, neverEnding.getFrequency()));
    }
  */

    /**
     *
     * @return the type of repetition
     */
    public ChronoUnit getFrequency() {
        return frequency;   
    }


}
