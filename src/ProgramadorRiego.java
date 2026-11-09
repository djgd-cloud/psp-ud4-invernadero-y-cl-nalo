import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ProgramadorRiego extends Thread {
    private static final int CICLOS_POR_DEFECTO = 5;
    private static final int ESPERA_MS = 700;
    private static final double LITROS_POR_APERTURA = 2.5;
    private final int ciclosTotales;
    private int ciclos = 0;
    private double litros = 0;
    // Constructor sin parámetros
    public ProgramadorRiego() {
        this(CICLOS_POR_DEFECTO);
    }
    // Constructor con número de ciclos
    public ProgramadorRiego(int ciclosTotales) {
        super("riego"); // nombre del hilo
        this.ciclosTotales = ciclosTotales;
    }

    @Override
    //Abre y cierra la válvula ciclosTotales veces
    public void run() {
        for (int i = 1; i <= ciclosTotales; i++) {
            System.out.printf("%s [%s] Ciclo %d: VALVULA ABIERTA%n", hora(), getName(), i);
            ciclos++;
            litros += LITROS_POR_APERTURA;
            esperar();
            System.out.printf("%s [%s] Ciclo %d: VALVULA CERRADA%n", hora(), getName(), i);
            esperar();
        }
    }
    //Método para no repetir try y catch
    private void esperar() {
        try {
            Thread.sleep(ESPERA_MS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    private String hora() {
        return LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS"));
    }
    //Devuelve resumen de ciclos y litros
    public String informe() {
        return String.format("Riego: %d ciclos, %.1f litros en total", ciclos, litros);
    }
}