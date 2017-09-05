package test;

import com.company.Station;
import com.company.Train;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class NoFittingTrainTest {
    @Test
    public void noFittingTrainTest() {
        Map<String, Train> trains = new HashMap<>();
        Map<String, Timestamp> shedule = new HashMap<>();
        Station station = new Station(trains, shedule);
        station.AddTrain("IRE1", Timestamp.valueOf("2017-03-27 17:14:01"), "Priozersk");
        station.AddTrain("IRE2", Timestamp.valueOf("2017-03-25 18:20:00"), "Moscow");
        station.AddPassStation("IRE1", "Lushasno");
        station.AddPassStation("IRE1", "Minsk");
        station.AddPassStation("IRE2", "Frankfurt");
        station.AddPassStation("IRE2", "Bryansk");
        Timestamp time = Timestamp.valueOf("2017-03-26 20:00:00");
        station.FindClosestTrain("Bryansk", time);
    }
}