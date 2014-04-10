package com.rochatec.athena.manufacture.ncm.dialog;

import java.util.List;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.manufacture.ncm.box.NcmSearchBox;
import com.rochatec.athena.manufacture.ncm.provider.NcmLabelProvider;
import com.rochatec.athena.manufacture.ncm.table.NcmTable;
import com.rochatec.athena.model.Ncm;
import com.rochatec.framework.model.Executable;
import com.rochatec.graphics.dialog.AbstractDialog;
import com.rochatec.graphics.provider.GenericContentProvider;

public class NcmDialog extends AbstractDialog<Ncm> implements Executable<Ncm>{

	public NcmDialog(Shell owner) {
		super(owner);
	}
	
	@Override
	protected Point getInitialSize() {
		return new Point(800,600);
	}

	@Override
	public void execute(List<Ncm> objects) {
		table.setInput(objects);
	}

	@Override
	public void createSearchArea(Composite parent) {
		new NcmSearchBox(parent,this);
	}

	@Override
	public void createTable(Composite parent) {
		table = new NcmTable(parent);
		table.setContentProvider(new GenericContentProvider<Ncm>());
		table.setLabelProvider(new NcmLabelProvider());
	}

	@Override
	public String getTitle() {
		return Messages.getMessage("ncm.dialog.title");
	}

	@Override
	public void execute(Ncm object) {
		
	}

}
