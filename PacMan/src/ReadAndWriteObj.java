import java.io.*;
import java.util.ArrayList;

public class ReadAndWriteObj<T> {
    String url;
    ReadAndWriteObj(String ulr){
        this.url=ulr;
    }

    public boolean isExistFile(){
        File file= new File(url);
        return file.isFile() && file.canRead();
    }

    public ArrayList<T> readIt() {
        if (isExistFile()) {
            try (FileInputStream fileIn = new FileInputStream(url);
                 ObjectInputStream ois = new ObjectInputStream(fileIn)) {
                return (ArrayList<T>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }
    public void writeIt(T typeToFlush) {
        ArrayList<T> players = new ArrayList<>();
        players.add(typeToFlush);
        try (FileOutputStream fileOut = new FileOutputStream(url);
             ObjectOutputStream oos = new ObjectOutputStream(fileOut)) {
            oos.writeObject(players);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeIt(T typeToFlush, ArrayList<T> tempArray) {
        tempArray.add(typeToFlush);
        try (FileOutputStream fileOut = new FileOutputStream(url);
             ObjectOutputStream oos = new ObjectOutputStream(fileOut)) {
            oos.writeObject(tempArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeItEnchanted(T typeToFlush) {
        if (isExistFile()) {
            ArrayList<T> tempArray = readIt();
            writeIt(typeToFlush, tempArray);
        } else {
            writeIt(typeToFlush);
        }
    }
}
