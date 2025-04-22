import org.example.ShapeFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class ShapeTest {

    @Test
    public void ShapeFactoryInitTest(){

        ShapeFactory sf = new ShapeFactory(78);

        Assertions.assertEquals(sf.paint, Color.red);

    }
}
