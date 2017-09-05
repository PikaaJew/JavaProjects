package test;

import com.company.Station;
import com.company.Train;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class AddNewPassingStationTest {
    @Test
    public void addNewPassingStation() {
        Map<String, Train> trains = new HashMap<>();
        Map<String, Timestamp> shedule = new HashMap<>();
        Station station = new Station(trains, shedule);
        station.AddTrain("IRE1", new Timestamp(System.currentTimeMillis()), "Priozersk");
        station.AddPassStation("IRE1", "Lushasno");
        station.AddPassStation("IRE1", "Vitebsk");
    }
}
