import java.util.concurrent.ThreadLocalRandom;
public class Temperatura extends Sensor {
    public Temperatura() {
        // nombre unidad periodo lecturas umbral min umbral max
        super("Temperatura", "ºC", 400, 5, 18.0, 30.0);
    }

    @Override
    public double medir() {
        // Genera un valor entre 15 y 35 (puede salir fuera del rango correcto)
        return ThreadLocalRandom.current().nextDouble(15.0, 35.0);
    }
}