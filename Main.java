package Projet;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Application d'apprentissage des langues orientales et non-orientales.
 * @author AlexPo
 * @version 1.1
 */
public class Main {

    public static void main(String[] args) {

        // Lecture du fichier avec les exercices du professeur
        ArrayList<Phrase> listePhrasesProf = lectureFichierProf("src/projet/exercices.txt", '#');

        //------------------PROFESSEUR-------------------------
        printProf(listePhrasesProf);

        //-----------------------ÉLÈVE-------------------------
        printEleve(listePhrasesProf);

        //---------------------CORRECTION----------------------
        printCorrection(listePhrasesProf);
    }

    /**
     * Méthode qui prend en entrée une liste d'objets Phrase avec les exercices du professeur
     * Affichage dans la console de la version pour le prof
     */
    public static void printProf(ArrayList<Phrase> listePhrasesProf) {

        System.out.println("Version du prof :");
        for (Phrase p : listePhrasesProf) {
            System.out.println(p.pourProf());
        }
    }

    /**
     * Méthode qui prend en entrée une liste d'objets Phrase avec les exercices du professeur
     * Affichage dans la console de la version pour l'élève
     */
    public static void printEleve(ArrayList<Phrase> listePhrasesProf) {
        // Construction d'une liste des mots manquants
        String motsManquants = "";
        for (Phrase p: listePhrasesProf) {
            for (MorceauVariable mv: p.getMorceauxVariables()) {
                motsManquants += mv.pourReponseAttendu() + ", ";
            }
        }

        // Suppression de la virgule à la fin de la chaîne
        if (!(motsManquants.equals(""))) {
            motsManquants = motsManquants.substring(0, motsManquants.length() - 2);
        }

        // Affichage
        System.out.println("\n" + "Exercice de l'élève :");
        System.out.println("Liste de mots manquants : " + motsManquants + "\n");
        for(Phrase p : listePhrasesProf){
            System.out.println(p.pourEleve());
        }
    }

    /**
     * Méthode qui prend en entrée une liste d'objets Phrase avec les exercices du professeur
     * Affichage dans la console de la correction
     */
    public static void printCorrection(ArrayList<Phrase> listePhrasesProf) {
        // Lecture du fichier avec les réponses de l'élève
        ArrayList<Phrase> listeReponses = lectureReponses("src/projet/reponses.txt");

        // Affichage des réponses de l'élève
        System.out.println("\n" + "Correction des réponses de l'eleve : ");
        for(int i = 0; i< listePhrasesProf.size(); i++) {
            Correction cor = new Correction(listePhrasesProf.get(i));
            ArrayList<ElementCorrige> elementsCor = cor.corrige(listeReponses.get(i));

            List<String> listResultat = new ArrayList<>();

            for (ElementCorrige element : elementsCor) {
                listResultat.add("(" +  element.getReponseEleve().getChaine() + ", " + element.getReponseEleve().getPosition() + " : " + element.getCorrect()+ ")");
            }

            System.out.println(listResultat);
        }
    }

    /**
     * Méthode qui prend en entrée le fichier avec les réponses d'élève,
     * le lit et stocke dans une liste de chaînes de caractères.
     * @param cheminFichier (chemin vers le fichier d'un élève)
     * @return liste de phrase des réponses de l'élève
     */
    public static ArrayList<Phrase> lectureReponses(String cheminFichier) {
        ArrayList<Phrase> responses = new ArrayList<>();

        try {
            File fichier = new File(cheminFichier); // création d'un objet avec fichier d'entrée
            BufferedReader br = new BufferedReader(new FileReader(fichier)); // création de l'objet BufferedReader
            String ligne;

            while ((ligne = br.readLine()) != null) {
                responses.add(new Phrase(ligne));
            }

            br.close();
        } catch (FileNotFoundException exc) {
            System.out.println("Erreur d'ouverture du fichier");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responses;
    }


    /**
     * Méthode qui prend en entrée le fichier avec les exercices fournis par
     * professeur, le lit et stocke dans une liste de chaînes de caractères.
     * @param cheminFichier (chemin vers le fichier d'un professeur)
     * @param carParse (caractère permettant de distinguer les morceaux variables)
     * @return liste des phrases du prof
     */
    public static ArrayList<Phrase> lectureFichierProf(String cheminFichier, char carParse) {
        ArrayList<Phrase> phrases = new ArrayList<>();

        try {
            File file = new File(cheminFichier); // création d'un objet avec fichier d'entrée
            BufferedReader br = new BufferedReader(new FileReader(file)); // création de l'objet BufferedReader
            String ligne;

            while ((ligne = br.readLine()) != null) {
                Phrase phraseProf  = new Phrase(ligne);
                phraseProf.parsePhraseProf(carParse);
                phrases.add(phraseProf);
            }

            br.close();
        } catch(FileNotFoundException exc) {
            System.out.println("Erreur d'ouverture du fichier");
        } catch(IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

}

