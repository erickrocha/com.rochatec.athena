package com.rochatec.pos.athena.contribution;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.action.LegacyActionTools;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.rochatec.pos.athena.i18n.Message;
import com.rochatec.pos.athena.tools.GridLayoutBuilder;

public class ProductMenuContribution extends ContributionItem{
	
	public static String ID = "com.rochatec.pos.athena.contribution.ProductMenuContribution";
	private int charWidth;
	
	public final static int CALC_TRUE_WIDTH = -1;
	
	private Text txtProduct;	
	
	@Override
	public void fill(Composite parent) {
		new Label(parent, SWT.SEPARATOR);
		Composite composite = new Composite(parent,SWT.NONE);
		composite.setLayout(GridLayoutBuilder.getInstance().build(0,0,3));
		new CLabel(composite, SWT.NONE).setText(Message.getMessage("label.product"));
		txtProduct = new Text(composite,  SWT.SEARCH | SWT.ICON_SEARCH | SWT.CANCEL| SWT.BORDER);
		txtProduct.setMessage("Search");
	    GridDataFactory.fillDefaults().hint(250, SWT.DEFAULT).applyTo(txtProduct);		
	}

	public void setText(String text) {
		Assert.isNotNull(text);

		this.txtProduct.setText(LegacyActionTools.escapeMnemonics(text));

		if (this.txtProduct != null ) {
			this.txtProduct.setText(text);
		}

		if (this.txtProduct.getText().length() == 0) {
			if (isVisible()) {
				setVisible(false);
				IContributionManager contributionManager = getParent();

				if (contributionManager != null) {
					contributionManager.update(true);
				}
			}
		} else {
			// Always update if using 'CALC_TRUE_WIDTH'
			if (!isVisible() || charWidth == CALC_TRUE_WIDTH) {
				setVisible(true);
				IContributionManager contributionManager = getParent();

				if (contributionManager != null) {
					contributionManager.update(true);
				}
			}
		}
	}

}
