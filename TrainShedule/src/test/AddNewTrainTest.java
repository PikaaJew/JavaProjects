package test;

import com.company.Station;
import com.company.Train;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class AddNewTrainTest {
    @Test
    public void addNewTrain() {
        Map<String, Train> trains = new HashMap<>();
        Map<String, Timestamp> shedule = new HashMap<>();
        Station station = new Station(trains, shedule);
        station.AddTrain("IRE1", new Timestamp(System.currentTimeMillis()), "Priozersk");
        station.AddTrain("IRE2", new Timestamp(System.currentTimeMillis()), "Minsk");
        System.out.println(station.toString());
    }
}
