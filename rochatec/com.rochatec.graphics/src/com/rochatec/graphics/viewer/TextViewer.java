package com.rochatec.graphics.viewer;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.util.Policy;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

public class TextViewer extends Viewer {

	private Object input;
	private Text text;
	
	private ListenerList postSelectionChangedListeners = new ListenerList();

	/**
	 * This viewer's content provider, or <code>null</code> if none.
	 */
	private IContentProvider contentProvider = null;

	private IBaseLabelProvider labelProvider = null;
	
	private final ILabelProviderListener labelProviderListener = new ILabelProviderListener() {
    	private boolean logWhenDisposed = true; // initially true, set to false
        
        public void labelProviderChanged(LabelProviderChangedEvent event) {
        	Control control = getControl();
        	if (control == null || control.isDisposed()) {
    			if (logWhenDisposed) {
    				String message = "Ignored labelProviderChanged notification because control is diposed." + //$NON-NLS-1$
    						" This indicates a potential memory leak."; //$NON-NLS-1$    				
    				Policy.getLog().log(
    						new Status(IStatus.WARNING, Policy.JFACE, message,
    								new RuntimeException()));
    			}
        		return;
        	}
        	handleLabelProviderChanged(event);
        }
    };
    
    public void addPostSelectionChangedListener(ISelectionChangedListener listener) {
		postSelectionChangedListeners.add(listener);
	}
    
    public void removePostSelectionChangedListener(ISelectionChangedListener listener) {
		postSelectionChangedListeners.remove(listener);
	}

	public TextViewer(Text text) {
		this.text = text;
	}
	
	public void setLayoutData(Object layoutData){
		this.text.setLayoutData(layoutData);
	}
	
	public void addKeyListener(KeyListener listener){
		this.text.addKeyListener(listener);
	}
	
	public void removeKeyListener(KeyListener listener){
		this.text.removeKeyListener(listener);
	}

	@Override
	public Control getControl() {
		return text;
	}

	@Override
	public Object getInput() {
		return input;
	}

	@Override
	public ISelection getSelection() {
		if (input != null)
			return new StructuredSelection(input);
		return new StructuredSelection();
	}

	@Override
	public void refresh() {
		if (input != null)
			refresh(input);
	}
	
	public void refresh(final Object element) {
		StructuredSelection singleSelection = new StructuredSelection(element);
		updateSelection(singleSelection);
	}
	
	protected void updateSelection(ISelection selection) {
		SelectionChangedEvent event = new SelectionChangedEvent(this, selection);
		fireSelectionChanged(event);
		firePostSelectionChanged(event);
	}	
	
	protected void internalRefresh(Object element){
		Text control = (Text)getControl();
		ILabelProvider labelProvider = (ILabelProvider) getLabelProvider();
		control.setText(labelProvider.getText(element));
	}
	
	protected void firePostSelectionChanged(final SelectionChangedEvent event) {
		Object[] listeners = postSelectionChangedListeners.getListeners();
		for (int i = 0; i < listeners.length; ++i) {
			final ISelectionChangedListener l = (ISelectionChangedListener) listeners[i];
			SafeRunnable.run(new SafeRunnable() {
				public void run() {
					l.selectionChanged(event);
				}
			});
		}
	}

	@Override
	public void setInput(Object input) {		
        Object oldInput = getInput();
        contentProvider.inputChanged(this, oldInput, input);
        this.input = input;

        // call input hook
        inputChanged(this.input, oldInput);
	}

	@Override
	public void setSelection(ISelection selection, boolean reveal) {
		internalRefresh(((IStructuredSelection)selection).getFirstElement());
		setInput(((IStructuredSelection)selection).getFirstElement());
		updateSelection(selection);		
	}

	public IContentProvider getContentProvider() {
		return contentProvider;
	}

	public IBaseLabelProvider getLabelProvider() {
		if (labelProvider == null) {
			labelProvider = new LabelProvider();
		}
		return labelProvider;
	}
	
	protected void handleDispose(DisposeEvent event) {
        if (contentProvider != null) {
            contentProvider.inputChanged(this, getInput(), null);
            contentProvider.dispose();
            contentProvider = null;
        }
        if (labelProvider != null) {
            labelProvider.removeListener(labelProviderListener);
            labelProvider.dispose();
            labelProvider = null;
        }
        postSelectionChangedListeners.clear();
        input = null;
    }
	
	protected void handleLabelProviderChanged(LabelProviderChangedEvent event) {
        labelProviderChanged();
    }
	
	protected void hookControl(Control control) {
        control.addDisposeListener(new DisposeListener() {
            public void widgetDisposed(DisposeEvent event) {
                handleDispose(event);
            }
        });
    }
	
	protected void labelProviderChanged() {
        refresh();
    }
	
	public void setContentProvider(IContentProvider contentProvider) {
        Assert.isNotNull(contentProvider);
        IContentProvider oldContentProvider = this.contentProvider;
        this.contentProvider = contentProvider;
        if (oldContentProvider != null) {
            Object currentInput = getInput();
            oldContentProvider.inputChanged(this, currentInput, null);
            oldContentProvider.dispose();
            contentProvider.inputChanged(this, null, currentInput);
            refresh();
        }
    }
	
	public void setLabelProvider(IBaseLabelProvider labelProvider) {
        IBaseLabelProvider oldProvider = this.labelProvider;
        if (labelProvider == oldProvider) {
            return;
        }
        if (oldProvider != null) {
            oldProvider.removeListener(this.labelProviderListener);
        }
        this.labelProvider = labelProvider;
        if (labelProvider != null) {
            labelProvider.addListener(this.labelProviderListener);
        }
        refresh();

        // Dispose old provider after refresh, so that items never refer to stale images.
        if (oldProvider != null) {
    		internalDisposeLabelProvider(oldProvider);
        }
    }

	void internalDisposeLabelProvider(IBaseLabelProvider oldProvider) {
		oldProvider.dispose();
	}

}
