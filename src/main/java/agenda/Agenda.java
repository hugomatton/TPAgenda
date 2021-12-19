package agenda;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * Description : An agenda that stores events
 */
public class Agenda {

    ArrayList<Event> lesEvents = new ArrayList<>();
    /**
     * Adds an event to this agenda
     *
     * @param e the event to add
     */
    public void addEvent(Event e) {
        lesEvents.add(e);
    }

    /**
     * Computes the events that occur on a given day
     *
     * @param day the day toi test
     * @return and iteraror to the events that occur on that day
     */
    public List<Event> eventsInDay(LocalDate day) {
        ArrayList<Event> res = new ArrayList<>();
        for(Event e : lesEvents){
            if(e.isInDay(day)){
                res.add(e);
            }
        }
        return res;
    }

     /**
     * Trouver les événements de l'agenda en fonction de leur titre
     * @param title le titre à rechercher
     * @return les événements qui ont le même titre
     */
    public List<Event> findByTitle(String title) {
        ArrayList<Event> res = new ArrayList<>();
        for(Event e : lesEvents){
            if(e.getTitle().equals(title)){
                res.add(e);
            }
        }
        return res;       
    }
    
    /**
     * Déterminer s’il y a de la place dans l'agenda pour un événement
     * @param e L'événement à tester (on se limitera aux événements simples)
     * @return vrai s’il y a de la place dans l'agenda pour cet événement
     */
    public boolean isFreeFor(Event evenement) {
        boolean res = true;
        LocalDateTime debutParam = evenement.getStart();
        LocalDateTime finParam = evenement.getStart().plus(evenement.getDuration().toMinutes(), ChronoUnit.MINUTES);
        for (Event e : lesEvents){
            //si les deux evenements commencent en même temps ce n'est pas possible
            if(e.getStart().equals(evenement.getStart())){
                res = false;
            }

            //si le debut de l'evenement est compris entre le debut et la fin d'un autre event ce n'est egalement pas possible
            LocalDateTime debutE = evenement.getStart();
            LocalDateTime finE = evenement.getStart().plus(evenement.getDuration().toMinutes(), ChronoUnit.MINUTES);
            if(debutParam.isAfter(debutE)&&debutParam.isBefore(finE)){
                res = false;
            }
        }
        //on regarde si  
        return res;  
    }

   
}
