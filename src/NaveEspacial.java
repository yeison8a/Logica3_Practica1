import java.util.ArrayList;

public class NaveEspacial {
    private String planetaOrigen;
    private String planetaDestino;
    private Familia[][] cabinas;

    public NaveEspacial(String planetaOrigen, String planetaDestino, int numCabinas) {
        this.planetaOrigen = planetaOrigen;
        this.planetaDestino = planetaDestino;
        this.cabinas = new Familia[numCabinas][5];
    }

    public Familia[][] getCabinas() {
        return cabinas;
    }    

    public void imprimirEstadoInicial() {
        System.out.println("Estado inicial de la nave:");
        for (int i = 0; i < cabinas.length; i++) {
            for (int j = 0; j < cabinas[0].length; j++) {
                System.out.println("Cabina " + i + "-" + j + ":");
                Familia familia = cabinas[i][j];
                if (familia == null) {
                    System.out.println("Vacía");
                } else {
                    for (Persona persona : familia.getPersonas()) {
                        System.out.println("- " + persona.getNombre() + ", " + persona.getGenero() + ", " + persona.getEdad() + " años");
                    }
                }
            }
        }
        System.out.println("-----------------------------");
    }

    public void imprimirEstadoFinal() {
        System.out.println("Estado final de la nave:");
        for (int i = 0; i < cabinas.length; i++) {
            for (int j = 0; j < cabinas[0].length; j++) {
                System.out.println("Cabina " + i + "-" + j + ":");
                Familia familia = cabinas[i][j];
                if (familia == null) {
                    System.out.println("Vacía");
                } else {
                    for (Persona persona : familia.getPersonas()) {
                        System.out.println("- " + persona.getNombre() + ", " + persona.getGenero() + ", " + persona.getEdad() + " años");
                    }
                }
            }
        }
        System.out.println("-----------------------------");
    }

    public void agregarFamilia(int cabinaFila, int cabinaColumna, Familia familia) {
        this.cabinas[cabinaFila][cabinaColumna] = familia;
    }

    public void eliminarFamilia(int cabinaFila, int cabinaColumna) {
        this.cabinas[cabinaFila][cabinaColumna] = null;
    }

    public ArrayList<Persona> getTodasLasPersonas() {
        ArrayList<Persona> todasLasPersonas = new ArrayList<>();
        for (int i = 0; i < cabinas.length; i++) {
            for (int j = 0; j < cabinas[0].length; j++) {
                Familia familia = cabinas[i][j];
                if (familia != null) {
                    for (Persona persona : familia.getPersonas()) {
                        todasLasPersonas.add(persona);
                    }
                }
            }
        }
        return todasLasPersonas;
    }
}
