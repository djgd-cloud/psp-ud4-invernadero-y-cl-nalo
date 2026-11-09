public class Fase3_Experimentos {
    public static void main(String[] args) throws InterruptedException {
        //Experimento A: Lanzamiento de sensor
        System.out.println("EXPERIMENTO A");
        Temperatura temperatura = new Temperatura();
        Thread hiloTemp = new Thread(temperatura, "sensor-Temperatura");
        hiloTemp.start();
        System.out.println("Sin esperar: " + temperatura.informe());
        hiloTemp.join();
        System.out.println("Tras join: " + temperatura.informe());

        //Experimento B: Creación de riego
        System.out.println("XPERIMENTO B");
        ProgramadorRiego riegoB = new ProgramadorRiego(3);
        long inicio = System.currentTimeMillis();
        riegoB.run();
        long total = System.currentTimeMillis() - inicio;
        System.out.println("Tiempo: " + total + " ms");
        System.out.println("Estado del hilo: " + riegoB.getState());

        // Experimento C: Arrancar un riego
        System.out.println("EXPERIMENTO C");
        ProgramadorRiego riegoC = new ProgramadorRiego(3);
        riegoC.start();
        riegoC.join();
        try {
            riegoC.start(); // segunda vez: error
        } catch (IllegalThreadStateException e) {
            System.out.println("Excepción capturada: " + e);
        }
    }
}
