package com.rochatec.athena.humanresources.employee.view;

import java.util.List;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionService;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.humanresources.employee.box.EmployeeSearchBox;
import com.rochatec.athena.model.Employee;
import com.rochatec.athena.provider.DefaultSelectionProvider;
import com.rochatec.framework.model.Executable;
import com.rochatec.graphics.selection.SearchSelection;
import com.rochatec.graphics.view.AbstractView;

public class EmployeeSearchView extends AbstractView implements Executable<Employee> {

	public static final String ID = "com.rochatec.metallurgical.humanresources.employee.view.EmployeeSearchView";
	private EmployeeSearchBox searchBox;
	
	@Override
	protected void createContents(Composite parent) {
		searchBox = new EmployeeSearchBox(parent,this);		
	}

	@Override
	protected void addListeners() {
		getSite().setSelectionProvider(new DefaultSelectionProvider());
		getSite().getSelectionProvider().addSelectionChangedListener(
				(ISelectionChangedListener) Activator.getDefault().getView(
						EmployeeTableView.ID));
	}

	@Override
	public void setFocus() {
		searchBox.setFocus();
	}

	public void execute(List<Employee> employees) {
		SearchSelection<Employee> selection = new SearchSelection<Employee>(
				employees);
		getSite().getSelectionProvider().setSelection(selection);
	}

	public ISelectionService getSelectionService() {
		return getSite().getWorkbenchWindow().getSelectionService();
	}

	@Override
	public void execute(Employee object) {
		
	}

}
