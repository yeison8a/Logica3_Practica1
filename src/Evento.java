import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

public class Evento {
    private NaveEspacial nave;
    private Random rand;

    public Evento(NaveEspacial nave) {
        this.nave = nave;
        this.rand = new Random();
    }

    //falta
    public void colisionAsteroide() { 
        System.out.println("¡La nave ha colisionado con un asteroide!");

        // Calcular la probabilidad por cada cabina
        double probabilidadPorCabina = 1.0 / (nave.getCabinas().length * nave.getCabinas()[0].length);

        // Recorrer todas las cabinas de la nave
        for (int i = 0; i < nave.getCabinas().length; i++) {
            for (int j = 0; j < nave.getCabinas()[0].length; j++) {
                // Determinar si ocurre la colisión en esta cabina
                if (rand.nextDouble() < probabilidadPorCabina) {
                    // Eliminar la familia de la cabina
                    nave.eliminarFamilia(i, j);
                    System.out.println("Familia eliminada de la cabina " + i + "-" + j);
                }
            }
        }
    }

    public void enloquecimiento() {
        for (int i = 0; i < nave.getCabinas().length; i++) {
            for (int j = 0; j < nave.getCabinas()[0].length; j++) {
                Familia familia = nave.getCabinas()[i][j];
                if (familia != null) {
                    double probabilidad = Math.random();
                    if (probabilidad <= 0.05) {
                        nave.eliminarFamilia(i, j);
                        System.out.println("La familia en la cabina " + i + "-" + j + " se ha enloquecido y ha sido eliminada.");
                    }
                }
            }
        }
    }
    
    public void eventoFugaAire(NaveEspacial nave) {
        // Obtener todas las personas de la nave
        ArrayList<Persona> todasLasPersonas = nave.getTodasLasPersonas();

        // Ordenar las personas por edad (de mayor a menor)
        Collections.sort(todasLasPersonas, (p1, p2) -> p2.getEdad() - p1.getEdad());

        // Expulsar las 10 personas más viejas
        for (int i = 0; i < 10 && i < todasLasPersonas.size(); i++) {
            Persona personaExpulsada = todasLasPersonas.get(i);

            // Buscar la familia de la persona expulsada y eliminarla de la cabina correspondiente
            Familia familia = null;
            int cabinaFila = -1;
            int cabinaColumna = -1;
            for (int j = 0; j < nave.getCabinas().length; j++) {
                for (int k = 0; k < nave.getCabinas()[0].length; k++) {
                    Familia f = nave.getCabinas()[j][k];
                    if (f != null && f.contienePersona(personaExpulsada)) {
                        familia = f;
                        cabinaFila = j;
                        cabinaColumna = k;
                        break;
                    }
                }
                if (familia != null) {
                    break;
                }
            }
            if (familia != null) {
                familia.eliminarPersona(personaExpulsada);
                if (familia.getNumeroPersonas() == 0) {
                    nave.eliminarFamilia(cabinaFila, cabinaColumna);
                }
            }
        }
    }

    /*//falta
    public static void tormentaSolar(NaveEspacial nave) {
        Random random = new Random();
        int num = random.nextInt(2); // Genera un número aleatorio entre 0 y 1
    
        if (num == 0) {
            // Elimina las familias de una fila en el extremo superior
            for (int i = 0; i < nave.getFilas(); i++) {
                Familia familia = nave.getCabina(i, 0).getFamilia();
                if (familia != null) {
                    nave.getCabina(i, 0).setFamilia(null);
                    System.out.println("La familia en la fila " + i + " y columna 0 ha sido eliminada por la tormenta solar.");
                }
            }
        } else {
            // Elimina las familias de una columna en el extremo derecho
            for (int j = 0; j < nave.getColumnas(); j++) {
                Familia familia = nave.getCabina(0, j).getFamilia();
                if (familia != null) {
                    nave.getCabina(0, j).setFamilia(null);
                    System.out.println("La familia en la fila 0 y columna " + j + " ha sido eliminada por la tormenta solar.");
                }
            }
        }
    }*/
        
    public void ataquePiratas(NaveEspacial nave) {
        System.out.println("¡Ataque de piratas!");
        int filas = nave.getCabinas().length;
        int columnas = nave.getCabinas()[0].length;
        ArrayList<Persona> mujeresSecuestradas = new ArrayList<Persona>();
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (i == j && nave.getCabinas()[i][j] != null) {
                    Persona[] personas = nave.getCabinas()[i][j].getPersonas();
                    for (Persona persona : personas) {
                        if (persona.getGenero().equals("Femenino")) {
                            mujeresSecuestradas.add(persona);
                        }
                    }
                    nave.eliminarFamilia(i, j);
                }
            }
        }
        System.out.println("Se han secuestrado " + mujeresSecuestradas.size() + " mujeres:");
        for (Persona mujer : mujeresSecuestradas) {
            System.out.println("- " + mujer.getNombre());
        }
    }

    public void ataquePiratasHombresDiagonalSecundaria(NaveEspacial nave) {
        System.out.println("Ataque de piratas: secuestran todos los hombres de la diagonal secundaria");
    
        Familia[][] cabinas = nave.getCabinas();
    
        // Recorrer la diagonal secundaria de la matriz
        for (int i = 0; i < cabinas.length; i++) {
            int j = cabinas.length - 1 - i; // Calcular la columna correspondiente en la diagonal secundaria
            Familia familia = cabinas[i][j];
            if (familia != null) {
                Persona[] personas = familia.getPersonas();
                for (int k = 0; k < personas.length; k++) {
                    Persona persona = personas[k];
                    if (persona.getGenero().equals("Hombre")) {
                        familia.eliminarPersona(persona);
                        System.out.println("- " + persona.getNombre() + " ha sido secuestrado por los piratas");
                        k--; // Decrementar el índice para no saltar personas en el bucle
                    }
                }
            }
        }
    
        System.out.println("Ataque de piratas finalizado\n");
    }
    
    public static void esclavistasSecuestranMenores(NaveEspacial nave) {
        Familia[][] cabinas = nave.getCabinas();
        for (int i = 0; i < cabinas.length; i++) {
            for (int j = 0; j < cabinas[0].length; j++) {
                Familia familia = cabinas[i][j];
                if (familia != null) {
                    Persona[] personas = familia.getPersonas();
                    for (Persona persona : personas) {
                        if (persona.getEdad() < 18 && (i == j || i+j == cabinas.length-1)) {
                            familia.eliminarPersona(persona);
                        }
                    }
                }
            }
        }
    }
    

}
