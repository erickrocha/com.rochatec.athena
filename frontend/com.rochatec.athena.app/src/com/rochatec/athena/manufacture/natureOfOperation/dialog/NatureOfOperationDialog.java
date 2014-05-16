package com.rochatec.athena.manufacture.natureOfOperation.dialog;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.manufacture.natureOfOperation.box.NatureOfOperationSearchBox;
import com.rochatec.athena.manufacture.natureOfOperation.provider.NatureOfOperationContentProvider;
import com.rochatec.athena.manufacture.natureOfOperation.provider.NatureOfOperationLabelProvider;
import com.rochatec.athena.manufacture.natureOfOperation.tree.NatureOfOperationTree;
import com.rochatec.athena.model.NatureOfOperation;
import com.rochatec.framework.model.Executable;
import com.rochatec.graphics.dialog.AbstractDialog;

public class NatureOfOperationDialog extends AbstractDialog<NatureOfOperation> implements Executable<NatureOfOperation>{
	
	public NatureOfOperationDialog(Shell owner) {
		super(owner);
	}

	@Override
	public void createSearchArea(Composite parent) {
		new NatureOfOperationSearchBox(parent, this);
	}
	
	@Override
	protected Point getInitialSize() {
		return new Point(1024,768);
	}

	@Override
	public void createTable(Composite parent) {
		table = new NatureOfOperationTree(parent,SWT.BORDER | SWT.SINGLE| SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.setContentProvider(new NatureOfOperationContentProvider());
		table.setLabelProvider(new NatureOfOperationLabelProvider());
	}

	@Override
	public String getTitle() {		
		return Messages.getMessage("natureOfOperation.title");
	}

	@Override
	public void execute(List<NatureOfOperation> objects) {
		table.setInput(objects);
	}

	@Override
	public void execute(NatureOfOperation object) {
	}

}
