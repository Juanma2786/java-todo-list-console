import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager("tasks.txt");
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n===== TO-DO LIST =====");
            System.out.println("1. Agregar tarea");
            System.out.println("2. Listar tareas");
            System.out.println("3. Marcar tarea como completada");
            System.out.println("4. Eliminar tarea");
            System.out.println("5. Guardar y salir");
            System.out.print("Opción: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Ingrese la descripción de la tarea: ");
                    String desc = scanner.nextLine();
                    manager.addTask(desc);
                    break;
                case 2:
                    manager.listTasks();
                    break;
                case 3:
                    System.out.print("Ingrese el número de tarea a completar: ");
                    int completeIndex = scanner.nextInt();
                    manager.completeTask(completeIndex - 1);
                    break;
                case 4:
                    System.out.print("Ingrese el número de tarea a eliminar: ");
                    int deleteIndex = scanner.nextInt();
                    manager.removeTask(deleteIndex - 1);
                    break;
                case 5:
                    manager.saveTasks();
                    System.out.println("✅ Tareas guardadas. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("❌ Opción inválida.");
            }
        } while (option != 5);

        scanner.close();
    }
}
