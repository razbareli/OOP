package filesprocessing.filessections;

import filesprocessing.filesexceptions.TypeOneExceptions;
import filesprocessing.filesexceptions.TypeTwoExceptions;
import filesprocessing.filesfilters.*;
import filesprocessing.filesorders.AbsOrder;
import filesprocessing.filesorders.OrderSort;
import filesprocessing.filesorders.SizeOrder;
import filesprocessing.filesorders.TypeOrder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import static java.lang.Double.parseDouble;

/**
 * creates multiple sections according to the .flt file given
 */
public class SectionsFactory {

    /**
     * error and warning messages
     */
    private static final String NO_FILTER = "no filter subsection";
    private static final String NO_ORDER = "no order subsection";

    /**
     * strings that can appear in the filter sub section
     */
    private static final String FILTER = "FILTER";
    private static final String GREATER_THAN = "greater_than";
    private static final String BETWEEN = "between";
    private static final String SMALLER_THAN = "smaller_than";
    private static final String FILE = "file";
    private static final String CONTAINS = "contains";
    private static final String PREFIX = "prefix";
    private static final String SUFFIX = "suffix";
    private static final String WRITEABLE = "writable";
    private static final String EXECUTABLE = "executable";
    private static final String HIDDEN = "hidden";
    private static final String ALL = "all";
    private static final String NOT = "NOT";
    private static final String SPLIT = "#";

    /**
     * strings that can appear in the order sub section
     */
    private static final String ORDER = "ORDER";
    private static final String ABS = "abs";
    private static final String TYPE = "type";
    private static final String SIZE = "size";
    private static final String REVERSE = "REVERSE";

    /**
     * array of all sections generated from the file
     */
    public static ArrayList<Section> sectionArray = new ArrayList<Section>();

    /**
     * a static instance of the SectionFactory, for it to be a singleton
     */
    private static final SectionsFactory singletonFactory = new SectionsFactory();


    /**
     * instance method for the singleton approach
     *
     * @return a static instance of SectionFactory
     */
    public static SectionsFactory instance() {
        return singletonFactory;
    }

    /**
     * creates all sections in the .flt file into an array of sections
     *
     * @param sectionAddress the address of the .flt file
     * @throws IOException       when the file/address is not readable
     * @throws TypeTwoExceptions then there is a problem with the content of the file
     */
    public void SectionsGenerator(String sectionAddress) throws IOException, TypeTwoExceptions {
        BufferedReader reader = null;
        reader = new BufferedReader(new FileReader(sectionAddress));
        String line = reader.readLine();
        int lineNumber = 1;
        ArrayList<Integer> warningsArray = new ArrayList<Integer>();
        while (line != null) {
            //FILTER line
            if (!line.equals(FILTER)) {
                reader.close();
                throw new TypeTwoExceptions(NO_FILTER);
            }
            //filter description line
            line = reader.readLine();
            lineNumber++;
            String[] currFilter = line.split(SPLIT);

            //check if the line ends with #NOT
            boolean notFilter = line.endsWith(NOT);

            //decide what filter to choose
            FilterInterface filter = null;

            switch (currFilter[0]) {
                case (GREATER_THAN):
                    try {
                        filter = new GreaterThanFilter(parseDouble(currFilter[1]));
                    } catch (TypeOneExceptions t1e) {
                        warningsArray.add(lineNumber);
                        filter = new AllFilter();
                    }
                    break;
                case (BETWEEN):
                    try {
                        filter = new BetweenFilter(parseDouble(currFilter[1]),
                                parseDouble(currFilter[2]));
                    } catch (TypeOneExceptions t1e) {
                        warningsArray.add(lineNumber);
                        filter = new AllFilter();
                    }
                    break;
                case (SMALLER_THAN):
                    try {
                        filter = new SmallerThanFilter(parseDouble(currFilter[1]));
                    } catch (TypeOneExceptions t1e) {
                        warningsArray.add(lineNumber);
                        filter = new AllFilter();
                    }
                    break;
                case (FILE):
                    filter = new FileFilter(currFilter[1]);
                    break;
                case (CONTAINS):
                    filter = new ContainsFilter(currFilter[1]);
                    break;
                case (PREFIX):
                    filter = new PrefixFilter(currFilter[1]);
                    break;
                case (SUFFIX):
                    filter = new SuffixFilter(currFilter[1]);
                    break;
                case (WRITEABLE):
                    try {
                        filter = new WriteableFilter(currFilter[1]);
                    } catch (TypeOneExceptions t1e) {
                        warningsArray.add(lineNumber);
                        filter = new AllFilter();
                    }
                    break;
                case (EXECUTABLE):
                    try {
                        filter = new ExecutableFilter(currFilter[1]);
                    } catch (TypeOneExceptions t1e) {
                        warningsArray.add(lineNumber);
                        filter = new AllFilter();
                    }
                    break;
                case (HIDDEN):
                    try {
                        filter = new HiddenFilter(currFilter[1]);
                    } catch (TypeOneExceptions t1e) {
                        warningsArray.add(lineNumber);
                        filter = new AllFilter();
                    }
                    break;
                case (ALL):
                    filter = new AllFilter();
                    break;
                default:
                    filter = new AllFilter();
                    warningsArray.add(lineNumber);
            }

            //ORDER line
            line = reader.readLine();
            lineNumber++;
            if (line == null || !line.equals(ORDER)) {
                reader.close();
                throw new TypeTwoExceptions(NO_ORDER);
            }
            //order description line
            line = reader.readLine();
            lineNumber++;
            if (line == null || line.equals(FILTER)) {//if this is the end of the section
                sectionArray.add(new Section(filter,
                        new AbsOrder(false), notFilter, warningsArray));
                warningsArray.clear();
                if (line == null) {//if there is no more lines
                    reader.close();
                    break;
                }
                // if it's a start of new section
                continue;
            }

            //if the order description line exists:
            boolean reverseFilter = line.endsWith(REVERSE);

            //decide what order to choose
            String[] currOrder = line.split(SPLIT);

            //decide what order to choose
            OrderSort order = null;

            switch (currOrder[0]) {
                case (ABS):
                    order = new AbsOrder(reverseFilter);
                    break;
                case (TYPE):
                    order = new TypeOrder(reverseFilter);
                    break;
                case (SIZE):
                    order = new SizeOrder(reverseFilter);
                    break;
                default:
                    warningsArray.add(lineNumber);
                    order = new AbsOrder(reverseFilter);
            }

            //generate segment
            sectionArray.add(new Section(filter, order, notFilter, warningsArray));
            warningsArray.clear();
            line = reader.readLine();
            lineNumber++;
        }
    }
}
