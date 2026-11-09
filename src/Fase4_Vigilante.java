public class Fase4_Vigilante {

    public static void main(String[] args) throws InterruptedException {
        //Creación de 4 hilos
        Thread temp = new Thread(new Temperatura(), "sensor-Temperatura");
        Thread hum = new Thread(new Humedad(), "sensor-Humedad");
        Thread luz = new Thread(new Luz(), "sensor-Luz");
        Thread riego = new ProgramadorRiego(5);

        Thread vigilante = new Thread(() -> {
            while (true) {
                mostrar(temp, hum, luz, riego);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
        vigilante.setDaemon(true);

        System.out.println("Antes de arrancar:");
        mostrar(temp, hum, luz, riego);

        temp.start();
        hum.start();
        luz.start();
        riego.start();
        vigilante.start();

        temp.join();
        hum.join();
        luz.join();
        riego.join();

        System.out.println("Después de los join:");
        mostrar(temp, hum, luz, riego);
    }

    static void mostrar(Thread a, Thread b, Thread c, Thread d) {
        System.out.println(a.getName() + "=" + a.getState() + "  "
                + b.getName() + "=" + b.getState() + "  "
                + c.getName() + "=" + c.getState() + "  "
                + d.getName() + "=" + d.getState());
    }
}