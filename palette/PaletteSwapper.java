package palette;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Class for applying the palette of one image to another
 * 
 */
public class PaletteSwapper
{
    private final HashSet<Color> sourcePalette;
    
    public PaletteSwapper()
    {
        sourcePalette = new HashSet<>();
    }
    
    /**
     * Adds all colors from the given image to the list of palette colors
     * 
     * @param image Palette source image
     */
    public void addColors(BufferedImage image)
    {
        int rgb;
        for (int x=0; x<image.getWidth(); x++) {
            for (int y=0; y<image.getHeight(); y++) {
                rgb = image.getRGB(x,y); 
                this.addColor(new Color(rgb, true));
            }
        }
    }
    
    /**
     * Adds a single color to the source palette
     * 
     * @param color 
     */
    public void addColor(Color color)
    {
        this.sourcePalette.add(color);
    }
    
    /**
     * Find a color from the source palette the most closely matches
     * the given color
     * 
     * @param color
     * @return a similar color from the source palette
     */
    public Color findClosestColor(Color color)
    {
        Iterator<Color> iterator = sourcePalette.iterator();
        Color closest = iterator.next();
        double difference = this.getColorDistance(color, closest);
        double candidateDiff;
        while (iterator.hasNext()) {
            Color candidate = iterator.next();
            candidateDiff = this.getColorDistance(color, candidate);
            if (candidateDiff < difference) {
                difference = candidateDiff;
                closest = candidate;
            }
        }
        
        return closest;
    }
    
    /**
     * Swap the colors in the target image out for the colors available in the source image
     * and store the results in a new image
     * 
     * @param source Source image from which to obtain the palette
     * @param target Image on which the palette should be replaced
     * @param newImage The new image created by this process
     * @return 
     */
    public BufferedImage swapPalettes(BufferedImage source, BufferedImage target, BufferedImage newImage)
    {
        Color color;
        this.addColors(source);
        for (int x=0; x<target.getWidth(); x++) {
            for (int y=0; y<target.getHeight(); y++) {
                color = new Color(target.getRGB(x, y), true);
                Color closestColor = this.findClosestColor(color);
                newImage.setRGB(x, y, closestColor.getRGB());
            }
        }
        return target;
    }
    
    /**
     * Calculates the difference between two given colors by treating them as
     * 3D points in space and finding the distance between them.
     * @param c1 Color 1
     * @param c2 Color 2
     * @return the "distance" between the two colors
     */
    private double getColorDistance(Color c1, Color c2)
    {
        return Math.sqrt(Math.pow(c2.getRed() - c1.getRed(), 2)
                + Math.pow(c2.getGreen() - c1.getGreen(), 2)
                + Math.pow(c2.getBlue() - c2.getBlue(), 2));
    }
    
}
