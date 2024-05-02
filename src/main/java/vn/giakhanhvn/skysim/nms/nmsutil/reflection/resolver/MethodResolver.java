/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.nms.nmsutil.reflection.resolver;

import java.lang.reflect.Method;
import vn.giakhanhvn.skysim.nms.nmsutil.reflection.resolver.MemberResolver;
import vn.giakhanhvn.skysim.nms.nmsutil.reflection.resolver.ResolverQuery;
import vn.giakhanhvn.skysim.nms.nmsutil.reflection.resolver.wrapper.MethodWrapper;
import vn.giakhanhvn.skysim.nms.nmsutil.reflection.util.AccessUtil;

public class MethodResolver
extends MemberResolver<Method> {
    public MethodResolver(Class<?> clazz) {
        super(clazz);
    }

    public MethodResolver(String className) throws ClassNotFoundException {
        super(className);
    }

    public Method resolveSignature(String ... signatures) throws ReflectiveOperationException {
        for (Method method : this.clazz.getDeclaredMethods()) {
            String methodSignature = MethodWrapper.getMethodSignature(method);
            for (String s : signatures) {
                if (!s.equals(methodSignature)) continue;
                return AccessUtil.setAccessible(method);
            }
        }
        return null;
    }

    public Method resolveSignatureSilent(String ... signatures) {
        try {
            return this.resolveSignature(signatures);
        }
        catch (ReflectiveOperationException ignored) {
            return null;
        }
    }

    public MethodWrapper resolveSignatureWrapper(String ... signatures) {
        return new MethodWrapper(this.resolveSignatureSilent(signatures));
    }

    @Override
    public Method resolveIndex(int index) throws IndexOutOfBoundsException, ReflectiveOperationException {
        return AccessUtil.setAccessible(this.clazz.getDeclaredMethods()[index]);
    }

    @Override
    public Method resolveIndexSilent(int index) {
        try {
            return this.resolveIndex(index);
        }
        catch (IndexOutOfBoundsException | ReflectiveOperationException ex2) {
            return null;
        }
    }

    @Override
    public MethodWrapper resolveIndexWrapper(int index) {
        return new MethodWrapper(this.resolveIndexSilent(index));
    }

    public MethodWrapper resolveWrapper(String ... names) {
        return new MethodWrapper(this.resolveSilent(names));
    }

    public MethodWrapper resolveWrapper(ResolverQuery ... queries) {
        return new MethodWrapper(this.resolveSilent(queries));
    }

    public Method resolveSilent(String ... names) {
        try {
            return this.resolve(names);
        }
        catch (Exception e) {
            return null;
        }
    }

    @Override
    public Method resolveSilent(ResolverQuery ... queries) {
        return (Method)super.resolveSilent(queries);
    }

    public Method resolve(String ... names) throws NoSuchMethodException {
        ResolverQuery.Builder builder = ResolverQuery.builder();
        for (String name : names) {
            builder.with(name);
        }
        return this.resolve(builder.build());
    }

    @Override
    public Method resolve(ResolverQuery ... queries) throws NoSuchMethodException {
        try {
            return (Method)super.resolve(queries);
        }
        catch (ReflectiveOperationException e) {
            throw (NoSuchMethodException)e;
        }
    }

    @Override
    protected Method resolveObject(ResolverQuery query) throws ReflectiveOperationException {
        for (Method method : this.clazz.getDeclaredMethods()) {
            if (!method.getName().equals(query.getName()) || query.getTypes().length != 0 && !MethodResolver.ClassListEqual(query.getTypes(), method.getParameterTypes())) continue;
            return AccessUtil.setAccessible(method);
        }
        throw new NoSuchMethodException();
    }

    @Override
    protected NoSuchMethodException notFoundException(String joinedNames) {
        return new NoSuchMethodException("Could not resolve method for " + joinedNames + " in class " + this.clazz);
    }

    static boolean ClassListEqual(Class<?>[] l1, Class<?>[] l2) {
        boolean equal = true;
        if (l1.length != l2.length) {
            return false;
        }
        for (int i = 0; i < l1.length; ++i) {
            if (l1[i] == l2[i]) continue;
            equal = false;
            break;
        }
        return equal;
    }
}

