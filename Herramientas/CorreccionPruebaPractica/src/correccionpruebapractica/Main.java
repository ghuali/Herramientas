package correccionpruebapractica;


public class Main {

    public static void main(String[] args) {
        Herramientas herramientas = new Herramientas();

        Thread agenteA1_1 = new Thread(new Agente(herramientas, "Agente A1_1", "A1", true, true, false));
        Thread agenteA1_2 = new Thread(new Agente(herramientas, "Agente A1_2", "A1", true, true, false));
        Thread agenteA2 = new Thread(new Agente(herramientas, "Agente A2", "A2", true, false, true));
        Thread agenteA3 = new Thread(new Agente(herramientas, "Agente A3", "A3", true, true, true));

        agenteA1_1.start();
        agenteA1_2.start();
        agenteA2.start();
        agenteA3.start();
    }
}
