

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Grafo grafo = leerGrafoDesdeArchivo("guategrafo.txt");

        grafo.floydWarshall();

        while (true) {
            System.out.println("Opciones:");
            System.out.println("1. Encontrar la ruta más corta");
            System.out.println("2. Encontrar el centro del grafo");
            System.out.println("3. Modificar el grafo");
            System.out.println("4. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Clear the buffer

            if (opcion == 1) {
                System.out.println("Ingrese la ciudad origen:");
                int origen = scanner.nextInt();
                System.out.println("Ingrese la ciudad destino:");
                int destino = scanner.nextInt();
                grafo.printPath(origen, destino);
            } else if (opcion == 2) {
                int centro = grafo.findCenter();
                System.out.println("El centro del grafo es la ciudad: " + centro);
            } else if (opcion == 3) {
                System.out.println("1. Interrupción de tráfico");
                System.out.println("2. Establecer conexión");

                int subOpcion = scanner.nextInt();
                scanner.nextLine();  // Clear the buffer

                if (subOpcion == 1) {
                    System.out.println("Ingrese la ciudad origen:");
                    int origen = scanner.nextInt();
                    System.out.println("Ingrese la ciudad destino:");
                    int destino = scanner.nextInt();
                    grafo.addEdge(origen, destino, 99999);  // Use a high value to indicate no direct connection
                } else if (subOpcion == 2) {
                    System.out.println("Ingrese la ciudad origen:");
                    int origen = scanner.nextInt();
                    System.out.println("Ingrese la ciudad destino:");
                    int destino = scanner.nextInt();
                    System.out.println("Ingrese la distancia:");
                    int distancia = scanner.nextInt();
                    grafo.addEdge(origen, destino, distancia);
                }

                grafo.floydWarshall();  // Recalcular las rutas más cortas
            } else if (opcion == 4) {
                break;
            }
        }

        scanner.close();
    }

    private static Grafo leerGrafoDesdeArchivo(String archivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            int numVertices = Integer.parseInt(br.readLine().trim());
            Grafo grafo = new Grafo(numVertices);

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(" ");
                int ciudad1 = Integer.parseInt(partes[0]);
                int ciudad2 = Integer.parseInt(partes[1]);
                int distancia = Integer.parseInt(partes[2]);
                grafo.addEdge(ciudad1, ciudad2, distancia);
            }

            return grafo;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
