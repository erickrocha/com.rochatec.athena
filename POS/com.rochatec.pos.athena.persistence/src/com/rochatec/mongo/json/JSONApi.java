package com.rochatec.mongo.json;

import com.rochatec.mongo.json.transformer.DateBlankTransformer;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import flexjson.ObjectFactory;
import flexjson.transformer.AbstractTransformer;
import flexjson.transformer.Transformer;

import java.util.*;

/**
 * Created by epr on 03/10/14.
 */
public class JSONApi {

    /** */
    private static final String[] DEFAULT_EXCLUDES  = {
            "*.errors",
            "*.class",
            "*.metaClass",
            "*.attached",
            "*.properties",
            "*.readOnlyBeforeAttachedToSession",
            "*.handler",
            "*.hibernateLazyInitializer",
            "*.beforeInsert",
            "*.beforeUpdate",
            "*.beforeDelete",
            "*.dbo",
            "dbo"
    };

    private final Map<Object, Transformer> transformers = new HashMap<Object, Transformer>();
    private final Map<Object, ObjectFactory> factories = new HashMap<Object, ObjectFactory>();
    private final Set<String> structure = new HashSet<String>();
    private final List<String> exclusions = new ArrayList<String>();

    private JSONSerializer serializer;
    @SuppressWarnings("rawtypes")
	private JSONDeserializer deserializer;

    public static JSONApiBuilder builder(){
        return new JSONApiBuilder();
    }

    public static JSONApiBuilder defaultBuilder(){
        return new JSONApiBuilder().
                withTransformer(new DateBlankTransformer("dd/MM/yyyy"), Date.class).
                withObjectFactory(new DateBlankTransformer("dd/MM/yyyy"), Date.class);
    }

    public String serialize(final Object pObject, boolean excludesNull){
        refreshSerializerInstance();
        if(excludesNull) {
            serializer.transform(new ExcludeTransformer(), void.class);
        }
        return serializer.deepSerialize(pObject);
    }

    public String serialize(final Object pObject){
        return serialize(pObject,false);
    }

    @SuppressWarnings("rawtypes")
	public Object deserialize(final String pSource,Class pTarget){
        refreshDeserializerInstance();
        return deserializer.deserialize(pSource, pTarget);
    }

    void refreshSerializerInstance(){
        JSONSerializer newSerializer = new JSONSerializer();

        if(structure != null  && structure.isEmpty()){
            String[] others = {"*.chave", "chave", "href", "*.href", "quantidadeRegistros"};
            String[] excludes = {"*"};
            newSerializer.include((String[]) structure.toArray()).include(others).exclude(excludes);
        }else{
            List<String> allExclusions = Arrays.asList(DEFAULT_EXCLUDES);
            if(exclusions != null && !exclusions.isEmpty()) {
                allExclusions.addAll(exclusions);
            }
            newSerializer.exclude((String[]) allExclusions.toArray());
        }

        if(transformers!= null && !transformers.isEmpty()){
            for (Object o : transformers.keySet()){
                newSerializer.transform(transformers.get(o),o.getClass());
            }
        }

        this.serializer = newSerializer;
    }

    @SuppressWarnings("rawtypes")
	void refreshDeserializerInstance(){
        JSONDeserializer newDeserializer = new JSONDeserializer();

        if(factories != null && !factories.isEmpty()){
            for (Object o : factories.keySet()){
                newDeserializer.use((String)o,factories.get(o));
            }
        }

        deserializer = newDeserializer;
    }

    public Set<String> getStructure() {
        return structure;
    }

    public Map<Object, ObjectFactory> getFactories() {
        return factories;
    }

    public Map<Object, Transformer> getTransformers() {
        return transformers;
    }

    public List<String> getExclusions() {
        return exclusions;
    }


    public class ExcludeTransformer extends AbstractTransformer {

        @Override
        public Boolean isInline() {
            return true;
        }

        @Override
        public void transform(Object object) {
            // Do nothing, null objects are not serialized.
            return;
        }
    }
}
