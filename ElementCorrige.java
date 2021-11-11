package Projet;

/**
 * Cette classe permet de corriger un élément.
 * @author AlexPo
 * @version 1.2
 */
public class ElementCorrige {

    private Morceau reponseEleve;
    private Morceau reponseAttendue;
    private REPONSE correct;

    // STEP 1: Constructors (to update every field of a new object)
    public ElementCorrige(Morceau reponseEleve, Morceau reponseAttendue) {
        this.reponseEleve = reponseEleve;
        this.reponseAttendue = reponseAttendue;
    }

    // STEP 2: Getter (to access field values by getting its copy)
    public Morceau getReponseEleve() {
        return reponseEleve;
    }

    public Morceau getReponseAttendue() {
        return reponseAttendue;
    }

    public REPONSE getCorrect() {
        return correct;
    }

    // STEP 3: Setter (to update private fields)
    public void setReponseEleve(Morceau reponseEleve) {
        this.reponseEleve = reponseEleve;
    }

    public void setReponseAttendue(Morceau reponseAttendue) {
        this.reponseAttendue = reponseAttendue;
    }

    public void setCorrect(REPONSE correct) {
        this.correct = correct;
    }

    // STEP 4: Actions and other methods
    /**
     * Méthode qui compare les morceaux variables de l'éléve avec les morceaux variables du prof (VRAI, FAUX , NR)
     */
    public REPONSE setCorrection() {

        if (reponseEleve.getChaine().equals("..."))  {
            this.correct = REPONSE.NR;
        } else {
            if (reponseEleve.getChaine().equals(reponseAttendue.getChaine())) {
                this.correct = REPONSE.VRAI;
            } else {
                this.correct = REPONSE.FAUX;
            }
        }
        return this.correct;
    }
}
