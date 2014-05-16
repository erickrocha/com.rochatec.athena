package com.rochatec.athena.manufacture.natureOfOperation.tree;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.rochatec.athena.i18n.Messages;
import com.rochatec.graphics.tree.AbstractTree;

public class NatureOfOperationTree extends AbstractTree{

	public NatureOfOperationTree(Composite parent) {
		this(parent,SWT.NONE);
	}
	
	public NatureOfOperationTree(Composite parent,int style) {
		super(parent,style);
	}

	@Override
	protected void makeCollumns(TreeViewer treeViewer) {
		TreeViewerColumn cfop = new TreeViewerColumn(treeViewer, SWT.NONE);
		cfop.getColumn().setWidth(150);
		cfop.getColumn().setMoveable(false);
		cfop.getColumn().setText(Messages.getMessage("natureofoperation.field.label.cfop"));
		
		TreeViewerColumn application = new TreeViewerColumn(treeViewer, SWT.NONE);
		application.getColumn().setWidth(350);
		application.getColumn().setMoveable(false);
		application.getColumn().setText(Messages.getMessage("natureofoperation.field.label.application"));
		
		TreeViewerColumn label = new TreeViewerColumn(treeViewer, SWT.NONE);
		label.getColumn().setWidth(600);
		label.getColumn().setMoveable(false);
		label.getColumn().setText(Messages.getMessage("natureofoperation.field.label.label"));
		
	}

}
