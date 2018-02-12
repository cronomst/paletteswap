package palette;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main
{
    /**
     * Palette Swap entry point
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args)
    {
        if (args.length < 3) {
            System.out.println("Usage:\n"
                    + "java palette/Main <palette source image> <target image> <output image>\n\n"
                    + "- palette source image: The image from which the palette should be created\n"
                    + "- target image: The image that should have its colors replaced with the source palette\n"
                    + "- output image: The filename of the new image generated from the two other images\n");
            System.exit(0);
        }
        File sourceFile = new File(args[0]);
        File targetFile = new File(args[1]);
        File outputFile = new File(args[2]);
        PaletteSwapper swapper = new PaletteSwapper();
        
        try {
            BufferedImage sourceImage = createRGBImage(ImageIO.read(sourceFile), true);
            BufferedImage targetImage = createRGBImage(ImageIO.read(targetFile), true);
            BufferedImage newImage = createRGBImage(targetImage, false);
            
            swapper.swapPalettes(sourceImage, targetImage, newImage);
            
            boolean success = ImageIO.write(newImage, "png", outputFile);
            if (success) {
                System.out.println("Wrote image to " + outputFile.getPath());
            } else {
                System.out.println("Failed to write image to " + outputFile.getPath());
            }
            
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    
    /**
     * Creates a new ARGB image from the given image so that all of images will be
     * of the same type and color model
     * 
     * @param source
     * @param copyImage
     * @return new ARGB image based on the source image
     */
    private static BufferedImage createRGBImage(BufferedImage source, boolean copyImage)
    {
        BufferedImage rgbImage = new BufferedImage(source.getWidth(), source.getHeight(), BufferedImage.TYPE_INT_ARGB);
        if (copyImage) {
            rgbImage.createGraphics().drawImage(source, 0, 0, null);
        }
        
        return rgbImage;
    }
}
