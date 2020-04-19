package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class Main {

    static BufferedImage inputPic;
    static BufferedImage outputPic;
    public static void main(String[] args) {
        try {
            System.out.println("Processing image...");
            inputPic = ImageIO.read(new File("donald_hacking.jpg"));
            outputPic =new BufferedImage(inputPic.getWidth(), inputPic.getHeight(),TYPE_INT_RGB);

            //sobel's filter
            int xKernel[][] = {{-1,0,1},
                                {-2,0,2},
                                {-1,0,1}};

            int yKernel[][] = {{-1,-2,-1},
                                {0,0,0},
                                { 1, 2, 1}};

            for(int i = 1; i< inputPic.getWidth()-1; i++){
                for(int j = 1; j< inputPic.getHeight()-1; j++){
                    int gradient_X =
                            //sobel kernel times greyscale value of pixel at index
                            xKernel[0][0] * new Color(inputPic.getRGB(i-1, j-1)).getRed() +
                            xKernel[0][1] * new Color(inputPic.getRGB(i, j-1)).getRed() +
                            xKernel[0][2] * new Color(inputPic.getRGB(i+1, j-1)).getRed() +
                            xKernel[1][0] * new Color(inputPic.getRGB(i-1, j)).getRed() +
                            xKernel[1][1] * new Color(inputPic.getRGB(i, j)).getRed() +
                            xKernel[1][2] * new Color(inputPic.getRGB(i+1, j)).getRed() +
                            xKernel[2][0] * new Color(inputPic.getRGB(i-1, j+1)).getRed() +
                            xKernel[2][1] * new Color(inputPic.getRGB(i, j+1)).getRed() +
                            xKernel[2][2] * new Color(inputPic.getRGB(i+1, j+1)).getRed();

                    int gradient_Y =
                            //same as before but doing kernel for horizontal edges
                            yKernel[0][0] * new Color(inputPic.getRGB(i-1, j-1)).getRed() +
                                    yKernel[0][1] * new Color(inputPic.getRGB(i, j-1)).getRed() +
                                    yKernel[0][2] * new Color(inputPic.getRGB(i+1, j-1)).getRed() +
                                    yKernel[1][0] * new Color(inputPic.getRGB(i-1, j)).getRed() +
                                    yKernel[1][1] * new Color(inputPic.getRGB(i, j)).getRed() +
                                    yKernel[1][2] * new Color(inputPic.getRGB(i+1, j)).getRed() +
                                    yKernel[2][0] * new Color(inputPic.getRGB(i-1, j+1)).getRed() +
                                    yKernel[2][1] * new Color(inputPic.getRGB(i, j+1)).getRed() +
                                    yKernel[2][2] * new Color(inputPic.getRGB(i+1, j+1)).getRed();

                    //magnitude of x & j gradient using pythagoras' theorem
                    int val = (int)Math.sqrt((gradient_X * gradient_X) + (gradient_Y * gradient_Y));
                    //get rid of gradient magnitudes that aren't logical color values
                    if(val < 0) val = 0;
                    if (val > 255) val = 255;

                    //change resulting val to greyscale
                    int r = (val >> 16) & 0xFF;
                    int g = (val >> 8) & 0xFF;
                    int b = (val & 0xFF);

                    int grayLevel = (r + g + b) / 3;
                    int gray = (grayLevel << 16) + (grayLevel << 8) + grayLevel;
                    //write the greyscale val
                    outputPic.setRGB(i, j, gray);
                }
            }
            System.out.println("Done!");
            File outputFile = new File("SobelOutput.jpg");
            ImageIO.write(outputPic,"jpg", outputFile);

        } catch (IOException ex) {System.err.println("Error: " + ex.getMessage());}
    }
}
