package toolBox;

import javax.swing.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Updater<T> extends Thread {
    private Supplier<T> valueSupplier;
    private Consumer<T> updateAction;
    private int updateInterval;
    private boolean alive;

    public Updater(Supplier<T> valueSupplier, Consumer<T> updateAction, int updateInterval) {
        this.valueSupplier = valueSupplier;
        this.updateAction = updateAction;
        this.updateInterval = updateInterval;
        this.alive = true;
    }

    @Override
    public void run() {
       //!// System.out.println(Thread.currentThread()+" "+getClass().getName());

        while (alive) {
            try {
                Thread.sleep(updateInterval);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            SwingUtilities.invokeLater(() -> updateAction.accept(valueSupplier.get()));
        }
    }

    public void stopIt() {
        alive = false;
    }
}
