public class Fase2_Concurrente {
    public static void main(String[] args) throws InterruptedException {
        // Objetos NUEVOS, no los de la Fase 1
        Temperatura temperatura = new Temperatura();
        Humedad humedad = new Humedad();
        Luz luz = new Luz();
        ProgramadorRiego riego = new ProgramadorRiego();

        // Los sensores son Runnable
        Thread hiloTemp = new Thread(temperatura, "sensor-Temperatura");
        Thread hiloHum = new Thread(humedad, "sensor-Humedad");
        Thread hiloLuz = new Thread(luz, "sensor-Luz");

        // El riego ya es un Thread
        long inicio = System.currentTimeMillis();

        // Arrancamos los cuatro hilos
        hiloTemp.start();
        hiloHum.start();
        hiloLuz.start();
        riego.start();

        // Terminan los hilos
        hiloTemp.join();
        hiloHum.join();
        hiloLuz.join();
        riego.join();
        long total = System.currentTimeMillis() - inicio;

        // Impresión
        System.out.println("\n--- INFORME ---");
        System.out.println(temperatura.informe());
        System.out.println(humedad.informe());
        System.out.println(luz.informe());
        System.out.println(riego.informe());
        System.out.println("Tiempo total concurrente: " + total + " ms");
    }
}
