package snapshot;

import java.util.List;

public class CareTaker {
    private List<Snapshot> snapshots;

    public void addSnapshot(Snapshot snapshot){
        snapshots.add(snapshot);
    }

    public Snapshot getSnapshot(int index){
        return snapshots.get(index);
    }
}
