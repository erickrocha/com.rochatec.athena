package com.rochatec.mongo.json;

import flexjson.ObjectFactory;
import flexjson.transformer.Transformer;

import java.util.Arrays;

/**
 * Created by epr on 03/10/14.
 */
public class JSONApiBuilder {

    private JSONApi instance;

    public JSONApiBuilder(){
        instance = new JSONApi();
    }

    @SuppressWarnings("rawtypes")
	public JSONApiBuilder withTransformer(final Transformer pTransformer, final Class pClass){
        instance.getTransformers().put(pClass,pTransformer);
        return this;
    }

    public JSONApiBuilder withTransformer(final Transformer pTransformer, final String pPropertyName){
        instance.getTransformers().put(pPropertyName,pTransformer);
        return this;
    }

    @SuppressWarnings("rawtypes")
	public JSONApiBuilder withObjectFactory(final ObjectFactory pObjectFactory, final Class pClass){
        instance.getFactories().put(pClass,pObjectFactory);
        return this;
    }

    public JSONApiBuilder withStructure(final String[] pStructure){
        instance.getStructure().addAll(Arrays.asList(pStructure));
        return this;
    }

    public JSONApiBuilder exclude(final String pExclusion){
        instance.getExclusions().add(pExclusion);
        return this;
    }

    public JSONApi build(){
        return instance;
    }
}
