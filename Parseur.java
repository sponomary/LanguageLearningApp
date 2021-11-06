package Projet;

import java.util.List;

/**
 * Interface que doit implémenter tout objet pouvant être parsé en morceaux.
 * @author AlexPo
 * @version 1.0
 */
public interface Parseur {

    /**
     * @param chaine à découper
     * @return liste de morceaux
     */
    public List<Morceau> parse(String chaine);

}
