import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaunchPage implements ActionListener {

    JFrame frame = new JFrame("BattleShip - Player's Details");//Το Frame και ο τιτλος
    JButton myButton = new JButton("OK");// Το κουμπι
    JLabel myLabel = new JLabel("Please insert your name and press \"OK\""); //Το Label που εξηγει τι πρεπει να γραψεις στο TextField
    JTextField myTextField = new JTextField();

    LaunchPage(){
        myLabel.setBounds(40,5,250,30);

        myTextField.setBounds(55,50,200,20);
        myTextField.setToolTipText("Please insert your name here"); //Ενημερωτικο μηνυμα οταν πας στο Textfield

        myButton.setBounds(120,85,60,30);
        myButton.setFocusable(false);
        myButton.addActionListener(this);

        frame.add(myLabel);
        frame.add(myTextField);
        frame.add(myButton);
        myButton.setEnabled(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(327,160);
        frame.setLayout(null);
        frame.setVisible(true);

        myTextField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                // Perform action here
            }
            public void removeUpdate(DocumentEvent e) {
                myButton.setEnabled(false);
            }
            public void insertUpdate(DocumentEvent e) {
                myButton.setEnabled(true);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == myButton) {
            frame.dispose();//close our frame
            NewWindow myWindow = new NewWindow();//create a new Window
        }
    }
}

