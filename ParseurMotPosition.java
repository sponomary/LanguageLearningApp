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
public class ParseurMotPosition implements Parseur {

    private char delimiteur;

    public ParseurMotPosition(char caractere) {
        this.delimiteur = caractere;
    }

    /**
     * Méthode qui parse la phrase du prof pour la découper en morceaux
     * @return List<Morceau> (liste de morceaux de la phrase du prof)
     */
    public List<Morceau> parse(String chaine) {

        List<Morceau> listMorceau = new ArrayList<>();
        Pattern p = Pattern.compile("(?<=" + this.delimiteur + ")(\\S.*?\\S)(?=" + this.delimiteur + ")");
        Matcher m = p.matcher(chaine);

        int indicePrecedent = 0;
        while (m.find()) {

            // Récupération du morceau fixe et ajout à la liste (instance de l'objet Morceau)
            Morceau morceauFixe = new Morceau(chaine.substring(indicePrecedent, m.start() - 1), indicePrecedent);
            listMorceau.add(morceauFixe);

            // Récupération du morceau variable sans les # et ajout à la liste (instance de l'objet MorceauVariable)
            MorceauVariable morceauVar = new MorceauVariable(m.group(), m.start() - 1);
            listMorceau.add(morceauVar);

            indicePrecedent = m.end() + 1;
        }
        Morceau morceauFixe = new Morceau(chaine.substring(indicePrecedent, chaine.length()), indicePrecedent);
        listMorceau.add(morceauFixe);

        return listMorceau;
    }
}
