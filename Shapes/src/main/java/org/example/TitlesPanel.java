package org.example;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import javax.swing.JPanel;
import javax.swing.Timer;


/**
 * TitlesPanel is a custom JPanel that draws animated shapes.
 *
 * It uses ShapeFactory to generate the shape and style, and then
 * repeatedly draws many rotated copies of that shape in a grid pattern.
 *
 * The shapes slowly rotate to create a simple animation effect.
 *
 * @author Philippa Aboyi
 * @version 1.1
 */
public class TitlesPanel extends JPanel implements ActionListener {
   private Graphics2D g2d;
   private Timer animation;
   private boolean is_done = true;
   private int start_angle = 0;
   private int shape;


   /**
    * Constructor: sets the shape type and starts the animation timer.
    *
    * @param _shape - an integer code that defines shape and style (used in ShapeFactory)
    */
   public TitlesPanel(int _shape) {
      this.shape = _shape;
      (this.animation = new Timer(50, this)).setInitialDelay(50);
      this.animation.start();
   }


   /**
    * Called automatically by the timer to trigger screen repainting.
    */
   public void actionPerformed(ActionEvent arg0) {
      if (this.is_done) {
         this.repaint();
      }

   }


   /**
    * Handles the actual drawing of the shapes.
    * It fills the panel with many rotated copies of the same shape.
    * @param g - the graphics context used for drawing
    */
   private void doDrawing(Graphics g) {
      this.is_done = false;
      (this.g2d = (Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      Dimension size = this.getSize();  //Full size of panel
      Insets insets = this.getInsets();  // Padding/border area
      int w = size.width - insets.left - insets.right;
      int h = size.height - insets.top - insets.bottom;

      // Create the shape based on the given shape code
      ShapeFactory shape = new ShapeFactory(this.shape);

      this.g2d.setStroke(shape.stroke); // Set outline thickness
      this.g2d.setPaint(shape.paint);  // Set color or gradient

      // Rotation angle for the animation
      double angle = (double)(this.start_angle++);
      if (this.start_angle > 360) {
         this.start_angle = 0;
      }


      // Rotation step between each shape
      double dr = 90.0D / ((double)w / ((double)shape.width * 1.5D));


      // Draw the shapes in a grid layout
      for(int j = shape.height; j < h; j += (int)((double)shape.height * 1.5D)) {
         for(int i = shape.width; i < w; i += (int)((double)shape.width * 1.5D)) {
            angle = angle > 360.0D ? 0.0D : angle + dr;

            // Move and rotate shape to proper position
            AffineTransform transform = new AffineTransform();
            transform.translate((double)i, (double)j);
            transform.rotate(Math.toRadians(angle));

            // Draw transformed shape
            this.g2d.draw(transform.createTransformedShape(shape.shape));
         }
      }

      this.is_done = true;
   }


   /**
    * Called whenever the panel needs to be redrawn.
    */
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      this.doDrawing(g);
   }
}
