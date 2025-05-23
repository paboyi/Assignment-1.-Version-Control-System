package org.example;

/**
 * ShapeFactory is made to define the characteristics/properties of the animated images show on our compiled software.
 *
 * ShapeFactory(type int) - defines the Shape and color, by using switch statement and mathematical equations (/ & %).
 * @author Philippa Aboyi
 * @version 1.2
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D.Double;


/**
 * <p>
 * The input number is divided into two parts:
 * <ul>
 *   <li> (shape_type / 10) decides the shape (star, rectangle, triangle).</li>
 *   <li> (shape_type % 10) decides the style (stroke thickness, color).</li>
 * </ul>
 * </p>
 */
public class ShapeFactory {
   public Shape shape;
   public BasicStroke stroke = new BasicStroke(3.0F);
   public Paint paint;
   public int width = 25;
   public int height = 25;


    /**
     * Creates a ShapeFactory object based on a type code.
     *
     * @param shape_type - an integer where tens define the shape and ones define the style
     */

   public ShapeFactory(int shape_type) {
      switch(shape_type / 10) {
      case 1: // hexagon -  6 sided shape
         this.shape = createStar(3, new Point(0, 0), (double)this.width / 2.0D, (double)this.width / 2.0D);
         break;
      case 2: //5 point star - normal star
         this.shape = createStar(5, new Point(0, 0), (double)this.width / 2.0D, (double)this.width / 4.0D);
         break;
      case 3: //rectangle
         this.shape = new Double((double)(-this.width) / 2.0D, (double)(-this.height) / 2.0D, (double)this.width, (double)this.height);
         break;
      case 4: //Triangle
         GeneralPath path = new GeneralPath();
         double tmp_height = Math.sqrt(2.0D) / 2.0D * (double)this.height;
         path.moveTo((double)(-this.width / 2), -tmp_height);
         path.lineTo(0.0D, -tmp_height);
         path.lineTo((double)(this.width / 2), tmp_height);
         path.closePath();
         this.shape = path;
         break;
//      case 9:
//         this.shape = new java.awt.geom.Arc2D.Double((double)(-this.width) / 2.0D, (double)(-this.height) / 2.0D, (double)this.width, (double)this.height, 30.0D, 300.0D, 2);
      default:
              throw new Error("type is not supported");
      }

      switch(shape_type % 10) {
      case 1: //Default Stroke
         this.stroke = new BasicStroke(3.0F);
         break;
      case 2: //Thick Stroke
         this.stroke = new BasicStroke(7.0F);
         break;
      case 3: // Gradient fill (white to gray)
         this.paint = new GradientPaint((float)(-this.width), (float)(-this.height), Color.white, (float)this.width, (float)this.height, Color.gray, true);
         break;
      case 4: // Solid red fill
         this.paint = Color.red;
        break;
      default:
          throw new Error("type is not supported");
      }

   }



   /**
    * Purposed to create a star shape with specific parameters
    * @param arms - amount of arms a star shape has
    * @param center - the center of the shape
    * @param rInner - inner radius
    * @param rOuter - outer radius
    * @return path - a geometric path constructed from straight lines
    */
   private static Shape createStar(int arms, Point center, double rOuter, double rInner) {
      double angle = 3.141592653589793D / (double)arms;
      GeneralPath path = new GeneralPath();

      for(int i = 0; i < 2 * arms; ++i) {
         double r = (i & 1) == 0 ? rOuter : rInner;
         java.awt.geom.Point2D.Double p = new java.awt.geom.Point2D.Double((double)center.x + Math.cos((double)i * angle) * r, (double)center.y + Math.sin((double)i * angle) * r);
         if (i == 0) {
            path.moveTo(p.getX(), p.getY());
         } else {
            path.lineTo(p.getX(), p.getY());
         }
      }

      path.closePath();
      return path;
   }
}
