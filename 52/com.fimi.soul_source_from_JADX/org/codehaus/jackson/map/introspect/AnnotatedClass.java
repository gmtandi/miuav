package org.codehaus.jackson.map.introspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.ClassIntrospector.MixInResolver;
import org.codehaus.jackson.map.util.Annotations;
import org.codehaus.jackson.map.util.ArrayBuilders;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.smile.SmileConstants;

public final class AnnotatedClass extends Annotated {
    private static final AnnotationMap[] NO_ANNOTATION_MAPS;
    protected final AnnotationIntrospector _annotationIntrospector;
    protected final Class<?> _class;
    protected AnnotationMap _classAnnotations;
    protected List<AnnotatedConstructor> _constructors;
    protected List<AnnotatedMethod> _creatorMethods;
    protected AnnotatedConstructor _defaultConstructor;
    protected List<AnnotatedField> _fields;
    protected List<AnnotatedField> _ignoredFields;
    protected List<AnnotatedMethod> _ignoredMethods;
    protected AnnotatedMethodMap _memberMethods;
    protected final MixInResolver _mixInResolver;
    protected final Class<?> _primaryMixIn;
    protected final Collection<Class<?>> _superTypes;

    static {
        NO_ANNOTATION_MAPS = new AnnotationMap[0];
    }

    private AnnotatedClass(Class<?> cls, List<Class<?>> list, AnnotationIntrospector annotationIntrospector, MixInResolver mixInResolver) {
        this._class = cls;
        this._superTypes = list;
        this._annotationIntrospector = annotationIntrospector;
        this._mixInResolver = mixInResolver;
        this._primaryMixIn = this._mixInResolver == null ? null : this._mixInResolver.findMixInClassFor(this._class);
    }

    private AnnotationMap _emptyAnnotationMap() {
        return new AnnotationMap();
    }

    private AnnotationMap[] _emptyAnnotationMaps(int i) {
        if (i == 0) {
            return NO_ANNOTATION_MAPS;
        }
        AnnotationMap[] annotationMapArr = new AnnotationMap[i];
        for (int i2 = 0; i2 < i; i2++) {
            annotationMapArr[i2] = _emptyAnnotationMap();
        }
        return annotationMapArr;
    }

    private boolean _isIncludableField(Field field) {
        if (field.isSynthetic()) {
            return false;
        }
        int modifiers = field.getModifiers();
        return (Modifier.isStatic(modifiers) || Modifier.isTransient(modifiers)) ? false : true;
    }

    public static AnnotatedClass construct(Class<?> cls, AnnotationIntrospector annotationIntrospector, MixInResolver mixInResolver) {
        AnnotatedClass annotatedClass = new AnnotatedClass(cls, ClassUtil.findSuperTypes(cls, null), annotationIntrospector, mixInResolver);
        annotatedClass.resolveClassAnnotations();
        return annotatedClass;
    }

    public static AnnotatedClass constructWithoutSuperTypes(Class<?> cls, AnnotationIntrospector annotationIntrospector, MixInResolver mixInResolver) {
        AnnotatedClass annotatedClass = new AnnotatedClass(cls, Collections.emptyList(), annotationIntrospector, mixInResolver);
        annotatedClass.resolveClassAnnotations();
        return annotatedClass;
    }

    protected void _addClassMixIns(AnnotationMap annotationMap, Class<?> cls) {
        if (this._mixInResolver != null) {
            _addClassMixIns(annotationMap, cls, this._mixInResolver.findMixInClassFor(cls));
        }
    }

    protected void _addClassMixIns(AnnotationMap annotationMap, Class<?> cls, Class<?> cls2) {
        if (cls2 != null) {
            for (Annotation annotation : cls2.getDeclaredAnnotations()) {
                if (this._annotationIntrospector.isHandled(annotation)) {
                    annotationMap.addIfNotPresent(annotation);
                }
            }
            for (Class declaredAnnotations : ClassUtil.findSuperTypes(cls2, cls)) {
                for (Annotation annotation2 : declaredAnnotations.getDeclaredAnnotations()) {
                    if (this._annotationIntrospector.isHandled(annotation2)) {
                        annotationMap.addIfNotPresent(annotation2);
                    }
                }
            }
        }
    }

    protected void _addConstructorMixIns(Class<?> cls) {
        int size = this._constructors == null ? 0 : this._constructors.size();
        MemberKey[] memberKeyArr = null;
        for (Constructor constructor : cls.getDeclaredConstructors()) {
            switch (constructor.getParameterTypes().length) {
                case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                    if (this._defaultConstructor == null) {
                        break;
                    }
                    _addMixOvers(constructor, this._defaultConstructor, false);
                    break;
                default:
                    MemberKey[] memberKeyArr2;
                    if (memberKeyArr == null) {
                        memberKeyArr2 = new MemberKey[size];
                        for (int i = 0; i < size; i++) {
                            memberKeyArr2[i] = new MemberKey(((AnnotatedConstructor) this._constructors.get(i)).getAnnotated());
                        }
                    } else {
                        memberKeyArr2 = memberKeyArr;
                    }
                    MemberKey memberKey = new MemberKey(constructor);
                    for (int i2 = 0; i2 < size; i2++) {
                        if (memberKey.equals(memberKeyArr2[i2])) {
                            _addMixOvers(constructor, (AnnotatedConstructor) this._constructors.get(i2), true);
                            memberKeyArr = memberKeyArr2;
                            break;
                        }
                    }
                    memberKeyArr = memberKeyArr2;
                    break;
            }
        }
    }

    protected void _addFactoryMixIns(Class<?> cls) {
        MemberKey[] memberKeyArr = null;
        int size = this._creatorMethods.size();
        for (Method method : cls.getDeclaredMethods()) {
            if (Modifier.isStatic(method.getModifiers()) && method.getParameterTypes().length != 0) {
                MemberKey[] memberKeyArr2;
                if (memberKeyArr == null) {
                    memberKeyArr2 = new MemberKey[size];
                    for (int i = 0; i < size; i++) {
                        memberKeyArr2[i] = new MemberKey(((AnnotatedMethod) this._creatorMethods.get(i)).getAnnotated());
                    }
                } else {
                    memberKeyArr2 = memberKeyArr;
                }
                MemberKey memberKey = new MemberKey(method);
                for (int i2 = 0; i2 < size; i2++) {
                    if (memberKey.equals(memberKeyArr2[i2])) {
                        _addMixOvers(method, (AnnotatedMethod) this._creatorMethods.get(i2), true);
                        memberKeyArr = memberKeyArr2;
                        break;
                    }
                }
                memberKeyArr = memberKeyArr2;
            }
        }
    }

    protected void _addFieldMixIns(Class<?> cls, Map<String, AnnotatedField> map) {
        for (Field field : cls.getDeclaredFields()) {
            if (_isIncludableField(field)) {
                AnnotatedField annotatedField = (AnnotatedField) map.get(field.getName());
                if (annotatedField != null) {
                    for (Annotation annotation : field.getDeclaredAnnotations()) {
                        if (this._annotationIntrospector.isHandled(annotation)) {
                            annotatedField.addOrOverride(annotation);
                        }
                    }
                }
            }
        }
    }

    protected void _addFields(Map<String, AnnotatedField> map, Class<?> cls) {
        Class superclass = cls.getSuperclass();
        if (superclass != null) {
            _addFields(map, superclass);
            for (Field field : cls.getDeclaredFields()) {
                if (_isIncludableField(field)) {
                    map.put(field.getName(), _constructField(field));
                }
            }
            if (this._mixInResolver != null) {
                superclass = this._mixInResolver.findMixInClassFor(cls);
                if (superclass != null) {
                    _addFieldMixIns(superclass, map);
                }
            }
        }
    }

    protected void _addMemberMethods(Class<?> cls, MethodFilter methodFilter, AnnotatedMethodMap annotatedMethodMap, Class<?> cls2, AnnotatedMethodMap annotatedMethodMap2) {
        if (cls2 != null) {
            _addMethodMixIns(methodFilter, annotatedMethodMap, cls2, annotatedMethodMap2);
        }
        if (cls != null) {
            for (Method method : cls.getDeclaredMethods()) {
                if (_isIncludableMethod(method, methodFilter)) {
                    AnnotatedMethod find = annotatedMethodMap.find(method);
                    if (find == null) {
                        find = _constructMethod(method);
                        annotatedMethodMap.add(find);
                        AnnotatedMethod remove = annotatedMethodMap2.remove(method);
                        if (remove != null) {
                            _addMixOvers(remove.getAnnotated(), find, false);
                        }
                    } else {
                        _addMixUnders(method, find);
                        if (find.getDeclaringClass().isInterface() && !method.getDeclaringClass().isInterface()) {
                            annotatedMethodMap.add(find.withMethod(method));
                        }
                    }
                }
            }
        }
    }

    protected void _addMethodMixIns(MethodFilter methodFilter, AnnotatedMethodMap annotatedMethodMap, Class<?> cls, AnnotatedMethodMap annotatedMethodMap2) {
        for (Method method : cls.getDeclaredMethods()) {
            if (_isIncludableMethod(method, methodFilter)) {
                AnnotatedMethod find = annotatedMethodMap.find(method);
                if (find != null) {
                    _addMixUnders(method, find);
                } else {
                    annotatedMethodMap2.add(_constructMethod(method));
                }
            }
        }
    }

    protected void _addMixOvers(Constructor<?> constructor, AnnotatedConstructor annotatedConstructor, boolean z) {
        for (Annotation annotation : constructor.getDeclaredAnnotations()) {
            if (this._annotationIntrospector.isHandled(annotation)) {
                annotatedConstructor.addOrOverride(annotation);
            }
        }
        if (z) {
            Annotation[][] parameterAnnotations = constructor.getParameterAnnotations();
            int length = parameterAnnotations.length;
            for (int i = 0; i < length; i++) {
                for (Annotation addOrOverrideParam : parameterAnnotations[i]) {
                    annotatedConstructor.addOrOverrideParam(i, addOrOverrideParam);
                }
            }
        }
    }

    protected void _addMixOvers(Method method, AnnotatedMethod annotatedMethod, boolean z) {
        for (Annotation annotation : method.getDeclaredAnnotations()) {
            if (this._annotationIntrospector.isHandled(annotation)) {
                annotatedMethod.addOrOverride(annotation);
            }
        }
        if (z) {
            Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            int length = parameterAnnotations.length;
            for (int i = 0; i < length; i++) {
                for (Annotation addOrOverrideParam : parameterAnnotations[i]) {
                    annotatedMethod.addOrOverrideParam(i, addOrOverrideParam);
                }
            }
        }
    }

    protected void _addMixUnders(Method method, AnnotatedMethod annotatedMethod) {
        for (Annotation annotation : method.getDeclaredAnnotations()) {
            if (this._annotationIntrospector.isHandled(annotation)) {
                annotatedMethod.addIfNotPresent(annotation);
            }
        }
    }

    protected AnnotationMap _collectRelevantAnnotations(Annotation[] annotationArr) {
        AnnotationMap annotationMap = new AnnotationMap();
        if (annotationArr != null) {
            for (Annotation annotation : annotationArr) {
                if (this._annotationIntrospector.isHandled(annotation)) {
                    annotationMap.add(annotation);
                }
            }
        }
        return annotationMap;
    }

    protected AnnotationMap[] _collectRelevantAnnotations(Annotation[][] annotationArr) {
        int length = annotationArr.length;
        AnnotationMap[] annotationMapArr = new AnnotationMap[length];
        for (int i = 0; i < length; i++) {
            annotationMapArr[i] = _collectRelevantAnnotations(annotationArr[i]);
        }
        return annotationMapArr;
    }

    protected AnnotatedConstructor _constructConstructor(Constructor<?> constructor, boolean z) {
        if (this._annotationIntrospector == null) {
            return new AnnotatedConstructor(constructor, _emptyAnnotationMap(), _emptyAnnotationMaps(constructor.getParameterTypes().length));
        }
        return new AnnotatedConstructor(constructor, _collectRelevantAnnotations(constructor.getDeclaredAnnotations()), z ? null : _collectRelevantAnnotations(constructor.getParameterAnnotations()));
    }

    protected AnnotatedMethod _constructCreatorMethod(Method method) {
        return this._annotationIntrospector == null ? new AnnotatedMethod(method, _emptyAnnotationMap(), _emptyAnnotationMaps(method.getParameterTypes().length)) : new AnnotatedMethod(method, _collectRelevantAnnotations(method.getDeclaredAnnotations()), _collectRelevantAnnotations(method.getParameterAnnotations()));
    }

    protected AnnotatedField _constructField(Field field) {
        return this._annotationIntrospector == null ? new AnnotatedField(field, _emptyAnnotationMap()) : new AnnotatedField(field, _collectRelevantAnnotations(field.getDeclaredAnnotations()));
    }

    protected AnnotatedMethod _constructMethod(Method method) {
        return this._annotationIntrospector == null ? new AnnotatedMethod(method, _emptyAnnotationMap(), null) : new AnnotatedMethod(method, _collectRelevantAnnotations(method.getDeclaredAnnotations()), null);
    }

    protected boolean _isIncludableMethod(Method method, MethodFilter methodFilter) {
        return ((methodFilter != null && !methodFilter.includeMethod(method)) || method.isSynthetic() || method.isBridge()) ? false : true;
    }

    public Iterable<AnnotatedField> fields() {
        return this._fields == null ? Collections.emptyList() : this._fields;
    }

    public AnnotatedMethod findMethod(String str, Class<?>[] clsArr) {
        return this._memberMethods.find(str, clsArr);
    }

    public Class<?> getAnnotated() {
        return this._class;
    }

    public <A extends Annotation> A getAnnotation(Class<A> cls) {
        return this._classAnnotations == null ? null : this._classAnnotations.get(cls);
    }

    public Annotations getAnnotations() {
        return this._classAnnotations;
    }

    public List<AnnotatedConstructor> getConstructors() {
        return this._constructors == null ? Collections.emptyList() : this._constructors;
    }

    public AnnotatedConstructor getDefaultConstructor() {
        return this._defaultConstructor;
    }

    public int getFieldCount() {
        return this._fields == null ? 0 : this._fields.size();
    }

    public Type getGenericType() {
        return this._class;
    }

    public int getMemberMethodCount() {
        return this._memberMethods.size();
    }

    public int getModifiers() {
        return this._class.getModifiers();
    }

    public String getName() {
        return this._class.getName();
    }

    public Class<?> getRawType() {
        return this._class;
    }

    public List<AnnotatedMethod> getStaticMethods() {
        return this._creatorMethods == null ? Collections.emptyList() : this._creatorMethods;
    }

    public boolean hasAnnotations() {
        return this._classAnnotations.size() > 0;
    }

    public Iterable<AnnotatedField> ignoredFields() {
        return this._ignoredFields == null ? Collections.emptyList() : this._ignoredFields;
    }

    public Iterable<AnnotatedMethod> ignoredMemberMethods() {
        return this._ignoredMethods == null ? Collections.emptyList() : this._ignoredMethods;
    }

    public Iterable<AnnotatedMethod> memberMethods() {
        return this._memberMethods;
    }

    protected void resolveClassAnnotations() {
        this._classAnnotations = new AnnotationMap();
        if (this._primaryMixIn != null) {
            _addClassMixIns(this._classAnnotations, this._class, this._primaryMixIn);
        }
        for (Annotation annotation : this._class.getDeclaredAnnotations()) {
            if (this._annotationIntrospector.isHandled(annotation)) {
                this._classAnnotations.addIfNotPresent(annotation);
            }
        }
        for (Class cls : this._superTypes) {
            _addClassMixIns(this._classAnnotations, cls);
            for (Annotation annotation2 : cls.getDeclaredAnnotations()) {
                if (this._annotationIntrospector.isHandled(annotation2)) {
                    this._classAnnotations.addIfNotPresent(annotation2);
                }
            }
        }
        _addClassMixIns(this._classAnnotations, Object.class);
    }

    public void resolveCreators(boolean z) {
        int size;
        this._constructors = null;
        for (Constructor constructor : this._class.getDeclaredConstructors()) {
            switch (constructor.getParameterTypes().length) {
                case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                    this._defaultConstructor = _constructConstructor(constructor, true);
                    break;
                default:
                    if (!z) {
                        break;
                    }
                    if (this._constructors == null) {
                        this._constructors = new ArrayList();
                    }
                    this._constructors.add(_constructConstructor(constructor, false));
                    break;
            }
        }
        if (!(this._primaryMixIn == null || (this._defaultConstructor == null && this._constructors == null))) {
            _addConstructorMixIns(this._primaryMixIn);
        }
        if (this._annotationIntrospector != null) {
            if (this._defaultConstructor != null && this._annotationIntrospector.isIgnorableConstructor(this._defaultConstructor)) {
                this._defaultConstructor = null;
            }
            if (this._constructors != null) {
                size = this._constructors.size();
                while (true) {
                    int i = size - 1;
                    if (i >= 0) {
                        if (this._annotationIntrospector.isIgnorableConstructor((AnnotatedConstructor) this._constructors.get(i))) {
                            this._constructors.remove(i);
                            size = i;
                        } else {
                            size = i;
                        }
                    }
                }
            }
        }
        this._creatorMethods = null;
        if (z) {
            for (Method method : this._class.getDeclaredMethods()) {
                if (Modifier.isStatic(method.getModifiers()) && method.getParameterTypes().length >= 1) {
                    if (this._creatorMethods == null) {
                        this._creatorMethods = new ArrayList();
                    }
                    this._creatorMethods.add(_constructCreatorMethod(method));
                }
            }
            if (!(this._primaryMixIn == null || this._creatorMethods == null)) {
                _addFactoryMixIns(this._primaryMixIn);
            }
            if (this._annotationIntrospector != null && this._creatorMethods != null) {
                size = this._creatorMethods.size();
                while (true) {
                    int i2 = size - 1;
                    if (i2 < 0) {
                        return;
                    }
                    if (this._annotationIntrospector.isIgnorableMethod((AnnotatedMethod) this._creatorMethods.get(i2))) {
                        this._creatorMethods.remove(i2);
                        size = i2;
                    } else {
                        size = i2;
                    }
                }
            }
        }
    }

    public void resolveFields(boolean z) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        _addFields(linkedHashMap, this._class);
        if (this._annotationIntrospector != null) {
            Iterator it = linkedHashMap.entrySet().iterator();
            while (it.hasNext()) {
                AnnotatedField annotatedField = (AnnotatedField) ((Entry) it.next()).getValue();
                if (this._annotationIntrospector.isIgnorableField(annotatedField)) {
                    it.remove();
                    if (z) {
                        this._ignoredFields = ArrayBuilders.addToList(this._ignoredFields, annotatedField);
                    }
                }
            }
        }
        if (linkedHashMap.isEmpty()) {
            this._fields = Collections.emptyList();
            return;
        }
        this._fields = new ArrayList(linkedHashMap.size());
        this._fields.addAll(linkedHashMap.values());
    }

    public void resolveMemberMethods(MethodFilter methodFilter, boolean z) {
        this._memberMethods = new AnnotatedMethodMap();
        AnnotatedMethodMap annotatedMethodMap = new AnnotatedMethodMap();
        _addMemberMethods(this._class, methodFilter, this._memberMethods, this._primaryMixIn, annotatedMethodMap);
        for (Class cls : this._superTypes) {
            _addMemberMethods(cls, methodFilter, this._memberMethods, this._mixInResolver == null ? null : this._mixInResolver.findMixInClassFor(cls), annotatedMethodMap);
        }
        if (this._mixInResolver != null) {
            Class findMixInClassFor = this._mixInResolver.findMixInClassFor(Object.class);
            if (findMixInClassFor != null) {
                _addMethodMixIns(methodFilter, this._memberMethods, findMixInClassFor, annotatedMethodMap);
            }
        }
        if (this._annotationIntrospector != null) {
            Iterator it;
            AnnotatedMethod annotatedMethod;
            if (!annotatedMethodMap.isEmpty()) {
                it = annotatedMethodMap.iterator();
                while (it.hasNext()) {
                    annotatedMethod = (AnnotatedMethod) it.next();
                    try {
                        Method declaredMethod = Object.class.getDeclaredMethod(annotatedMethod.getName(), annotatedMethod.getParameterClasses());
                        if (declaredMethod != null) {
                            AnnotatedMethod _constructMethod = _constructMethod(declaredMethod);
                            _addMixOvers(annotatedMethod.getAnnotated(), _constructMethod, false);
                            this._memberMethods.add(_constructMethod);
                        }
                    } catch (Exception e) {
                    }
                }
            }
            it = this._memberMethods.iterator();
            while (it.hasNext()) {
                annotatedMethod = (AnnotatedMethod) it.next();
                if (this._annotationIntrospector.isIgnorableMethod(annotatedMethod)) {
                    it.remove();
                    if (z) {
                        this._ignoredMethods = ArrayBuilders.addToList(this._ignoredMethods, annotatedMethod);
                    }
                }
            }
        }
    }

    public String toString() {
        return "[AnnotedClass " + this._class.getName() + "]";
    }
}
