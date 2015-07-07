package com.rochatec.pos.athena.app.service;

import com.rochatec.pos.athena.app.IAppConfig;
import com.rochatec.pos.athena.model.BoxStatus;
import org.eclipse.jface.preference.PreferenceStore;

import java.io.IOException;

/**
 * Created by epr on 06/07/15.
 */
public class PreferenceService {

    private PreferenceStore preferenceStore;

    public PreferenceService() {

    }

    public void init(){
        preferenceStore = new PreferenceStore("atheaPos.properties");
        try{
            preferenceStore.load();
        }catch (IOException ex){
            preferenceStore.setDefault(IAppConfig.BOX_STATUS, BoxStatus.CLOSED.name());
        }
    }

    public void setValue(String key,String value){
        preferenceStore.setValue(key,value);
    }
    public void setValue(String key,Integer value){
        preferenceStore.setValue(key,value);
    }

    public void setValue(String key,Long value){
        preferenceStore.setValue(key,value);
    }
    public void setValue(String key,Double value){
        preferenceStore.setValue(key,value);
    }
    public void setValue(String key,Float value){
        preferenceStore.setValue(key,value);
    }
    public void setValue(String key,Boolean value){
        preferenceStore.setValue(key,value);
    }

    public Object getObject(String key){
        return preferenceStore.getString(key);
    }

    public String getString(String key){
        return preferenceStore.getString(key);
    }

    public Integer getInteger(String key){
        return preferenceStore.getInt(key);
    }
    public Float getFloat(String key){
        return preferenceStore.getFloat(key);
    }
    public Double getDouble(String key){
        return preferenceStore.getDouble(key);
    }
    public Boolean getBoolean(String key){
        return preferenceStore.getBoolean(key);
    }

    public void save(){
        try {
            preferenceStore.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
