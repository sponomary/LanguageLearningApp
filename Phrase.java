package Projet;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

/**
 * Cette classe permet de reconstruire les phrases à partir des morceaux fixes et
 * morceaux variables en fonction du destinataire (professeur / élève).
 * @author AlexPo
 * @version 1.2
 */
public class Phrase {

    private String phrase;
    private List<Morceau> listeMorceaux = new ArrayList<>();
    private LinkedList<MorceauVariable> morceauxVariables = new LinkedList<>();
    private LinkedList<Morceau> morceauxFixes = new LinkedList<>();

    // STEP 1: Constructors (to update every field of a new object)
    public Phrase() {
    }

    public Phrase(String phrase) {
        this.phrase = phrase;
    }

    // STEP 2: Getter (to access field values by getting its copy)
    public String getPhrase() {
        return phrase;
    }

    public LinkedList<Morceau> getMorceauxFixes() {
        return morceauxFixes;
    }

    public LinkedList<MorceauVariable> getMorceauxVariables() {
        return morceauxVariables;
    }

    public List<Morceau> getListeMorceaux() {
        return listeMorceaux;
    }

    // STEP 3: Setter (to update private fields)
    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public void setMorceauxFixes(LinkedList<Morceau> morceauxFixes) {
        this.morceauxFixes = morceauxFixes;
    }

    public void setMorceauxVariables(LinkedList<MorceauVariable> morceauxVariables) {
        this.morceauxVariables = morceauxVariables;
    }

    public void setListeMorceaux(List<Morceau> listeMorceaux) {
        this.listeMorceaux = listeMorceaux;
    }

    // STEP 4: Actions and other methods
    /**
     * Méthode qui parse la phrase du prof (découpage en morceaux)
     * @param delimiteur (caractère permettant de distinguer les morceaux variables)
     */
    public void parsePhraseProf(char delimiteur) throws Exception {
        Parseur parsePhrase = new ParseurRegEx(delimiteur);
        this.listeMorceaux = parsePhrase.parse(this.phrase);
        this.setTypeListeMorceaux(this.listeMorceaux);
    }

    /**
     * Méthode qui repartit les morceaux dans 2 listes : morceaux fixe et morceaux variables
     * @param listeMorceaux (Liste des morceaux (fixe et variable) de la phrase)
     */
    public void setTypeListeMorceaux(List<Morceau> listeMorceaux) throws Exception {
        for (Morceau m: listeMorceaux) {
            if (m instanceof MorceauVariable) {
                morceauxVariables.add((MorceauVariable) m);
            } else {
                morceauxFixes.add(m);
            }
        }
        if (morceauxVariables.isEmpty()) {
            throw new Exception("ERREUR : Il faut avoir au minimum 1 morceau variable !");
        }  else if (morceauxFixes.isEmpty()) {
            throw new Exception("ERREUR : Il faut avoir au minimum 1 morceau fixe !");
        }
    }

    /**
     * Méthode prend la réponse de l'élève en entrée et le découpe en morceau.
     * @param reponseEleve (réponse de l'élève)
     * @return List<Morceau> (liste de morceaux de la phrase de l'élève)
     */
    public List<Morceau> analyseReponseEleve(String reponseEleve) throws Exception {
        List<Morceau> listMorceau = new ArrayList<>();

        int debutPosVar = 0; // début de position variable
        int finPosVar; // fin de position variable

        int plusLoin = 0;

        for (Morceau m: morceauxFixes) {
            int debutPosFixe = reponseEleve.indexOf(m.getChaine(), plusLoin);

        if (debutPosFixe == -1) {
            throw new Exception("La réponse de l'élève a été indument modifiée, morceaux fixe non trouvés : " + m + "dans" + reponseEleve);
        }

        int finPosFixe = debutPosFixe + m.getChaine().length();

        finPosVar = debutPosFixe; // la fin d'une position variable est le début d'une position fixe

        if ((finPosVar - debutPosVar) > 0) {
            String morceauVariable = reponseEleve.substring (debutPosVar, finPosVar);
            MorceauVariable mv = new MorceauVariable (morceauVariable, debutPosVar);
            listMorceau.add(mv);
        }

        Morceau mf = new Morceau (reponseEleve.substring (debutPosFixe, finPosFixe), debutPosFixe);
        listMorceau.add(mf);

        debutPosVar = finPosFixe;
        plusLoin = finPosFixe;
    }
    // Vérifier le dernier Morceau
	if (debutPosVar < reponseEleve.length()) {
        String morceauVariable = reponseEleve.substring(debutPosVar);
        MorceauVariable mv = new MorceauVariable(morceauVariable, debutPosVar);
        listMorceau.add(mv);
    }
        return listMorceau;
    }

    /**
     * Méthode affiche les morceaux pour le professeur, où le morceau variable
     * est entre deux caractères-délimiteurs (normalement, les dièses #)
     * @return chaîne de caractères (phrase pour le professeur)
     */
    public String pourProf() {
        return getPhrase();
    }

    /**
     * Méthode affiche les morceaux pour l'élève, où le morceau variable
     * est remplacé par les points de suspension (…)
     * @return chaîne de caractères (phrase pour l'élève)
     */
    public String pourEleve() {
        StringBuilder sb = new StringBuilder(500);

        for (Morceau m: listeMorceaux) {
                sb.append(m.pourEleve());
        }
        return sb.toString();
    }
}
