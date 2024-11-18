import java.io.*;


public abstract class FMRepository<T extends Identifiable> extends IMRepository<T> {
    private final String filename;

    public FMRepository(String filename) {
        super();
        this.filename = filename;
        loadDataFromFile();
    }

    @Override
    public void create(T object) {
        super.create(object);
        saveDataToFile();
    }

    @Override
    public void update(T object) {
        super.update(object);
        saveDataToFile();
    }

    @Override
    public T delete(int id) {
        T removedObject = super.delete(id);
        if (removedObject != null) {
            saveDataToFile();
        }
        return removedObject;
    }

    @Override
    public T read(int id) {
        return super.read(id);
    }

    private void loadDataFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                T object = createObjectFromString(line);
                super.create(object);  
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }

    private void saveDataToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (T object : getAll()) {
                writer.write(convertObjectToString(object));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }


    protected abstract T createObjectFromString(String line);
    protected abstract String convertObjectToString(T object);
}
