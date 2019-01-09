package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {


//    ArrayList<TimeEntry> list = new  ArrayList<TimeEntry>();
    HashMap<Integer,TimeEntry> hlist = new HashMap<Integer, TimeEntry>();
    Integer counter =1;

    public TimeEntry create(TimeEntry te)
    {

//        list.add(te);

        te.setId(counter);
        hlist.putIfAbsent(counter,te);
        System.out.print(hlist);
        counter = counter +1;

        return te;
    }

    public TimeEntry find(long id)
    {
        TimeEntry temp = null;


        if (hlist.isEmpty())
            return temp;
        else if(hlist.get(Math.toIntExact(id))== null)
            return temp;

        else if(hlist.get(Math.toIntExact(id)).getId() == id)
            temp = hlist.get(Math.toIntExact(id));
        return temp;
    }

    public List<TimeEntry> list() {

        List<TimeEntry> list= new ArrayList<>();

        hlist.forEach((k,v)->{
            list.add(v);
        });
        return list;

    }

    public TimeEntry update(long id, TimeEntry timeEntry)
    {

        timeEntry.setId(id);
        hlist.replace(Math.toIntExact(id),timeEntry);
//        list.add((int)id-1,timeEntry);

        return  hlist.get(Math.toIntExact(id));
    }

    public void delete(long id)
    {

       // ArrayList<TimeEntry> templist = new ArrayList<>();
        if(id>0){
            hlist.remove(Math.toIntExact(id));
//            list.remove((int)id-1);
         /*   list.forEach(t->{
                templist.add(t);
            });
*/
          //  list = templist;
        }


    }
}
