import javax.swing.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Updater extends Thread {
    private final Supplier<Integer> valueSupplier;
    private final Consumer<Integer> updateAction;
    private final int updateInterval;
    private boolean alive;

    public Updater(Supplier<Integer> valueSupplier, Consumer<Integer> updateAction, int updateInterval) {
        this.valueSupplier = valueSupplier;
        this.updateAction = updateAction;
        this.updateInterval = updateInterval;
        this.alive = true;
    }

    @Override
    public void run() {
        while (alive) {
            try {
                Thread.sleep(updateInterval);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            SwingUtilities.invokeLater(() -> updateAction.accept(valueSupplier.get()));
        }
    }

    public void stopUpdater() {
        alive = false;
    }
}
