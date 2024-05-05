package tp1;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Classe gérant une calculatrice 4 opérations sans priorité des opérateurs
 * 
 * @author Jean-Charles BOISSON
 * @version 1.0
 */
public class Calculatrice_Core {
    
    /** Caractères acceptés comme opérateur (incluant le signe égal) */
    private final Character[] SYMBOLS = {'+','-','*','/','='};
    /** Caractères autorisés, c'est à dire les nombres de 0 à 9*/
    private final Character[] NUMBERS = {'0','1','2','3','4','5','6','7','8','9'};
    
    /** Valeur actuelle de l'opérande 1 */
    private Integer operande_1;
    /** Valeur actuelle de l'opérande 2 */
    private Integer operande_2;
    /** Indique si l'opérande 1 est un résultat de calcul*/
    private boolean operande_1_est_un_resultat;
     
    /** Dernier opérateur saisi*/
    private Character operator;
    
    /** Version liste du tableau de symboles autorisés */
    private final ArrayList<Character> symbols;
    /** Version liste du tableau de chiffres autorisés */
    private final ArrayList<Character> numbers;
     
    /**
     * Constructeur classique
     */
    public Calculatrice_Core() {
        
        // Rien n'est actuellement saisi
        operande_1=null;
        operande_2=null;
        operator=null;
        operande_1_est_un_resultat=false;
     
        // Les tableaux sont créés
        symbols = new ArrayList<>(Arrays.asList(SYMBOLS));
        numbers = new ArrayList<>(Arrays.asList(NUMBERS));
    }
     
    /**
     * Procédure indiquant la saisie d'un chiffre (un caractère à la fois)
     * 
     * @param number Caractère représentant un chiffre
     */
    public void add_number(Character number) {
        
        // Test d'appartenance
        if(! numbers.contains(number)) {
            System.err.println(number+" n'est pas dans "+numbers);
            return;
        }
        
        // Gestion des différents cas
        // Cas 1 : première saisie
        if(operande_1 == null) {
            operande_1= Integer.valueOf(number.toString());
        } else {
            
            // Cas 2 : n-ième saisie avant la saisie de l'opérateur
            if (operator == null)  {
                // Cas particuliers, un calcul a déjà eu lieu
                // Seul un opérateur peut être saisi
                // donc rien ne se passe si cette variable est vraie
                if(operande_1_est_un_resultat)
                    System.err.println("Opérateur attendu");
                else {
                    String operande = operande_1.toString();
                    operande+=number;   
                    operande_1= Integer.valueOf(operande);
                }
            } else {
                // Cas 3 : première saisie après celle de l'opérateur
                if(operande_2 == null) {
                    operande_2 = Integer.valueOf(number.toString());
                } else {
                    // Cas 4 : n-ième saisie après celle de l'opérateur
                    String operande = operande_2.toString();
                    operande+=number;   
                    operande_2= Integer.valueOf(operande);
                }
            }   
        }
    }
    
    /**
     * Procédure indiquant la saisie d'un symbole (un caractère à la fois)
     * 
     * @param symbol Caractère représentant un chiffre
     */
    public void add_symbol(Character symbol) {
        
        // Test d'appartenance
        if(! symbols.contains(symbol)) {
            System.err.println(symbol+" n'est pas dans "+symbols);
            return;
        }
        
        // Test si operateur ou "="
        switch(symbol) {
            case '+', '-', '*', '/' -> operator = symbol;
            case '=' -> {
                if( (operator==null) || (operande_1==null) || (operande_2==null) ){
                    System.err.println("Calcul impossible: "+toString());
                    return;
                }
                // operande_1 contient le résultat
                switch(operator) {
                    case '+' -> operande_1+=operande_2;
                    case '-' -> operande_1-=operande_2;
                    case '*' -> operande_1*=operande_2;
                    case '/' -> operande_1/=operande_2;
                }
                
                operande_1_est_un_resultat=true;
                operator = null;
                operande_2=null;
            }
        }
    }
    
    /**
     * Function pour savoir quoi afficher sur l'écran de la calculatrice
     * 
     * @return La valeur à afficher à l'écran
     */
    public Integer getCurrentValue() {
        if(operande_2==null)
            return operande_1;
        else
            return operande_2;
    }
    
    /**
     * Surcharge du toString pour afficher l'état de la classe 
     * 
     * @return Le détail des 2 opérandes actuels et de l'opérateur
     */
    @Override
    public String toString() {
        return "operande 1 = "+operande_1+", operateur = "+operator+" et operande 2 = "+operande_2;
    }
}
