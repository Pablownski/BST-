import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        InventarioManager inventario = new InventarioManager();
        try (Scanner scanner = new Scanner(System.in)) {
            inventario.cargarDesdeCSV("inventario_ropa_deportiva_100.csv");
            
            boolean salir = false;
            while (!salir) {
                System.out.println("\n=== SISTEMA DE INVENTARIO ===");
                System.out.println("1. Buscar producto por SKU");
                System.out.println("2. Buscar producto por nombre");
                System.out.println("3. Mostrar productos ordenados por SKU");
                System.out.println("4. Mostrar productos ordenados por nombre");
                System.out.println("5. Agregar nuevo producto manualmente");
                System.out.println("6. Salir");
                System.out.print("Seleccione una opción: ");
                
                String opcion = scanner.nextLine();
                
                switch (opcion) {
                    case "1":
                        System.out.print("Ingrese el SKU del producto: ");
                        String sku = scanner.nextLine();
                        Producto p1 = inventario.buscarPorSKU(sku);
                        System.out.println(p1 != null ? p1 : "Producto no encontrado.");
                        break;
                        
                    case "2":
                        System.out.print("Ingrese el nombre del producto: ");
                        String nombre = scanner.nextLine();
                        Producto p2 = inventario.buscarPorNombre(nombre);
                        System.out.println(p2 != null ? p2 : "Producto no encontrado.");
                        break;
                        
                    case "3":
                        inventario.mostrarProductosPorSKU();
                        break;
                        
                    case "4":
                        inventario.mostrarProductosPorNombre();
                        break;
                        
                    case "5":
                        System.out.print("Ingrese el SKU: ");
                        String nuevoSKU = scanner.nextLine();
                        System.out.print("Ingrese el nombre: ");
                        String nuevoNombre = scanner.nextLine();
                        System.out.print("Ingrese la descripción: ");
                        String descripcion = scanner.nextLine();
                        
                        System.out.println("Ingrese tallas en formato talla:cantidad (ej. s:10). Escriba 'fin' para terminar:");
                        Map<String, Integer> tallas = new HashMap<>();
                        while (true) {
                            String entrada = scanner.nextLine();
                            if (entrada.equalsIgnoreCase("fin")) break;
                            String[] partes = entrada.split(":");
                            if (partes.length == 2) {
                                try {
                                    tallas.put(partes[0], Integer.valueOf(partes[1]));
                                } catch (NumberFormatException e) {
                                    System.out.println("Cantidad inválida. Intente de nuevo.");
                                }
                            } else {
                                System.out.println("Formato incorrecto. Intente de nuevo.");
                            }
                        }
                        
                        StringBuilder tallasRaw = new StringBuilder();
                        for (Map.Entry<String, Integer> entry : tallas.entrySet()) {
                            tallasRaw.append(entry.getKey()).append(":").append(entry.getValue()).append("|");
                        }
                        if (!tallas.isEmpty()) {
                            tallasRaw.setLength(tallasRaw.length() - 1);
                        }
                        
                        Producto nuevoProducto = new Producto(nuevoSKU, nuevoNombre, descripcion, tallasRaw.toString());
                        inventario.insertarProducto(nuevoProducto);
                        System.out.println("Producto agregado con éxito.");
                        break;
                        
                    case "6":
                        salir = true;
                        System.out.println("Saliendo del sistema...");
                        break;
                        
                    default:
                        System.out.println("Opción no válida.");
                }
            }
        }
    }
}
