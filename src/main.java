package src;

import java.util.Scanner;

public class main {

    static Scanner sc = new Scanner(System.in);
    static int id = 0;
    static Pizzeria pedidos = new Pizzeria();

    public static void main(String[] args) {
        menu();
    }

    static void menu() {
        int opc = 0;
        do {
            System.out.println("\nBIENVENIDO A LA PIZZERIA PEPE:");
            System.out.println("1. Gestionar pedidos");
            System.out.println("2. Gestionar orden de pedidos");
            System.out.println("3. Capturar tiempos de ejecución");
            System.out.println("4. Salir\n");
            if (sc.hasNextInt()) {
                opc = sc.nextInt();
                sc.nextLine();
            } else {
                System.out.println("Ingrese un número válido.");
                sc.nextLine();
                continue;
            }

            switch (opc) {
                case 1 -> {
                    int opc2 = 0;
                    do {
                        System.out.println("\nGESTION DE PEDIDOS:");
                        System.out.println("1. Agregar pedido");
                        System.out.println("2. Eliminar pedido");
                        System.out.println("3. Modificar pedido");
                        System.out.println("4. Mostrar pedidos");
                        System.out.println("5. Volver al menú principal");
                        if (sc.hasNextInt()) {
                            opc2 = sc.nextInt();
                            sc.nextLine();
                        } else {
                            System.out.println("Ingrese un número válido.");
                            sc.nextLine();
                            continue;
                        }

                        switch (opc2) {
                            case 1 -> {
                                agregarPedido();
                            }
                            case 2 -> {
                                pedidos.mostrarPedidos();
                                eliminarPedido();
                            }
                            case 3 -> {
                                pedidos.mostrarPedidos();
                                System.out.println("Ingresar id del pedido a modificar");
                                int index = sc.nextInt();
                                modificarPedido(index);
                            }
                            case 4 -> {
                                pedidos.mostrarPedidos();
                            }
                            case 5 -> {
                                System.out.println("\nVolviendo al menú principal...");
                                break;
                            }
                            default ->
                                System.out.println("\nOpción inválida. Por favor, ingrese un número entre 1 y 4.");
                        }
                    } while (opc2 != 4);
                }

                case 2 -> {

                    System.out.println("Gestionando orden de pedidos...");
                }

                case 3 -> {

                    System.out.println("Capturando tiempos de ejecución...");
                }

                case 4 -> {
                    System.out.println("\nSaliendo...");
                    break;
                }

                default ->
                    System.out.println("\nOpción inválida. Por favor, ingrese un número entre 1 y 4.");
            }
        } while (opc != 4);
    }

    public static void agregarPedido() {

        String nombre;
        String desc;
        int total;

        do {
            System.out.print("\nIngrese su nombre: ");
            nombre = sc.nextLine().trim();
            if (nombre.isEmpty()) {
                System.out.println("El nombre no puede estar vacío.");
            }
        } while (nombre.isEmpty());

        do {
            System.out.print("Ingrese la descripción del pedido: ");
            desc = sc.nextLine().trim();
            if (desc.isEmpty()) {
                System.out.println("La descripción no puede estar vacía.");
            }
        } while (desc.isEmpty());

        id++;
        total = (int) (Math.random() * (10000 - 1000 + 1)) + 1000;

        Pedido nuevoPedido = new Pedido(nombre, desc, id, total);
        pedidos.agregarPedido(nuevoPedido);
        System.out.println("Pedido agregado correctamente.");
    }

    public static void eliminarPedido() {
        System.out.print("\nIngrese el ID del pedido que desea eliminar: ");
        int idEliminar = sc.nextInt();
        sc.nextLine();

        boolean eliminado = pedidos.eliminarPedido(idEliminar);
        if (eliminado) {
            System.out.println("Pedido eliminado correctamente.");
        } else {
            System.out.println("No se encontró un pedido con ese ID.");
        }
    }

    public static void modificarPedido(int index) {
        System.out.println("\n¿Qué desea modificar?\n1.Nombre\n2.Descripción");
        int opcion = sc.nextInt();
        try {
            String cadena = sc.nextLine();
            sc.next();
            switch (opcion) {
                case 1 -> {
                    System.out.println("Ingresar nuevo nombre: ");
                    pedidos.modificarNombre(index - 1, cadena);
                }
                case 2 -> {
                    System.out.println("Ingresar nueva descripción: ");
                    pedidos.modificarDescripcion(index - 1, cadena);
                }
                default ->
                    System.out.println("\nOpción incorrecta");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
