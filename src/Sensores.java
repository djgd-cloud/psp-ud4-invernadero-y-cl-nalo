import java.time.LocalTime; //Herramienta para tener la hora actual

public abstract class Sensor implements Runnable {
    //Configuración
    private String nombre;
    private String unidad;
    private int periodo;
    private int numLecturas;
    private double minimoOk;
    private double maximoOk;
    //Estadísticas
    private int lecturas = 0;
    private double suma = 0;
    private double minimo = 9999;
    private double maximo = -9999;
    private int alertas = 0;

    //Constructor
    public Sensor(String nombre, String unidad, int periodo,
                  int numLecturas, double minimoOk, double maximoOk) {
        this.nombre = nombre;
        this.unidad = unidad;
        this.periodo = periodo;
        this.numLecturas = numLecturas;
        this.minimoOk = minimoOk;
        this.maximoOk = maximoOk;
    }

    //Cada sensor dice como genera su valor
    public abstract double medir();

    //Se ejecuta cuando el hilo arranca con start()
    @Override
    public void run() {
        //Repite cada lectura
        for (int i = 0; i < numLecturas; i++) {
            double valor = medir();
            lecturas++;
            suma = suma + valor;

            //Actualiza las estadísticas con el valor nuevo
            if (valor < minimo) {
                minimo = valor;
            }
            if (valor > maximo) {
                maximo = valor;
            }

            // Comprueba si el valor está fuera del rango
            String aviso = "";
            if (valor < minimoOk || valor > maximoOk) {
                alertas++;
                aviso = " ALERTA";
            }
            // Imprime la línea del log
            System.out.println(LocalTime.now() + " [" + Thread.currentThread().getName() + "] "
                    + nombre + " = " + String.format("%.1f", valor) + " " + unidad + aviso);

            // Espera antes de la siguiente lectura.
            try {
                Thread.sleep(periodo);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    //Devuelve un resumen en línea de texto
    public String informe() {
        if (lecturas == 0) {
            return nombre + ": todavía sin lecturas";
        }
        double media = suma / lecturas;
        return nombre + ": media " + String.format("%.1f", media) + " " + unidad
                + ", min " + String.format("%.1f", minimo)
                + ", max " + String.format("%.1f", maximo)
                + ", alertas " + alertas;
    }
}
