import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class BinaryTreeTest {

    @Test
    public void testInsertAndSearch() {
        BNT<String, Producto> arbol = new BNT<>();
        Producto producto = new Producto("001", "Zapatos deportivos", "Zapatos para correr", "m:10|l:5");
        arbol.insert(producto.getSku(), producto);
        Producto resultado = arbol.search("001");

        assertNotNull(resultado);
        assertEquals("001", resultado.getSku());
        assertEquals("Zapatos deportivos", resultado.getNombre());
        assertEquals("Zapatos para correr", resultado.getDescripcion());
        assertTrue(resultado.getTallas().containsKey("m"));
        assertEquals(10, resultado.getTallas().get("m"));
    }
}
