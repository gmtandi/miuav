package org.codehaus.jackson.map.deser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.deser.impl.BeanPropertyMap;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;

public class BeanDeserializerBuilder {
    protected SettableAnyProperty _anySetter;
    protected HashMap<String, SettableBeanProperty> _backRefProperties;
    protected final BasicBeanDescription _beanDesc;
    protected CreatorContainer _creators;
    protected HashSet<String> _ignorableProps;
    protected boolean _ignoreAllUnknown;
    protected final HashMap<String, SettableBeanProperty> _properties;

    public BeanDeserializerBuilder(BasicBeanDescription basicBeanDescription) {
        this._properties = new HashMap();
        this._beanDesc = basicBeanDescription;
    }

    public void addBackReferenceProperty(String str, SettableBeanProperty settableBeanProperty) {
        if (this._backRefProperties == null) {
            this._backRefProperties = new HashMap(4);
        }
        this._backRefProperties.put(str, settableBeanProperty);
    }

    public void addIgnorable(String str) {
        if (this._ignorableProps == null) {
            this._ignorableProps = new HashSet();
        }
        this._ignorableProps.add(str);
    }

    public void addOrReplaceProperty(SettableBeanProperty settableBeanProperty, boolean z) {
        this._properties.put(settableBeanProperty.getName(), settableBeanProperty);
    }

    public void addProperty(SettableBeanProperty settableBeanProperty) {
        SettableBeanProperty settableBeanProperty2 = (SettableBeanProperty) this._properties.put(settableBeanProperty.getName(), settableBeanProperty);
        if (settableBeanProperty2 != null && settableBeanProperty2 != settableBeanProperty) {
            throw new IllegalArgumentException("Duplicate property '" + settableBeanProperty.getName() + "' for " + this._beanDesc.getType());
        }
    }

    public JsonDeserializer<?> build(BeanProperty beanProperty) {
        BeanPropertyMap beanPropertyMap = new BeanPropertyMap(this._properties.values());
        beanPropertyMap.assignIndexes();
        return new BeanDeserializer(this._beanDesc.getClassInfo(), this._beanDesc.getType(), beanProperty, this._creators, beanPropertyMap, this._backRefProperties, this._ignorableProps, this._ignoreAllUnknown, this._anySetter);
    }

    public Iterator<SettableBeanProperty> getProperties() {
        return this._properties.values().iterator();
    }

    public boolean hasProperty(String str) {
        return this._properties.containsKey(str);
    }

    public SettableBeanProperty removeProperty(String str) {
        return (SettableBeanProperty) this._properties.remove(str);
    }

    public void setAnySetter(SettableAnyProperty settableAnyProperty) {
        if (this._anySetter == null || settableAnyProperty == null) {
            this._anySetter = settableAnyProperty;
            return;
        }
        throw new IllegalStateException("_anySetter already set to non-null");
    }

    public void setCreators(CreatorContainer creatorContainer) {
        this._creators = creatorContainer;
    }

    public void setIgnoreUnknownProperties(boolean z) {
        this._ignoreAllUnknown = z;
    }
}
