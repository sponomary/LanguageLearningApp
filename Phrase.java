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
        //this.parsePhraseProf('#'); Suppression afin de faire un constructeur generique reutilisable pour la phrase de l'élève
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
     * @param carParse (caractère permettant de distinguer les morceaux variables)
     */
    public void parsePhraseProf(char carParse) {
        Parseur parsePhrase = new ParseurMotPosition(carParse);
        this.listeMorceaux = parsePhrase.parse(this.phrase);
        this.setTypeListeMorceaux(this.listeMorceaux);
    }

    /**
     * Méthode qui repartit les morceaux dans 2 listes : morceaux fixe et morceaux variables
     * @param listeMorceaux (Liste des morceaux (fixe et variable) de la phrase)
     */
    public void setTypeListeMorceaux(List<Morceau> listeMorceaux){
        for (Morceau m: listeMorceaux) {
            if (m instanceof MorceauVariable) {
                morceauxVariables.add((MorceauVariable) m);
            } else {
                morceauxFixes.add(m);
            }
        }
    }

    /**
     * Méthode prend la réponse de l'élève en entrée et le découpe en morceau.
     * @param reponseEleve (réponse de l'élève)
     * @return List<Morceau> (liste de morceaux de la phrase de l'élève)
     */
    public List<Morceau> analyseReponseEleve(String reponseEleve) {
        List<Morceau> listMorceau = new ArrayList<>();

        int indexFin = 0;
        for (int i = 0; i<morceauxFixes.size()-1; i++) {

            // Calcul de l'index de fin d'un morceau fixe
            int indexDebut = reponseEleve.indexOf(morceauxFixes.get(i).getChaine(), indexFin);
            indexFin = indexDebut + morceauxFixes.get(i).getChaine().length();

            // Calcul de l'index du morceau fixe suivant
            int indexDebutSuivant = reponseEleve.indexOf(morceauxFixes.get(i + 1).getChaine(), indexFin);

            // Récupération du morceau fixe et ajout à la liste (instance de l'objet Morceau)
            Morceau morceauFixe = new Morceau(reponseEleve.substring(indexDebut, indexFin), indexDebut);
            listMorceau.add(morceauFixe);

            // Substring du morceau correspondant à la réponse de l'élève
            String reponseMorceau = reponseEleve.substring(indexFin, indexDebutSuivant);
            MorceauVariable morceauVar = new MorceauVariable(reponseMorceau, indexFin);
            listMorceau.add(morceauVar);
        }

        // Calcul pour le dernier morceau fixe de la phrase
        int indexDebut = reponseEleve.indexOf(morceauxFixes.getLast().getChaine(), indexFin);
        indexFin = indexDebut + morceauxFixes.getLast().getChaine().length();
        Morceau morceauFixe = new Morceau(reponseEleve.substring(indexDebut, indexFin), indexDebut);
        listMorceau.add(morceauFixe);

        return listMorceau;
    }

    /**
     * Méthode affiche les morceaux pour le professeur, où le morceau variable
     * est entre deux caractères-délimiteurs (normallement, les dièses #)
     * @return chaîne de caractères (phrase pour le professeur)
     */
    public String pourProf() {
        return getPhrase();
    }

    /**
     * Méthode affiche les morceaux pour l'élève, où le morceau variable
     * est remplacé par les points de suspension (...)
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
