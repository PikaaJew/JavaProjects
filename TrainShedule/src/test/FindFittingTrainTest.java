package test;

import com.company.Station;
import com.company.Train;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class FindFittingTrainTest {
    @Test
    public void findFittingTrainTest() {
        Map<String, Train> trains = new HashMap<>();
        Map<String, Timestamp> shedule = new HashMap<>();
        Station station = new Station(trains, shedule);
        station.AddTrain("IRE1", Timestamp.valueOf("2017-03-25 17:14:01"), "Priozersk");
        station.AddTrain("IRE2", Timestamp.valueOf("2017-03-25 18:20:00"), "Moscow");
        station.AddTrain("IRE3", Timestamp.valueOf("2017-03-25 14:51:02"), "Ulm");
        station.AddPassStation("IRE1", "Lushasno");
        station.AddPassStation("IRE1", "Bryansk");
        station.AddPassStation("IRE2", "Frankfurt");
        station.AddPassStation("IRE2", "Bryansk");
        station.AddPassStation("IRE3", "Bryansk");
        Timestamp time = Timestamp.valueOf("2017-03-25 17:00:00");
        station.FindClosestTrain("Bryansk", time);
    }
}