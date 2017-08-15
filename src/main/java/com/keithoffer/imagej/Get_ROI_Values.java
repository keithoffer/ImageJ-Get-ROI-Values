import java.awt.*;

import ij.*;
import ij.plugin.filter.PlugInFilter;
import ij.process.*;
import ij.measure.*;

public class Get_ROI_Values implements PlugInFilter, Measurements {

    public int setup(String s, ImagePlus imagePlus) {
        return DOES_8G + SUPPORTS_MASKING;
    }

    public void run(ImageProcessor ip) {
        byte[] pixels = (byte[]) ip.getPixels();
        int width = ip.getWidth();
        Rectangle rect = ip.getRoi();

        if (rect.height == ip.getHeight() && rect.width == ip.getWidth())
            IJ.showMessage("No ROI specified. Analysing entire image.");

        ResultsTable rt = new ResultsTable();

        int pixel_index,pixel_index_offset;
        for (int y = rect.y; y < (rect.y + rect.height); y++) {
            pixel_index_offset = y * width;
            for (int x = rect.x; x < (rect.x + rect.width); x++) {
                pixel_index = pixel_index_offset + x;
                rt.incrementCounter();
                rt.addValue("X", x);
                rt.addValue("Y", y);
                rt.addValue("Value",0xff & pixels[pixel_index]); // need to use the bitwise AND to give unsigned result
            }
        }

        rt.show("Pixel values");
    }

    /**
     * Main method for debugging.
     * <p>
     * For debugging, it is convenient to have a method that starts ImageJ, loads
     * an image and calls the plugin, e.g. after setting breakpoints.
     * This is taken from https://github.com/imagej/example-legacy-plugin
     *
     * @param args unused
     */
    public static void main(String[] args) {
        // set the plugins.dir property to make the plugin appear in the Plugins menu
        Class<?> clazz = Get_ROI_Values.class;
        String url = clazz.getResource("/" + clazz.getName().replace('.', '/') + ".class").toString();
        String pluginsDir = url.substring("file:".length(), url.length() - clazz.getName().length() - ".class".length());
        System.setProperty("plugins.dir", pluginsDir);

        new ImageJ();

        //Load a 8-bit greyscale image from the ImageJ website
        ImagePlus image = IJ.openImage("https://imagej.nih.gov/ij/images/abe.tif");
        image.show();

        IJ.runPlugIn(clazz.getName(), "");
    }
}