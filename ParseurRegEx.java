package Projet;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Cette classe permet de prendre en entrée les phrases du professeur,
 * les parser et construire listes de morceaux (morceaux fixes et morceaux variables)
 * @author AlexPo
 * @version 1.2
 */
public class ParseurRegEx implements Parseur {

    // Declaration des attributs
    private char delimiteur;
    private String regEx;
    private Pattern p;

    // STEP 1: Constructeurs (pour mettre à jour chaque attribut d'un nouvel objet)
    public ParseurRegEx() {
        delimiteur = '#'; // délimiteur par défaut
        regEx = "(?<=" + this.delimiteur + ")(\\S.*?\\S)(?=" + this.delimiteur + ")"; // (?<=#)(\\S.*?\\S)(?=#), alternatif : #.*?#
        p = Pattern.compile(regEx);
    }

    public ParseurRegEx(char delimiteur) {
        this.delimiteur = delimiteur;
        regEx = "(?<=" + this.delimiteur + ")(\\S.*?\\S)(?=" + this.delimiteur + ")";
        p = Pattern.compile(regEx);
    }

    // Constructeur de copie
    public ParseurRegEx(ParseurRegEx source) {
        this.delimiteur = source.delimiteur;
        this.regEx = source.regEx;
        this.p = source.p;
    }

    /**
     * Méthode qui parse la phrase du prof pour la découper en morceaux
     * @return List<Morceau> (liste de morceaux de la phrase du prof)
     */
    public List<Morceau> parse(String chaine) {

        List<Morceau> listeMorceaux = new ArrayList<>();
        Matcher m = p.matcher(chaine);

        int indicePrecedent = 0;
        while (m.find()) {

            // Récupération du morceau fixe et ajout à la liste (instance de l'objet Morceau)
            String chaineFixe = chaine.substring(indicePrecedent, m.start() - 1);

            if (!chaineFixe.equals("")) {
                Morceau morceauFixe = new Morceau(chaineFixe, indicePrecedent);
                listeMorceaux.add(morceauFixe);
            }

            // Récupération du morceau variable sans les # et ajout à la liste (instance de l'objet MorceauVariable)
            MorceauVariable morceauVar = new MorceauVariable(m.group(), m.start() - 1);
            listeMorceaux.add(morceauVar);

            indicePrecedent = m.end() + 1;
        }

        String chaineFixe = chaine.substring(indicePrecedent);
        if (!chaineFixe.equals("")) {
            Morceau morceauFixe = new Morceau(chaineFixe, indicePrecedent);
            listeMorceaux.add(morceauFixe);
        }

        return listeMorceaux;
    }
}
