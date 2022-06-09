package filesprocessing;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import filesprocessing.filesexceptions.TypeTwoExceptions;
import filesprocessing.filessections.Section;
import filesprocessing.filessections.SectionsFactory;


/**
 * contains a main method which runs the program
 */
public class DirectoryProcessor {



    public static void main(String[] args) throws IOException {
        // check number of arguments
        if (args.length != 2){
            System.err.println("ERROR: Should have received 2 arguments");
            return;
        }
        //create an array of all files in the source directory
        ArrayList<File> filesArray = FilesUtilities.FilesToArr(args[0]);

        //create a singleton instance of sectionFactory
        SectionsFactory sectionsFactory = SectionsFactory.instance();

        //try to generate all the sections in the .flt file
        try{
            sectionsFactory.SectionsGenerator(args[1]);
        }catch(TypeTwoExceptions t2e){
            t2e.printError();
            return;
        }

        //run all the sections on the files in the source directory
        for (Section section : SectionsFactory.sectionArray){
            section.executeSection(filesArray);
        }
    }
}
