/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.nms.nmsutil.reflection.resolver;

import java.lang.reflect.Field;
import vn.giakhanhvn.skysim.nms.nmsutil.reflection.resolver.MemberResolver;
import vn.giakhanhvn.skysim.nms.nmsutil.reflection.resolver.ResolverQuery;
import vn.giakhanhvn.skysim.nms.nmsutil.reflection.resolver.wrapper.FieldWrapper;
import vn.giakhanhvn.skysim.nms.nmsutil.reflection.util.AccessUtil;

public class FieldResolver
extends MemberResolver<Field> {
    public FieldResolver(Class<?> clazz) {
        super(clazz);
    }

    public FieldResolver(String className) throws ClassNotFoundException {
        super(className);
    }

    @Override
    public Field resolveIndex(int index) throws IndexOutOfBoundsException, ReflectiveOperationException {
        return AccessUtil.setAccessible(this.clazz.getDeclaredFields()[index]);
    }

    @Override
    public Field resolveIndexSilent(int index) {
        try {
            return this.resolveIndex(index);
        }
        catch (IndexOutOfBoundsException | ReflectiveOperationException ex2) {
            return null;
        }
    }

    @Override
    public FieldWrapper resolveIndexWrapper(int index) {
        return new FieldWrapper(this.resolveIndexSilent(index));
    }

    public FieldWrapper resolveWrapper(String ... names) {
        return new FieldWrapper(this.resolveSilent(names));
    }

    public Field resolveSilent(String ... names) {
        try {
            return this.resolve(names);
        }
        catch (Exception e) {
            return null;
        }
    }

    public Field resolve(String ... names) throws NoSuchFieldException {
        ResolverQuery.Builder builder = ResolverQuery.builder();
        for (String name : names) {
            builder.with(name);
        }
        try {
            return (Field)super.resolve(builder.build());
        }
        catch (ReflectiveOperationException e) {
            throw (NoSuchFieldException)e;
        }
    }

    @Override
    public Field resolveSilent(ResolverQuery ... queries) {
        try {
            return this.resolve(queries);
        }
        catch (Exception e) {
            return null;
        }
    }

    @Override
    public Field resolve(ResolverQuery ... queries) throws NoSuchFieldException {
        try {
            return (Field)super.resolve(queries);
        }
        catch (ReflectiveOperationException e) {
            throw (NoSuchFieldException)e;
        }
    }

    @Override
    protected Field resolveObject(ResolverQuery query) throws ReflectiveOperationException {
        if (query.getTypes() == null || query.getTypes().length == 0) {
            return AccessUtil.setAccessible(this.clazz.getDeclaredField(query.getName()));
        }
        for (Field field : this.clazz.getDeclaredFields()) {
            if (!field.getName().equals(query.getName())) continue;
            for (Class<?> type : query.getTypes()) {
                if (!field.getType().equals(type)) continue;
                return field;
            }
        }
        return null;
    }

    public Field resolveByFirstType(Class<?> type) throws ReflectiveOperationException {
        for (Field field : this.clazz.getDeclaredFields()) {
            if (!field.getType().equals(type)) continue;
            return AccessUtil.setAccessible(field);
        }
        throw new NoSuchFieldException("Could not resolve field of type '" + type.toString() + "' in class " + this.clazz);
    }

    public Field resolveByFirstTypeSilent(Class<?> type) {
        try {
            return this.resolveByFirstType(type);
        }
        catch (Exception e) {
            return null;
        }
    }

    public Field resolveByLastType(Class<?> type) throws ReflectiveOperationException {
        Field field = null;
        for (Field field2 : this.clazz.getDeclaredFields()) {
            if (!field2.getType().equals(type)) continue;
            field = field2;
        }
        if (field == null) {
            throw new NoSuchFieldException("Could not resolve field of type '" + type.toString() + "' in class " + this.clazz);
        }
        return AccessUtil.setAccessible(field);
    }

    public Field resolveByLastTypeSilent(Class<?> type) {
        try {
            return this.resolveByLastType(type);
        }
        catch (Exception e) {
            return null;
        }
    }

    @Override
    protected NoSuchFieldException notFoundException(String joinedNames) {
        return new NoSuchFieldException("Could not resolve field for " + joinedNames + " in class " + this.clazz);
    }
}

