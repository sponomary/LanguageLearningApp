package Projet;

/**
 * Cette classe permet de remplacer les morceaux variables par les trous.
 * @author AlexPo
 * @version 1.1
 */
public class MorceauVariable extends Morceau{

    public MorceauVariable(String chaine, int position) {
        this.chaine = chaine;
        this.position = position;
    }

    @Override
    public String pourProf() {
        return "#" + this.chaine + "#";
    }

    @Override
    public String pourEleve() {
        return "...";
    }

}
