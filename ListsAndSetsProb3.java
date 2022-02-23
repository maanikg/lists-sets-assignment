/* Maanik Gogna
 * Assignment 5 - Lists and Sets Problem 3
 * May 9, 2021
 * 
 * Program provides a simple GUI that allows user to view a deck of cards numbered 1 to 25(number of cards set by the user) that have been modified according to specific actions:
 * 		(1) The top card of the deck of cards is displayed
 * 		(2) The following card is moved to the bottom of the deck
 * 		(3) The following card is displayed
 * 		(4) The following card is moved to the bottom of the deck
 * The above process is repeated until all cards in the deck are displayed. 
 * Assuming that all the cards in the deck are placed in consecutive order from 1 to the last card, the program finds the original order of the cards.
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class ListsAndSetsProb3 extends JPanel implements ActionListener{

	//Variable initialization
	static JTextField deckSizeField = new JTextField(3);
	static JLabel outputMessage = new JLabel();
	static ListsAndSetsProb3 cardsPanel = new ListsAndSetsProb3();
	static JFrame programThreeFrame = new JFrame("Program 3");
	static ArrayList<ImageIcon> cardImages = new ArrayList<ImageIcon>();
	static ArrayList<JLabel> cardLabels = new ArrayList<JLabel>();
	
	//Constructor for panel
	public ListsAndSetsProb3() {
		//Adding buttons and text field
		JButton exitButton = new JButton("Exit");
		add(exitButton);
		exitButton.addActionListener(this);
		exitButton.setActionCommand("exit");
		exitButton.setVisible(true);
		
		JLabel askDeckSize = new JLabel("        Enter deck size(1-25 inclusive): ");
		add(askDeckSize);
		add(deckSizeField);
		
		JButton deckButton = new JButton("Submit deck size");
		add(deckButton);
		deckButton.addActionListener(this);
		deckButton.setActionCommand("enter deck");
		deckButton.setVisible(true);		
		add(outputMessage);
		
		setPreferredSize(new Dimension(550, 1000));
		setVisible(true);
	}
	
	public static void findOriginal(int numCards) {
		/* Purpose: finds the original order of the cards and adds the cards to a deque
		 * Params: the size of the deck of cards(inputted by the user)
		 * Returns void
		 */
		Deque<Integer> deckCards = new LinkedList<Integer>();
		
		//Reversing the actions taken on the deck of cards(see lines 5-10)
		for (int count = numCards; count > 0; count--) {
			if (count == 1) 
				deckCards.addFirst(count);
			else {
				deckCards.addFirst(count);
				deckCards.addFirst(deckCards.getLast());
				deckCards.removeLast();
			}
		}

		//Setting the icons for the cards
		for (int count = 1; count <= numCards; count++) {
			cardLabels.get(count-1).setIcon(cardImages.get(deckCards.getFirst()-1));
			deckCards.removeFirst();
		}
	}
	
	public static void main(String[] args) {
		//Setting layout and adding images to the arraylist of card images
		cardsPanel.setLayout(new FlowLayout(5, 10, 3));
		for (int count = 1; count <=25; count++) {
			ImageIcon currImage = new ImageIcon(count + ".gif");
			currImage = new ImageIcon(currImage.getImage().getScaledInstance(120, 90, Image.SCALE_SMOOTH));
			cardImages.add(currImage);
			cardLabels.add(new JLabel());
			cardsPanel.add(cardLabels.get(count-1));
		}
		
		//Displaying the program
		programThreeFrame.add(cardsPanel);
		programThreeFrame.pack();
		programThreeFrame.setResizable(false);
		programThreeFrame.setVisible(true);
		programThreeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void actionPerformed(ActionEvent event) {
		//If the exit button is clicked, exit the program
		if (event.getActionCommand().equals("exit"))
			System.exit(0);
		else if (event.getActionCommand().equals("enter deck")) {
			try {
				//Displaying cards
				for (int count = 1; count <= 25; count++) 
					cardLabels.get(count-1).setIcon(new ImageIcon());
				int numCards = Integer.parseInt(deckSizeField.getText().trim());
				if (numCards<1 || numCards > 25)
					throw new NumberFormatException();
				outputMessage.setText("                             Card Order: Left to Right, Top to Bottom                             ");
				findOriginal(numCards);
				deckSizeField.setText("");
			}catch (NumberFormatException e) {
				//output checking for invalid input
				outputMessage.setText("                  ERROR - Deck size must be between 1 and 25 inclusive                       ");
				deckSizeField.setText("");
			}
		}
		
	}

}