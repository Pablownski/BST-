import java.util.HashMap;
import java.util.Map;

public class Producto {
    private String sku;
    private String nombre;
    private String descripcion;
    private Map<String, Integer> tallas;

    public Producto(String sku, String nombre, String descripcion, String tallasRaw) {
        this.sku = sku;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tallas = parseTallas(tallasRaw);
    }

    private Map<String, Integer> parseTallas(String tallasRaw) {
        Map<String, Integer> mapa = new HashMap<>();
        String[] pares = tallasRaw.split("\\|");
        for (String par : pares) {
            String[] datos = par.split(":");
            if (datos.length == 2) {
                mapa.put(datos[0], Integer.parseInt(datos[1]));
            }
        }
        return mapa;
    }

    public String getSku() {
        return sku;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Map<String, Integer> getTallas() {
        return tallas;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTallas(Map<String, Integer> nuevasTallas) {
        this.tallas = nuevasTallas;
    }

    @Override
    public String toString() {
        return "SKU: " + sku + "\nNombre: " + nombre + "\nDescripción: " + descripcion + "\nTallas: " + tallas;
    }
}
