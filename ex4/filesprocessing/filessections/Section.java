package filesprocessing.filessections;

import filesprocessing.filesfilters.FilterInterface;
import filesprocessing.filesorders.OrderSort;

import java.io.File;
import java.util.ArrayList;

/**
 * this class represent a section within the .flt file
 */
public class Section {

    /**
     * template for warnings
     */
    private static final String WARNING = "Warning in line ";

    /**
     * array with all the warnings for this section
     */
    ArrayList<Integer> warningsArray;

    /**
     * the filter for this section
     */
    private final FilterInterface sectionFilter;

    /**
     * the order for this section
     */
    private final OrderSort sectionOrder;

    /**
     * boolean filter #NOT for the filter
     */
    private final boolean notFilter;

    /**
     * constructor
     * @param sectionFilter the filter to apply
     * @param sectionOrder the order to apply
     * @param notFilter true if the #NOT filter should be applied,
     *                  false otherwise
     * @param warningsArray array with all the warnings for this section
     */
    public Section(FilterInterface sectionFilter, OrderSort sectionOrder, boolean notFilter,
                   ArrayList<Integer> warningsArray){
        this.sectionFilter = sectionFilter;
        this.sectionOrder = sectionOrder;
        this.notFilter = notFilter;
        this.warningsArray = new ArrayList<Integer>(warningsArray);
    }

    /**
     * runs the section on an array of files
     * prints them according to the instructions
     * @param fileArray the files array to apply the section on
     */
    public void executeSection(ArrayList<File> fileArray){
        ArrayList<File> tempArr = new ArrayList<File>(fileArray);
        if(!notFilter) {
            tempArr.removeIf(curr -> !sectionFilter.filter(curr));
        }else{
            tempArr.removeIf(curr -> sectionFilter.filter(curr));
        }
        sectionOrder.sort(tempArr);
        for (Integer i : warningsArray) {
            System.err.print(WARNING);
            System.err.println(i);
        }
        for (File curr: tempArr){
            System.out.println(curr.getName());
        }
    }


}
