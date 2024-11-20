package Repository;

import Domain.Identifiable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class FMRepository<T extends Identifiable> extends IMRepository<T> {
    private final String filename;
    private int currentId;

    public FMRepository(String filename) {
        super();
        this.filename = filename;
        loadDataFromFile();
    }

    @Override
    public void create(T object) {

        if (exists(object)) {
            throw new IllegalArgumentException("Duplicate object detected");
        }

        object.setId(currentId);
        saveObjectToFile(object);
        currentId++;
    }

    private boolean exists(T object) {

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                T existingObject = createObjectFromString(line);
                if (existingObject.equals(object)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return false;
    }

    @Override
    public void update(T object) {
        List<T> allObjects = readAllObjects();
        boolean updated = false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (T existingObject : allObjects) {
                if (existingObject.getId() == object.getId()) {
                    writer.write(convertObjectToString(object));
                    writer.newLine();
                    updated = true;
                } else {
                    writer.write(convertObjectToString(existingObject));
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

        if (!updated) {
            throw new IllegalArgumentException("Object with ID " + object.getId() + " not found");
        }
    }

    @Override
    public T delete(int id) {
        List<T> allObjects = readAllObjects();
        T removedObject = null;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (T existingObject : allObjects) {
                if (existingObject.getId() == id) {
                    removedObject = existingObject;
                } else {
                    writer.write(convertObjectToString(existingObject));
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

        if (removedObject == null) {
            throw new IllegalArgumentException("Object with ID " + id + " not found");
        }

        return removedObject;
    }

    @Override
    public T read(int id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                T existingObject = createObjectFromString(line);
                if (existingObject.getId() == id) {
                    return existingObject;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return null;
    }

    private void loadDataFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                T object = createObjectFromString(line);
                if (object.getId() >= currentId) {
                    currentId = object.getId() + 1;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }

    private void saveObjectToFile(T object) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(convertObjectToString(object));
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    private List<T> readAllObjects() {
        List<T> objects = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                T object = createObjectFromString(line);
                objects.add(object);
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return objects;
    }

    protected abstract String convertObjectToString(T object);

    protected abstract T createObjectFromString(String line);
}
