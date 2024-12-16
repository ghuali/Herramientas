package correccionpruebapractica;

import java.util.Random;


public class Agente implements Runnable {
    private final Herramientas herramientas;
    private final String nombreAgente;
    private final String tipoActividad;
    private final boolean requiereDestornillador;
    private final boolean requiereTaladro;
    private final boolean requiereAlicates;
    private final Random random = new Random();
    private final int tiempoTrabajo = 50 + random.nextInt(151);
    private final int tiempoDescanso = 100 + random.nextInt(101);

    public Agente(Herramientas herramientas, String nombreAgente, String tipoActividad, boolean requiereDestornillador, boolean requiereTaladro, boolean requiereAlicates) {
        this.herramientas = herramientas;
        this.nombreAgente = nombreAgente;
        this.tipoActividad = tipoActividad;
        this.requiereDestornillador = requiereDestornillador;
        this.requiereTaladro = requiereTaladro;
        this.requiereAlicates = requiereAlicates;
    }

    public String getTipoActividad() {
        return tipoActividad;
    }

    public String getNombreAgente() {
        return nombreAgente;
    }

    public int getTiempoDescanso() {
        return tiempoDescanso;
    }

    public int getTiempoTrabajo() {
        return tiempoTrabajo;
    }

    public String listarHerramientasNecesarias() {
        String resultado = "";
        if (requiereDestornillador) resultado += "destornillador, ";
        if (requiereTaladro) resultado += "taladro, ";
        if (requiereAlicates) resultado += "alicates ";
        return resultado.trim();
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.printf("El agente %s quiere realizar la actividad %s y solicita %s.\n",
                        this.getNombreAgente(), this.getTipoActividad(), this.listarHerramientasNecesarias());
                
                herramientas.solicitarHerramientas(this, requiereDestornillador, requiereTaladro, requiereAlicates);
                Thread.sleep(tiempoTrabajo);

                herramientas.liberarHerramientas(this, requiereDestornillador, requiereTaladro, requiereAlicates);
                Thread.sleep(tiempoDescanso);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}


