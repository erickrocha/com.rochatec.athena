package com.rochatec.athena.util;

import java.util.Map;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.ChangeEvent;
import org.eclipse.core.databinding.observable.IChangeListener;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.nebula.jface.tablecomboviewer.TableComboViewer;
import org.eclipse.nebula.widgets.datechooser.DateChooserCombo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;

import com.rochatec.athena.address.viewer.AddressViewer;
import com.rochatec.framework.bind.Editable;
import com.rochatec.graphics.bind.converter.BigDecimalToStringConverter;
import com.rochatec.graphics.bind.converter.DateToStringConverter;
import com.rochatec.graphics.bind.converter.MaskToStringConverter;
import com.rochatec.graphics.bind.converter.StringToBigDecimalConverter;
import com.rochatec.graphics.bind.converter.StringToDateConverter;
import com.rochatec.graphics.bind.converter.StringToMaskConverter;
import com.rochatec.graphics.bind.converter.StringToWeightConverter;
import com.rochatec.graphics.bind.converter.WeightToStringConverter;
import com.rochatec.graphics.gui.DateFormatedText;
import com.rochatec.graphics.gui.MaskedText;
import com.rochatec.graphics.gui.TextField;
import com.rochatec.graphics.gui.TimeFormatedText;
import com.rochatec.graphics.nebula.DateChooserComboObservableValue;
import com.rochatec.graphics.viewer.IdAndTextViewer;
import com.rochatec.graphics.viewer.TextViewer;

public class DataBindingFactory<T> {
	
	private T object;
	private Editable editable;
	
	public DataBindingFactory(T object,Editable editable) {
		this.object = object;
		this.editable = editable;
	}
	
	public void bind(Map<String,Object> map){
		DataBindingContext ctx = new DataBindingContext();
		for (String key : map.keySet()){
			Object component = map.get(key);			  
			if (component instanceof Text){
				Text text = (Text)component;
				IObservableValue widgetValue = WidgetProperties.text(SWT.Modify).observe(text);
				IObservableValue modelValue = PojoProperties.value(object.getClass(),key).observe(object);
				ctx.bindValue(widgetValue, modelValue);
			}else if (component instanceof TextField){
				System.out.println(key);
				TextField field = (TextField)component;
				IObservableValue widgetValue = WidgetProperties.text(SWT.Modify).observe(field.getWidget());				
				IObservableValue modelValue = PojoProperties.value(object.getClass(),key).observe(object);
				
				UpdateValueStrategy targetToModel = new UpdateValueStrategy();
				UpdateValueStrategy modelToTarget = new UpdateValueStrategy();
				switch (field.getTypePattern()) {
				case ATHENA.PATTERN_SOCIALSECURITY:
					targetToModel.setConverter(new MaskToStringConverter(Formatter.getSocialSecurity()));
					modelToTarget.setConverter(new StringToMaskConverter(Formatter.getSocialSecurity()));
					break;
				case ATHENA.PATTERN_ZIPCODE:
					targetToModel.setConverter(new MaskToStringConverter(Formatter.getZipCode()));
					modelToTarget.setConverter(new StringToMaskConverter(Formatter.getZipCode()));
					break;
				case ATHENA.PATTERN_PHONE:
					targetToModel.setConverter(new MaskToStringConverter(Formatter.getPhone()));
					modelToTarget.setConverter(new StringToMaskConverter(Formatter.getPhone()));
					break;
				case ATHENA.PATTERN_BIGDECIMAL:
					targetToModel.setConverter(new StringToBigDecimalConverter());
					modelToTarget.setConverter(new BigDecimalToStringConverter());
					break;
				case ATHENA.PATTERN_DATE:
					targetToModel.setConverter(new StringToDateConverter());
					modelToTarget.setConverter(new DateToStringConverter());
					break;
				case ATHENA.PATTERN_WEIGHT:
					targetToModel.setConverter(new StringToWeightConverter());
					modelToTarget.setConverter(new WeightToStringConverter());
					break;
				default:
					targetToModel.setConverter(new MaskToStringConverter(Formatter.getNone()));
					modelToTarget.setConverter(new StringToMaskConverter(Formatter.getNone()));
					break;
				}
				ctx.bindValue(widgetValue, modelValue,targetToModel,modelToTarget);
			}else if (component instanceof DateChooserCombo){
				DateChooserCombo field = (DateChooserCombo)component;				
				DateChooserComboObservableValue widgetValue = new DateChooserComboObservableValue(field, SWT.Modify);
				IObservableValue modelValue = PojoProperties.value(object.getClass(),key).observe(object);
				ctx.bindValue(widgetValue, modelValue);
			}else if (component instanceof MaskedText){
				MaskedText maskedText = (MaskedText)component;
				IObservableValue widgetValue = WidgetProperties.text(SWT.Modify).observe(maskedText.getComponent());
				IObservableValue modelValue = PojoProperties.value(object.getClass(),key).observe(object);
				ctx.bindValue(widgetValue, modelValue);
			}else if (component instanceof DateFormatedText){
				DateFormatedText field = (DateFormatedText)component;
				IObservableValue widgetValue = WidgetProperties.text(SWT.Modify).observe(field);
				UpdateValueStrategy targetToModel = new UpdateValueStrategy();
				targetToModel.setConverter(new StringToDateConverter());
				UpdateValueStrategy modelToTarget = new UpdateValueStrategy();
				modelToTarget.setConverter(new DateToStringConverter());
				IObservableValue modelValue = PojoProperties.value(object.getClass(),key).observe(object);
				ctx.bindValue(widgetValue, modelValue,targetToModel,modelToTarget);
			}else if (component instanceof TimeFormatedText){
				TimeFormatedText field = (TimeFormatedText)component;
				IObservableValue widgetValue = WidgetProperties.text(SWT.Modify).observe(field);
				IObservableValue modelValue = PojoProperties.value(object.getClass(),key).observe(object);
				ctx.bindValue(widgetValue, modelValue);
			}else if (component instanceof TableComboViewer){
				TableComboViewer viewer = (TableComboViewer)component;
				IObservableValue widgetValue = ViewersObservables.observeSingleSelection(viewer);
				IObservableValue modelValue = PojoProperties.value(object.getClass(),key).observe(object);
				ctx.bindValue(widgetValue, modelValue);
			}else if (component instanceof AddressViewer){
				AddressViewer viewer = (AddressViewer)component;
				IObservableValue widgetValue = ViewersObservables.observeSingleSelection(viewer);
				IObservableValue modelValue = PojoProperties.value(object.getClass(),key).observe(object);
				ctx.bindValue(widgetValue, modelValue);
			}else if (component instanceof Button){
				Button button = (Button)component;
				IObservableValue widgetValue = WidgetProperties.selection().observe(button);
				IObservableValue modelValue = PojoProperties.value(object.getClass(),key).observe(object);
				ctx.bindValue(widgetValue, modelValue);
			}else if (component instanceof TextViewer){
				TextViewer viewer = (TextViewer)component;
				IObservableValue widgetValue = ViewersObservables.observeSingleSelection(viewer);
				IObservableValue modelValue = PojoProperties.value(object.getClass(),key).observe(object);
				ctx.bindValue(widgetValue, modelValue);
			}else if (component instanceof ComboViewer){
				ComboViewer viewer = (ComboViewer)component;
				IObservableValue widgetValue = ViewersObservables.observeSingleSelection(viewer);
				IObservableValue modelValue = PojoProperties.value(object.getClass(),key).observe(object);
				ctx.bindValue(widgetValue, modelValue);
			}else if (component instanceof IdAndTextViewer){
				IdAndTextViewer viewer = (IdAndTextViewer)component;
				IObservableValue widgetValue = ViewersObservables.observeSingleSelection(viewer);
				IObservableValue modelValue = PojoProperties.value(object.getClass(),key).observe(object);
				ctx.bindValue(widgetValue, modelValue);
			}
		}
		IObservableList providers = ctx.getValidationStatusProviders();
		for (Object o : providers){
			Binding binding = (Binding)o;
			binding.getTarget().addChangeListener(new IChangeListener() {
				
				@Override
				public void handleChange(ChangeEvent event) {
					if (!editable.isDirty()){
						editable.setDirty(true);
					}					
				}
			});
		}
				
	}
	
}
