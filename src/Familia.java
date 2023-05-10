public class Familia {
    private Persona[] personas;

    public Familia(Persona[] personas){
        this.personas = personas;
    }

    public Persona[] getPersonas() {
        return personas;
    }

    public void setPersonas(Persona[] personas) {
        this.personas = personas;
    }

    public boolean contienePersona(Persona persona) {
        for (Persona p : personas) {
            if (p.equals(persona)) {
                return true;
            }
        }
        return false;
    }

    public int getNumeroPersonas() {
        return personas.length;
    }    
    
    public void eliminarPersona(Persona persona) {
        for (int i = 0; i < personas.length; i++) {
            if (personas[i].equals(persona)) {
                personas[i] = null;
                return;
            }
        }
    }
}
