package org.example;

import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


/**
 * The TitlesFrame class creates a basic window (JFrame)
 * that displays a custom panel with drawn shapes or figures.
 */
public class TitlesFrame extends JFrame {

   /**
    * Constructor for TitlesFrame.
    * It initializes the user interface.
    */
   public TitlesFrame() { this.initUI(); }


   /**
    * Sets up the window properties such as title, size,
    * what happens when the window is closed, and adds the drawing panel.
    */
   private void initUI() {

      // Sets the title of the window
      this.setTitle("Криві фігури"); // "Curved Shapes" in Ukrainian

      // Ensures the program exits when the window is closed
      this.setDefaultCloseOperation(3);

      // Adds a custom panel (likely for drawing) to the frame
      this.add(new TitlesPanel(13));

      // Sets the size of the window
      this.setSize(350, 350);

      // Centers the window on the screen
      this.setLocationRelativeTo((Component)null);
   }


   /**
    * The main method to run the program.
    * It ensures that the UI is created on the Event Dispatch Thread,
    * which is the proper way to build GUI in Swing.
    */
   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            TitlesFrame ps = new TitlesFrame();
            ps.setVisible(true); // Makes the window visible
         }
      });
   }
}
