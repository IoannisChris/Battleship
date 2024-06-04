
import java.util.Random;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class Game {
    public Player[] players  = new Player[10]; /* πίνακας παικτών */
    public char c;   /* tmp  xaraxthra */
    public JDialog placeshipsframe;
    public GameAction gameactionframe;
    public Player activePlayer; // paixths pou paizh
    public Player inactivePlayer; // paixths pou den paizei
    public int numberOfPlayers;
    public boolean cpugame;
    public Game(){

        int answer = JOptionPane.showConfirmDialog (null, "Single Player;","Game mode",JOptionPane.YES_NO_OPTION);
        if (answer==JOptionPane.YES_OPTION) {
            cpugame=true;
            numberOfPlayers=2;
            /* me cpu; */
            /*  an nai ,steise gia 2 paixtes */
            System.out.println("cpugame");

            /* sthsimo human player */
            String name = JOptionPane.showInputDialog(null, "Please insert your name and press \"OK\"","Player");
            players[0] = new Player(name, 0);
            placeshipsframe = new PlaceShips(this, players[0]);
            placeshipsframe.setModal(true);
            placeshipsframe.setVisible(true);

            /*sthsimo cpu player */
            players[1] = new Player(("cpu"), 1);
            int x,y;
            boolean h;
            Random rand = new Random();
            for (Ship s: players[1].ships) {
                do {
                    x = rand.nextInt(10);
                    y = rand.nextInt(10);
                    h = rand.nextBoolean();
                    System.out.println("x="+x+" y="+y);
                } while (players[1].addShip(s, x, y, h)==false);
            }



            activePlayer = players[0];
            inactivePlayer = players[1];

            gameactionframe = new GameAction(this);
            gameactionframe.setVisible(true);

        }
        else {
            System.out.println("multiplayer");
            cpugame=false;
        /*  an oxi ,
             zhta arithmo paixtwn x
            zhta ta onomata twn paixtwn
            sthse gia x paixtes
        */
            String np;
            do {
                np = JOptionPane.showInputDialog(null, "Αριθμός παικτών (2-10)" ,"2");
                numberOfPlayers = Integer.valueOf(np);
            } while (numberOfPlayers<2 || numberOfPlayers >10);

            for (int i=0; i<numberOfPlayers; i++) {
                String name = JOptionPane.showInputDialog(null, "Παίκτης " + (i+1) +". Δώσε όνομα και πάτα 'OK'.","Παίκτης"+(i+1));
                players[i] = new Player(name, i);

                int x, y;
                boolean z;

                placeshipsframe = new PlaceShips(this, players[i]);
                placeshipsframe.setModal(true);
                placeshipsframe.setVisible(true);
            }



            activePlayer = players[0];
            inactivePlayer = players[1];

            gameactionframe = new GameAction(this);
            gameactionframe.setVisible(true);

        }



    }

    /**
     * allazh thn seira twn paixtwn
     */
    public void nextPlayer() {

        if (!cpugame) {
            int next;
            next = (activePlayer.id + 1) % numberOfPlayers;
            activePlayer = players[next];

            //epelekse antipalo tablo, oxi omws to diko mou
            String np;
            int target;
            do {
                np = JOptionPane.showInputDialog(null, "Παίζει ο " + (activePlayer.id+1)+"\n Επέλεξε αντίπαλο (1-"+numberOfPlayers+")" ,"");
                target = Integer.valueOf(np)-1;
            } while (!(target>=0 && target<numberOfPlayers && target!=activePlayer.id));
            inactivePlayer = players[target];
        }





    }

    /**
     * DEN DOULEVEI   Ελέγχει εάν το παιχνίδι έχει τελειώσει
     * Εξέτάζει εάν όλα τα πλοία ενός παίκτη είναι βυθισμένα
     * @return True εάν το παιχνίδι τελείωσε, false αλλιώς
     */
//    public boolean isEndofGame() {
//        for(Player p: players) {
//            if (p.lost())
//                    return true;
//        }
//        return false;
//    }




}