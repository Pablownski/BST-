import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InventarioManager {

    private final BNT<String, Producto> bstPorSKU;
    private final BNT<String, Producto> bstPorNombre;

    public InventarioManager() {
        bstPorSKU = new BNT<>();
        bstPorNombre = new BNT<>();
    }

    public void cargarDesdeCSV(String nombreArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] partes = linea.split(",", 4);
                if (partes.length == 4) {
                    String sku = partes[0].trim();
                    String nombre = partes[1].trim();
                    String descripcion = partes[2].trim();
                    String tallasRaw = partes[3].trim();
                    Producto producto = new Producto(sku, nombre, descripcion, tallasRaw);
                    insertarProducto(producto);
                }
            }
            System.out.println("Carga de productos completada.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public void insertarProducto(Producto producto) {
        bstPorSKU.insert(producto.getSku(), producto);
        bstPorNombre.insert(producto.getNombre(), producto);
    }

    public Producto buscarPorSKU(String sku) {
        return bstPorSKU.search(sku);
    }

    public Producto buscarPorNombre(String nombre) {
        return bstPorNombre.search(nombre);
    }

    public void mostrarProductosPorSKU() {
        System.out.println("=== Productos ordenados por SKU ===");
        bstPorSKU.inOrderTraversal();
    }

    public void mostrarProductosPorNombre() {
        System.out.println("=== Productos ordenados por Nombre ===");
        bstPorNombre.inOrderTraversal();
    }
}
