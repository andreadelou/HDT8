import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

/*

Autor: Andrea Lam, 20102
Hoja de Trabajo 8
Objetivos:
a.Utilización del ADT Priority Queue.
b.Implementación del Priority Queue empleando un heap.

Referencias de todo el programa :
https://docs.oracle.com/javase/7/docs/api/java/util/PriorityQueue.html
https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/PriorityQueue.html
https://www.geeksforgeeks.org/priority-queue-class-in-java-2/
7ma edición del libro Java Structures por Duane A. Bailey.

 */
public class Main {

    public static void main(String[] args) {
        boolean ciclo = true;
        Scanner scan = new Scanner(System.in);

        VectorHeap<Patient> hospitalQueue = new VectorHeap<>();

        do {
            System.out.println("Menu");
            System.out.println("1. Ingresar el archivo de pacientes que se desea procesar ");
            System.out.println("2. Obtener la información de algun paciente");
            System.out.println("3. Salir");

            int op = scan.nextInt();

            switch (op){

                case 1: {
                    //Ingresar el archivo
                    System.out.print("Ingrese el archivo de texto que desea procesar: ");
                    String userFile;
                    userFile = scan.nextLine();

                    //Lectura de archivo de texto.
                    try {
                        Stream<String> lines = Files.lines(
                                Paths.get(userFile),
                                StandardCharsets.UTF_8
                        );
                        lines.forEach(line -> {
                            //separa por comas
                            String[] parts = line.toUpperCase().replace(", ", ",").split(",");

                            if (parts.length == 3){
                                String name = parts[0];
                                String syntom = parts[1];
                                String code = parts[2];

                                Patient newPatient = new Patient(name, syntom, code);
                                hospitalQueue.add(newPatient);
                            }

                        });

                        System.out.println("Se han agregado los pacientes éxito.");
                    } catch (
                            IOException exception) {
                        System.out.println("Error, porfavor verifique los datos ingresados");
                    }
                    break;
                }

                case 2: {
                    //Ver información del paciente

                    if (!hospitalQueue.isEmpty()){
                        System.out.println(hospitalQueue.remove());
                    } else {
                        System.out.println("No se encontraron pacientes en la lista de espera");
                    }

                    break;
                }

                case 3: {
                    //bye bye
                    System.out.println("Pase feliz día :D");
                    ciclo = false;
                    break;
                }
            }

        }while (ciclo);
    }
}
