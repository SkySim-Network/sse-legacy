/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.nms.nmsutil.reflection.resolver;

import java.lang.reflect.Constructor;
import vn.giakhanhvn.skysim.nms.nmsutil.reflection.resolver.MemberResolver;
import vn.giakhanhvn.skysim.nms.nmsutil.reflection.resolver.ResolverQuery;
import vn.giakhanhvn.skysim.nms.nmsutil.reflection.resolver.wrapper.ConstructorWrapper;
import vn.giakhanhvn.skysim.nms.nmsutil.reflection.util.AccessUtil;

public class ConstructorResolver
extends MemberResolver<Constructor> {
    public ConstructorResolver(Class<?> clazz) {
        super(clazz);
    }

    public ConstructorResolver(String className) throws ClassNotFoundException {
        super(className);
    }

    @Override
    public Constructor resolveIndex(int index) throws IndexOutOfBoundsException, ReflectiveOperationException {
        return AccessUtil.setAccessible(this.clazz.getDeclaredConstructors()[index]);
    }

    @Override
    public Constructor resolveIndexSilent(int index) {
        try {
            return this.resolveIndex(index);
        }
        catch (IndexOutOfBoundsException | ReflectiveOperationException ex2) {
            return null;
        }
    }

    @Override
    public ConstructorWrapper resolveIndexWrapper(int index) {
        return new ConstructorWrapper(this.resolveIndexSilent(index));
    }

    public ConstructorWrapper resolveWrapper(Class<?>[] ... types) {
        return new ConstructorWrapper(this.resolveSilent(types));
    }

    public Constructor resolveSilent(Class<?>[] ... types) {
        try {
            return this.resolve(types);
        }
        catch (Exception e) {
            return null;
        }
    }

    public Constructor resolve(Class<?>[] ... types) throws NoSuchMethodException {
        ResolverQuery.Builder builder = ResolverQuery.builder();
        for (Class<?>[] type : types) {
            builder.with(type);
        }
        try {
            return (Constructor)super.resolve(builder.build());
        }
        catch (ReflectiveOperationException e) {
            throw (NoSuchMethodException)e;
        }
    }

    @Override
    protected Constructor resolveObject(ResolverQuery query) throws ReflectiveOperationException {
        return AccessUtil.setAccessible(this.clazz.getDeclaredConstructor(query.getTypes()));
    }

    public Constructor resolveFirstConstructor() throws ReflectiveOperationException {
        Constructor<?>[] declaredConstructors = this.clazz.getDeclaredConstructors();
        int length = declaredConstructors.length;
        boolean n = false;
        if (0 < length) {
            Constructor<?> constructor = declaredConstructors[0];
            return AccessUtil.setAccessible(constructor);
        }
        return null;
    }

    public Constructor resolveFirstConstructorSilent() {
        try {
            return this.resolveFirstConstructor();
        }
        catch (Exception e) {
            return null;
        }
    }

    public Constructor resolveLastConstructor() throws ReflectiveOperationException {
        Constructor<?> constructor = null;
        Constructor<?>[] declaredConstructors = this.clazz.getDeclaredConstructors();
        int length = declaredConstructors.length;
        for (int i = 0; i < length; ++i) {
            Constructor<?> constructor2 = constructor = declaredConstructors[i];
        }
        if (constructor != null) {
            return AccessUtil.setAccessible(constructor);
        }
        return null;
    }

    public Constructor resolveLastConstructorSilent() {
        try {
            return this.resolveLastConstructor();
        }
        catch (Exception e) {
            return null;
        }
    }

    @Override
    protected NoSuchMethodException notFoundException(String joinedNames) {
        return new NoSuchMethodException("Could not resolve constructor for " + joinedNames + " in class " + this.clazz);
    }
}

