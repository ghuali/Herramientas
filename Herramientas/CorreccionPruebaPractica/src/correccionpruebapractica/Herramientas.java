package correccionpruebapractica;

public class Herramientas {
    private boolean destornilladorDisponible = true;
    private boolean taladroDisponible = true;
    private boolean alicatesDisponibles = true;

    public synchronized void solicitarHerramientas(Agente agente, boolean requiereDestornillador, boolean requiereTaladro, boolean requiereAlicates) throws InterruptedException {
        while ((requiereDestornillador && !destornilladorDisponible) ||
                (requiereTaladro && !taladroDisponible) ||
                (requiereAlicates && !alicatesDisponibles)) {
            System.out.printf("--- El agente %s espera para la actividad %s. Necesita %s.\n",
                    agente.getNombreAgente(), agente.getTipoActividad(), agente.listarHerramientasNecesarias());
            wait();
        }
        if (requiereDestornillador) destornilladorDisponible = false;
        if (requiereTaladro) taladroDisponible = false;
        if (requiereAlicates) alicatesDisponibles = false;

        System.out.printf(">>> El agente %s inicia la actividad %s. Utiliza %s. Trabajará por %d milisegundos.\n",
                agente.getNombreAgente(), agente.getTipoActividad(), agente.listarHerramientasNecesarias(), agente.getTiempoTrabajo());
    }

    public synchronized void liberarHerramientas(Agente agente, boolean requiereDestornillador, boolean requiereTaladro, boolean requiereAlicates) {
        if (requiereDestornillador) destornilladorDisponible = true;
        if (requiereTaladro) taladroDisponible = true;
        if (requiereAlicates) alicatesDisponibles = true;

        System.out.printf("<<< El agente %s finaliza la actividad %s. Devuelve %s. Descansará por %d milisegundos.\n",
                agente.getNombreAgente(), agente.getTipoActividad(), agente.listarHerramientasNecesarias(), agente.getTiempoDescanso());

        notifyAll();
    }
}
