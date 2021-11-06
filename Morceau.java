package Projet;

/**
 * Cette classe permet de découper les phrases en morceaux.
 * @author AlexPo
 * @version 1.1
 */
public class Morceau {
    protected String chaine;
    protected int position;

    // STEP 1: Constructors (to update every field of a new object)
    public Morceau() {
    }

    public Morceau(String chaine, int position) {
        this.chaine = chaine;
        this.position = position;
    }

    // STEP 2: Getter (to access field values by getting its copy)
    public String getChaine() {
        return chaine;
    }

    public int getPosition() {
        return position;
    }

    // STEP 3: Setter (to update private fields)
    public void setChaine(String chaine) {
        this.chaine = chaine;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    // STEP 4: Actions and other methods
    /**
     * Méthode qui retourne la chaîne pour le prof.
     * @return chaîne de caractères (morceau fixe)
     */
    public String pourProf() {
        return this.chaine;
    }

    /**
     * Méthode qui retourne la chaîne pour l'élève.
     * @return chaîne de caractères (morceau fixe)
     */
    public String pourEleve() {
        return this.chaine;
    }

    /**
     * Méthode qui retourne la chaîne pour la réponse attendu.
     * @return chaîne de caractères
     */
    public String pourReponseAttendu() {
        return this.chaine;
    }

    @Override
    public String toString() {
        return "Morceau {" +
                "chaine='" + chaine + '\'' +
                ", position=" + position +
                '}';
    }
}
