import java.io.*;
import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks;
    private String filePath;

    public TaskManager(String filePath) {
        this.filePath = filePath;
        this.tasks = new ArrayList<>();
        loadTasks();
    }

    public void addTask(String description) {
        tasks.add(new Task(description));
        System.out.println("✅ Tarea agregada.");
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("📭 No hay tareas pendientes.");
            return;
        }
        System.out.println("\n--- Lista de tareas ---");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public void completeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).setCompleted(true);
            System.out.println("✅ Tarea marcada como completada.");
        } else {
            System.out.println("❌ Número de tarea inválido.");
        }
    }

    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            System.out.println("🗑️ Tarea eliminada.");
        } else {
            System.out.println("❌ Número de tarea inválido.");
        }
    }

    public void saveTasks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.write(task.getDescription() + ";" + task.isCompleted());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("❌ Error al guardar tareas.");
        }
    }

    private void loadTasks() {
        File file = new File(filePath);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                Task task = new Task(parts[0]);
                task.setCompleted(Boolean.parseBoolean(parts[1]));
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("❌ Error al cargar tareas.");
        }
    }
}
