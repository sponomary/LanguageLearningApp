package Projet;

import java.util.List;
import java.util.ArrayList;

/**
 * Cette classe permet de corriger la phrase entière.
 * @author AlexPo
 * @version 1.2
 */
public class Correction {

    private Phrase phraseProf;
    private ArrayList<ElementCorrige> elementsCorriges = new ArrayList<>();

    public Correction(Phrase phraseProf) {
        this.phraseProf = phraseProf;
    }

    /**
     * Méthode qui corrige les réponses de l'élève
     * @return retourne une liste d'ElementCorrige
     */
    public ArrayList<ElementCorrige> corrige(Phrase phraseEleve) {
        List<String> listResultat = new ArrayList<>();

        // Découpage de la réponse de l'éleve en morceaux à partir des morceaux fixes du prof
        List<Morceau> morceauxEleve = phraseProf.analyseReponseEleve(phraseEleve.getPhrase());
        phraseEleve.setListeMorceaux(morceauxEleve);
        phraseEleve.setTypeListeMorceaux(morceauxEleve);

        // Récupération des morceaux variables de l'élève
        List<MorceauVariable> morceauxVariablesEleve = phraseEleve.getMorceauxVariables();

        // Boucle sur les éléments variables
        for (int j = 0; j<morceauxVariablesEleve.size(); j++){

            // Construction d'un objet ElementCorrige avec le morceaux de l'élève et le morceaux du prof correspondant
            ElementCorrige elementCorrige = new ElementCorrige(morceauxVariablesEleve.get(j), this.phraseProf.getMorceauxVariables().get(j));

            // Appel de la méthode qui permet de corriger (VRAI, FAUX, NR)
            elementCorrige.setCorrection();

            // Construction de la liste des éléments corrigés
            this.elementsCorriges.add(elementCorrige);
        }
        return this.elementsCorriges;
    }
}
