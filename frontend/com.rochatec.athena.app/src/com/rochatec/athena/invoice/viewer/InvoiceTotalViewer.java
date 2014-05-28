package com.rochatec.athena.invoice.viewer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.ITableFontProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.util.Formatter;
import com.rochatec.framework.exception.BadFormatException;
import com.rochatec.graphics.provider.GenericContentProvider;
import com.rochatec.graphics.util.Colors;
import com.rochatec.graphics.util.FontToolkit;
import com.rochatec.graphics.util.LayoutFactory;

public class InvoiceTotalViewer {

	private Composite container;	
	private TableViewer tableViewer;
	
	public InvoiceTotalViewer(Composite parent) {		
		createContents(parent);		
	}
	
	
	private void createContents(Composite parent){
		this.container = new Composite(parent,SWT.NONE);
		FillLayout layout = new FillLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.spacing = 0;
		this.container.setLayout(LayoutFactory.getInstance().getFillLayout());
		tableViewer = new TableViewer(container,SWT.BORDER | SWT.SINGLE|SWT.FULL_SELECTION);
		tableViewer.getTable().setHeaderVisible(true);
//		tableViewer.getTable().setLinesVisible(true);
		makeColumns(tableViewer);
		tableViewer.setContentProvider(new GenericContentProvider<BigDecimal>());
		tableViewer.setLabelProvider(new BigDecimalLabelProvider());
	}
	
	private void makeColumns(TableViewer tableViewer){
		TableViewerColumn totalIcms = new TableViewerColumn(tableViewer, SWT.CENTER);
		totalIcms.getColumn().setText(Messages.getMessage("invoice.total.icms.label"));
		totalIcms.getColumn().setWidth(200);
		totalIcms.getColumn().setResizable(true);
		totalIcms.getColumn().setMoveable(false);
		totalIcms.getColumn().setAlignment(SWT.CENTER);
		
		TableViewerColumn totalIcmsSub = new TableViewerColumn(tableViewer, SWT.CENTER);
		totalIcmsSub.getColumn().setText(Messages.getMessage("invoice.total.icmsSub.label"));
		totalIcmsSub.getColumn().setWidth(200);
		totalIcmsSub.getColumn().setResizable(true);
		totalIcmsSub.getColumn().setMoveable(false);
		totalIcmsSub.getColumn().setAlignment(SWT.CENTER);
		
		TableViewerColumn totalIpi = new TableViewerColumn(tableViewer, SWT.CENTER);
		totalIpi.getColumn().setText(Messages.getMessage("invoice.total.ipi.label"));
		totalIpi.getColumn().setWidth(200);
		totalIpi.getColumn().setResizable(true);
		totalIpi.getColumn().setMoveable(false);
		totalIpi.getColumn().setAlignment(SWT.CENTER);
		
		TableViewerColumn totalItems = new TableViewerColumn(tableViewer, SWT.CENTER);
		totalItems.getColumn().setText(Messages.getMessage("invoice.total.products.label"));
		totalItems.getColumn().setWidth(200);
		totalItems.getColumn().setResizable(true);
		totalItems.getColumn().setMoveable(false);
		totalItems.getColumn().setAlignment(SWT.CENTER);
		
		TableViewerColumn totalInvoice = new TableViewerColumn(tableViewer, SWT.CENTER);
		totalInvoice.getColumn().setText(Messages.getMessage("invoice.total.invoice.label"));
		totalInvoice.getColumn().setWidth(200);
		totalInvoice.getColumn().setResizable(true);
		totalInvoice.getColumn().setMoveable(false);
		totalInvoice.getColumn().setAlignment(SWT.CENTER);
	}
	
	public void setLayoutData(Object layoutData){
		this.container.setLayoutData(layoutData);
	}
	
	public void setInput(Map<Integer,BigDecimal> values){
		List<Map<Integer,BigDecimal>> rows = new ArrayList<Map<Integer,BigDecimal>>();
		rows.add(values);
		this.tableViewer.setInput(rows);
	}
	
	class BigDecimalLabelProvider extends LabelProvider implements ITableLabelProvider,ITableFontProvider,ITableColorProvider{

		@SuppressWarnings("unchecked")
		@Override
		public Color getForeground(Object element, int columnIndex) {
			Map<Integer,BigDecimal> map = (Map<Integer, BigDecimal>)element;
			BigDecimal value = map.get(columnIndex);
			if (value.doubleValue() >= 0D){
				return Colors.getColorBlue();
			}
			return Colors.getColorBlack();
		}

		@Override
		public Color getBackground(Object element, int columnIndex) {		
			return Colors.getColorWhite();
		}

		@Override
		public Font getFont(Object element, int columnIndex) {
			if (columnIndex == 4){
				return FontToolkit.getInstance().getTahoma(12, SWT.BOLD);
			}
			return FontToolkit.getInstance().getTahomaLabel();
		}

		@Override
		public Image getColumnImage(Object element, int columnIndex) {			
			return null;
		}

		@SuppressWarnings("unchecked")
		@Override
		public String getColumnText(Object element, int columnIndex) {
			Map<Integer,BigDecimal> map = (Map<Integer, BigDecimal>)element;
			try {
				return Formatter.getDecimal().mask((map.get(columnIndex)));
			}catch (BadFormatException ex){
			}
			return BigDecimal.ZERO.toString();
		}

		
		
	}
	
}
