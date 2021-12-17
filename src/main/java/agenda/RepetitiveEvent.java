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
    public boolean isInDay(LocalDate aDay){
        boolean res = false;
        //on fait une copie de l'objet courant pour ne pas le modifier
        RepetitiveEvent event = new RepetitiveEvent(this.getTitle(), this.getStart(), this.getDuration(), frequency);
        while(event.getStart().toLocalDate().isBefore(aDay)){
            
            long dureeMinutes = event.getDuration().toMinutes();
            LocalDateTime myEnd = event.getStart().plus(dureeMinutes, ChronoUnit.MINUTES);

            LocalDate jourFin = myEnd.toLocalDate();
            LocalDate jourDebut = event.getStart().toLocalDate();

            if (aDay.isBefore(jourFin) && aDay.isAfter(jourDebut)||aDay.equals(jourDebut) || aDay.equals(jourFin)){
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

  

    /**
     *
     * @return the type of repetition
     */
    public ChronoUnit getFrequency() {
        return frequency;   
    }


}
