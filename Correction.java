package Projet;

import java.util.List;
import java.util.ArrayList;

/**
 * Cette classe permet de corriger la phrase entière.
 * @author AlexPo
 * @version 1.0
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

        //découpage de la réponse de l'éleve en morceaux à partir des morceaux fixes du prof
        List<Morceau> morceauxEleve = phraseProf.analyseReponseEleve(phraseEleve.getPhrase());
        phraseEleve.setListeMorceaux(morceauxEleve);
        phraseEleve.setTypeListeMorceaux(morceauxEleve);

        //recupération des morceaux variables de l'élève
        List<MorceauVariable> morceauxVariableEleve = phraseEleve.getMorceauxVariables();

        //boucle sur les éléments variables
        for(int j = 0; j< morceauxVariableEleve.size(); j++){

            //construction d'un objet ElementCorrige avec le morceaux de l'élève et le morceaux du prof correspondant
            ElementCorrige elemtCorr = new ElementCorrige(morceauxVariableEleve.get(j), this.phraseProf.getMorceauxVariables().get(j));

            //appel de la méthode qui permet de corriger (VRAI, FAUX , NR)
            elemtCorr.setCorrection();

            //construction de la liste des éléments corrigés
            this.elementsCorriges.add(elemtCorr);
        }

        return this.elementsCorriges;
    }
}
