package exotptest;

public class PokemonTypeCore {
    enum TypeRelation{
        NEUTRE, SUPER_EFFICACE, PAS_TRES_EFFICACE, INEFFICACE
    }
    
    enum Type{
        NORMAL("Normal"), 
        FEU("Feu"), 
        EAU("Eau"), 
        PLANTE("Plante"), 
        ELECTRIK("Electrik"), 
        GLACE("Glace"), 
        COMBAT("Combat"), 
        POISON("Poison"), 
        SOL("Sol"), 
        VOL("Vol"), 
        PSY("Psy"), 
        INSECTE("Insecte"), 
        ROCHE("Roche"), 
        SPECTRE("Spectre"),
        DRAGON("Dragon"),
        TENEBRES("Ténèbres"), 
        ACIER("Acier"),
        FEE("Fée");
        
        private final String type;
        
        private Type(String type){
            this.type=type;
        }
        
        @Override
        public String toString(){
            return type;
        }
        
        public TypeRelation relatioTo(Type type){
            TypeRelation result=TypeRelation.NEUTRE;
            
            switch (this) {
                case ACIER -> {
                    switch (type) {
                        case FEE, GLACE, ROCHE -> result = TypeRelation.SUPER_EFFICACE;
                        case ACIER, EAU, ELECTRIK -> result = TypeRelation.PAS_TRES_EFFICACE;
                    }
                }

                case COMBAT -> {
                    switch (type) {
                        case ACIER, GLACE, NORMAL, ROCHE, TENEBRES -> result = TypeRelation.SUPER_EFFICACE;
                        case FEE, INSECTE, POISON, PSY, VOL -> result = TypeRelation.PAS_TRES_EFFICACE;
                        case SPECTRE -> result = TypeRelation.INEFFICACE;
                    }
                }

                case DRAGON -> {
                    switch (type) {
                        case DRAGON -> result = TypeRelation.SUPER_EFFICACE;
                        case ACIER -> result = TypeRelation.PAS_TRES_EFFICACE;
                        case FEE -> result = TypeRelation.INEFFICACE;
                    }
                }

                case EAU -> {
                    switch (type) {
                        case DRAGON, EAU, PLANTE -> result = TypeRelation.PAS_TRES_EFFICACE;
                        case FEU, ROCHE, SOL -> result = TypeRelation.SUPER_EFFICACE;
                    }
                }

                case ELECTRIK -> {
                    switch (type) {
                        case EAU, VOL -> result = TypeRelation.SUPER_EFFICACE;
                        case DRAGON, ELECTRIK, PLANTE -> result = TypeRelation.PAS_TRES_EFFICACE;
                        case SOL -> result = TypeRelation.INEFFICACE;
                    }
                }

                case FEE -> {
                    switch (type) {
                        case COMBAT, DRAGON, TENEBRES -> result = TypeRelation.SUPER_EFFICACE;
                        case ACIER, FEU, POISON -> result = TypeRelation.PAS_TRES_EFFICACE;
                    }
                }

                
                case FEU -> {
                    switch (type) {
                        case ACIER, GLACE, INSECTE, PLANTE -> result = TypeRelation.SUPER_EFFICACE;
                        case DRAGON, EAU, FEU, ROCHE -> result = TypeRelation.PAS_TRES_EFFICACE;
                    }
                }

                case GLACE -> {
                    switch (type) {
                        case DRAGON, PLANTE, ROCHE, VOL -> result = TypeRelation.SUPER_EFFICACE;
                        case ACIER, EAU, FEU -> result = TypeRelation.PAS_TRES_EFFICACE;
                    }
                }

                case INSECTE -> {
                    switch (type) {
                        case PLANTE, PSY, TENEBRES -> result = TypeRelation.SUPER_EFFICACE;
                        case ACIER, COMBAT, FEU, POISON, SPECTRE, ROCHE, VOL -> result = TypeRelation.PAS_TRES_EFFICACE;
                    }
                }

                case NORMAL -> {
                    switch (type) {
                        case ACIER, ROCHE -> result = TypeRelation.PAS_TRES_EFFICACE;
                        case SPECTRE -> result = TypeRelation.INEFFICACE;
                    }
                }
                    
                case PLANTE -> {
                    switch (type) {
                        case EAU, ROCHE, SOL -> result = TypeRelation.SUPER_EFFICACE;
                        case ACIER, DRAGON, FEU, INSECTE, PLANTE, POISON, VOL -> result = TypeRelation.PAS_TRES_EFFICACE;
                    }
                }

                case POISON -> {
                    switch (type) {
                        case FEE, PLANTE -> result = TypeRelation.SUPER_EFFICACE;
                        case POISON, ROCHE, SOL, SPECTRE -> result = TypeRelation.PAS_TRES_EFFICACE;
                        case ACIER -> result = TypeRelation.INEFFICACE;
                    }
                }

                case PSY -> {
                    switch (type) {
                        case COMBAT, POISON -> result = TypeRelation.SUPER_EFFICACE;
                        case ACIER, PSY -> result = TypeRelation.PAS_TRES_EFFICACE;
                        case TENEBRES -> result = TypeRelation.INEFFICACE;
                    }
                }

                case ROCHE -> {
                    switch (type) {
                        case FEU, GLACE, VOL, INSECTE -> result = TypeRelation.SUPER_EFFICACE;
                        case COMBAT, ROCHE, ACIER -> result = TypeRelation.PAS_TRES_EFFICACE;
                    }
                }

                case SOL -> {
                    switch (type) {
                        case ACIER, ELECTRIK, FEU, POISON, ROCHE -> result = TypeRelation.SUPER_EFFICACE;
                        case INSECTE, PLANTE -> result = TypeRelation.PAS_TRES_EFFICACE;
                        case VOL -> result = TypeRelation.INEFFICACE;
                    }
                }
                    
                case SPECTRE -> {
                    switch (type) {
                        case PSY, SPECTRE -> result = TypeRelation.SUPER_EFFICACE;
                        case TENEBRES -> result = TypeRelation.PAS_TRES_EFFICACE;
                        case NORMAL -> result = TypeRelation.INEFFICACE;
                    }
                }

                case TENEBRES -> {
                    switch (type) {
                        case PSY, SPECTRE -> result = TypeRelation.SUPER_EFFICACE;
                        case COMBAT, FEE, TENEBRES -> result = TypeRelation.PAS_TRES_EFFICACE;
                    }
                }

                case VOL -> {
                    switch (type) {
                        case COMBAT, INSECTE, PLANTE -> result = TypeRelation.SUPER_EFFICACE;
                        case ACIER, ELECTRIK, ROCHE -> result = TypeRelation.PAS_TRES_EFFICACE;
                    }
                }
            }
            
            return result;
        }
    }
    
    private int score;
    private boolean lock;
    private boolean correct;
    private Type type1;
    private Type type2;
    
    public PokemonTypeCore(){
        score=0;
        
        generate();
    }
    
    public final void generate(){
        Type[] types = Type.values();
        
        type1 = types[(int)(Math.random()*types.length)];
        type2 = types[(int)(Math.random()*types.length)];
        
        lock=false;
        correct=false;
    }
    
    public void tentative(String relation){
        if(lock)
            return;
        
        TypeRelation rel = TypeRelation.valueOf(relation);
        
        if(rel==type1.relatioTo(type2)){
            score++;
            correct=true;
        }
        else{
            correct=false;
        }
        
        lock=true;
    }
    
    public String getType1(){
        return type1.toString();
    }
    
    public String getType2(){
        return type2.toString();
    }
    
    public int getScore(){
        return score;
    }
    
    public String getGoodResponse(){
        return type1.relatioTo(type2).toString();
    }
}
