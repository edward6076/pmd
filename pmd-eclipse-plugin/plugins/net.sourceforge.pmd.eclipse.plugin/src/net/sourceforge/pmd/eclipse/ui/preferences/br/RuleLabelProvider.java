package net.sourceforge.pmd.eclipse.ui.preferences.br;

import net.sourceforge.pmd.Rule;
import net.sourceforge.pmd.eclipse.ui.preferences.AbstractTableLabelProvider;

import org.eclipse.swt.graphics.Image;

/**
 *
 * @author Brian Remedios
 */
public class RuleLabelProvider extends AbstractTableLabelProvider {

    private RuleColumnDescriptor[] columnDescriptors;

    /**
     * Constructor for RuleLabelProvider.
     * @param columns RuleColumnDescriptor[]
     */
    public RuleLabelProvider(RuleColumnDescriptor[] columns) {
    	columnDescriptors = columns;
    }

    public String getDetailText(Object element, int columnIndex) {

    	if (columnIndex < 0) {
    		return "??";
    	}
    	
    	if (element instanceof Rule) {
        	Rule rule = (Rule) element;
        	return columnDescriptors[columnIndex].detailStringFor(rule);
        }

    	if (element instanceof RuleGroup) {
        	RuleGroup group = (RuleGroup) element;
        	return columnDescriptors[columnIndex].detailStringFor(group);
        }
    	
    	return "??";
    }

    /**
     * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(Object, int)
     */
    public String getColumnText(Object element, int columnIndex) {

        if (element instanceof RuleCollection) {
        	if (columnIndex == 0) {
        		RuleGroup rg = (RuleGroup)element;
            	String label = rg.label();
            	return standardized(label, rg.ruleCount());
        	}
        	return columnDescriptors[columnIndex].stringValueFor((RuleCollection)element);
        }

        if (element instanceof Rule) {
        	return columnDescriptors[columnIndex].stringValueFor((Rule) element);
        }

        return "??";
    }

    /**
     * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(Object, int)
     */
    public Image getColumnImage(Object element, int columnIndex) {
    	
        if (element instanceof RuleCollection) {
            return columnDescriptors[columnIndex].imageFor((RuleCollection)element);
        }

        if (element instanceof Rule) {
            return columnDescriptors[columnIndex].imageFor((Rule) element);
        }
            
        return null;	// should never get here
    }

    /**
     * @param rawLabel String
     * @param count int
     * @return String
     */
    private String standardized(String rawLabel, int count) {

    	int rulesPos = rawLabel.indexOf(" Rules");
    	String filteredLabel = rulesPos > 0 ?
    			rawLabel.substring(0, rulesPos) : rawLabel;

    	return filteredLabel + "  (" + count + ")";
    }

}