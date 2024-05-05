package exotptest.swing;

import exotptest.PokemonTypeCore;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class IHMSwing extends JFrame{
    private int time = 15;
    private final PokemonTypeCore coeur;
    private final JLabel temps;
    private final JLabel score;
    private HashMap<String, JButton> buttons;
    private Color baseColor;
    
    public IHMSwing() {
        
        this.setTitle("Le truc de gros modo discord qui se branle en pensant aux Pokémons");
        this.resize(720,300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        coeur = new PokemonTypeCore();
        
        JPanel structure = new JPanel();
        structure.setLayout(new GridLayout(3,1));
        
        JPanel types = new JPanel(new GridLayout(1,3));
        JLabel t1 = new JLabel(coeur.getType1());
        t1.setHorizontalAlignment(JLabel.CENTER);
        types.add(t1);
        JLabel amettre = new JLabel("mettre image");
        amettre.setHorizontalAlignment(JLabel.CENTER);
        types.add(amettre);
        JLabel t2 = new JLabel(coeur.getType2());
        t2.setHorizontalAlignment(JLabel.CENTER);
        types.add(t2);
        structure.add(types);
        
        JPanel stats = new JPanel(new GridLayout(2,1));
        score = new JLabel("Score : " + coeur.getScore());
        score.setHorizontalAlignment(JLabel.CENTER);
        stats.add(score);
        temps = new JLabel(time + " s");
        temps.setHorizontalAlignment(JLabel.CENTER);
        Timer timer = new Timer(1000, e -> {
            time -= 1;
            if (time<0){
                time=15;
                coeur.generate();
                t1.setText(coeur.getType1());
                t2.setText(coeur.getType2());
                
                for (JButton button : buttons.values()){
                    button.setBackground(baseColor);
                }
            }
            temps.setText(time + " s");
        });
        timer.start();
        stats.add(temps);
        structure.add(stats);
        
        
        
        JPanel mesure = new JPanel(new GridLayout(1,4));
        
        buttons=new HashMap();
        JButton inefficace = new JButton("Inefficace"); 
        inefficace.addActionListener(new Niveau("INEFFICACE"));
        buttons.put("INEFFICACE", inefficace);
        mesure.add(inefficace);
        
        JButton pasefficace = new JButton("pas très efficace");
        pasefficace.addActionListener(new Niveau("PAS_TRES_EFFICACE"));
        buttons.put("PAS_TRES_EFFICACE", pasefficace);
        mesure.add(pasefficace);
        
        JButton neutre = new JButton("Neutre");
        neutre.addActionListener(new Niveau("NEUTRE"));
        buttons.put("NEUTRE", neutre);
        mesure.add(neutre);
        
        JButton superefficace = new JButton("Super Efficace");
        superefficace.addActionListener(new Niveau("SUPER_EFFICACE"));
        buttons.put("SUPER_EFFICACE", superefficace);
        mesure.add(superefficace);
        structure.add(mesure);
        
        baseColor=inefficace.getBackground();
        
        this.getContentPane().add(structure, BorderLayout.CENTER);
    }
    
    class Niveau implements ActionListener{
        private final String niveau;
        public Niveau(String niveau){
            this.niveau=niveau;
        }
        @Override
        public void actionPerformed(ActionEvent e){
            coeur.tentative(niveau);
            score.setText("Score : " + coeur.getScore());
            
            if (time > 3){
                time = 3;
                temps.setText(""+time+" s");
            }
            
            for(JButton button : buttons.values())
                button.setBackground(new Color(155,20,20));
            buttons.get(coeur.getGoodResponse()).setBackground(new Color(10, 130, 10));
        }
    }
}
