package BulkPhotoCreator.photo;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PhotoResizer {
	 
    /**
     * Resizes an image to a absolute width and height (the image may not be
     * proportional)
     * @param inputImagePath Path of the original image
     * @param outputImagePath Path to save the resized image
     * @param scaledWidth absolute width in pixels
     * @param scaledHeight absolute height in pixels
     * @throws IOException
     */
    public static void resize(String inputImagePath,
            String outputImagePath, int scaledWidth, int scaledHeight)
            throws IOException {
        // reads input image
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);
 
        // creates output image
        BufferedImage outputImage = new BufferedImage(scaledWidth,
                scaledHeight, inputImage.getType());
 
        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();
 
        // extracts extension of output file
        String formatName = outputImagePath.substring(outputImagePath
                .lastIndexOf(".") + 1);
 
        // writes to output file
        ImageIO.write(outputImage, formatName, new File(outputImagePath));
    }
 
    /**
     * Resizes an image by a percentage of original size (proportional).
     * @param inputImagePath Path of the original image
     * @param outputImagePath Path to save the resized image
     * @param percent a double number specifies percentage of the output image
     * over the input image.
     * @throws IOException
     */
    public static void resize(String inputImagePath,
            String outputImagePath, double percent) throws IOException 
    {
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);
        int scaledWidth = (int) (inputImage.getWidth() * percent);
        int scaledHeight = (int) (inputImage.getHeight() * percent);
        resize(inputImagePath, outputImagePath, scaledWidth, scaledHeight);
    }
 
    public static String[] printFiles(final File inputDirectoryPath, String[] fileNameList)
    {
    	int i = 0;
    		for (final File fileEntry:inputDirectoryPath.listFiles())
    		{
    			
    			if(fileEntry.isDirectory())
    			{
    				printFiles(fileEntry, fileNameList);
    			}
    			else
    			{
    				fileNameList[i] = fileEntry.getAbsoluteFile().toString();
    				//System.out.println(fileEntry.getAbsoluteFile() + " " + i);
    				i++;
    			}
    		}
    		//System.out.println(fileNameList[301]);
    		return fileNameList;
    }
    
    public static String[] printFiles2(final File inputDirectoryPath, String[] fileNameList)
    {
    	int i = 0;
    		for (final File fileEntry:inputDirectoryPath.listFiles())
    		{
    			
    			if(fileEntry.isDirectory())
    			{
    				printFiles(fileEntry, fileNameList);
    			}
    			else
    			{
    				fileNameList[i] = fileEntry.getName();
    				//System.out.println(fileEntry.getAbsoluteFile() + " " + i);
    				i++;
    			}
    		}
    		//System.out.println(fileNameList[301]);
    		return fileNameList;
    }

    public static int countFiles(final File inputDirectoryPath, int numOfFiles)
    {
    		for (final File fileEntry:inputDirectoryPath.listFiles())
    		{
    			if(fileEntry.isDirectory())
    			{
    				countFiles(fileEntry, numOfFiles);
    			}
    			else
    			{
    				//System.out.println(fileEntry.getName());
    				numOfFiles++;
    			}
    		}
    		return numOfFiles;
    }
    
    public static void renameFiles(String[] fileNames, int numOfFiles)
    {
    		for(int i = 0; i < numOfFiles; i++)
    		{
    			if(fileNames[i].contains("---"))
    			{
    				
    			}
    		}
    		System.out.println();
    		//return fileNames;
    }
    /**
     * Test resizing images
     */
    public static void main(String[] args) 
    {        
        String outputPath = "/Users/Kamaro/Pictures/NewAlbumCovers/";
        int numOfFiles = 0;
        
        final File folder = new File("/Users/Kamaro/Pictures/Album Covers/");
        numOfFiles = countFiles(folder, numOfFiles);
        
        String[] photosList = new String[numOfFiles];
        photosList = printFiles(folder, photosList);
        
        String[] photosList2 = new String[numOfFiles];
        photosList2 = printFiles2(folder, photosList2);
 
        try 
        {
            int scaledWidth = 750;
            int scaledHeight = 750;
            for(int i = 0; i < numOfFiles; i++)
            {
            		if(photosList[i].endsWith("g") || photosList[i].endsWith("G"))
            		{
            			//System.out.println(photosList[i]);
            			PhotoResizer.resize(photosList[i], outputPath + photosList2[i], scaledWidth, scaledHeight);
            		}
            }
        } 

        catch (IOException ex) 
        {
            System.out.println("Error resizing the image.");
            ex.printStackTrace();
        }
    }
 
}
