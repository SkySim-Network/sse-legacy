/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.nms.nmsutil.reflection.resolver;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import vn.giakhanhvn.skysim.nms.nmsutil.reflection.resolver.ResolverQuery;

public abstract class ResolverAbstract<T> {
    protected final Map<ResolverQuery, T> resolvedObjects = new ConcurrentHashMap<ResolverQuery, T>();

    protected T resolveSilent(ResolverQuery ... queries) {
        try {
            return this.resolve(queries);
        }
        catch (Exception e) {
            return null;
        }
    }

    protected T resolve(ResolverQuery ... queries) throws ReflectiveOperationException {
        if (queries == null || queries.length <= 0) {
            throw new IllegalArgumentException("Given possibilities are empty");
        }
        int length = queries.length;
        for (int i = 0; i < length; ++i) {
            ResolverQuery query = queries[i];
            if (this.resolvedObjects.containsKey(query)) {
                return this.resolvedObjects.get(query);
            }
            try {
                T resolved = this.resolveObject(query);
                this.resolvedObjects.put(query, resolved);
                return resolved;
            }
            catch (ReflectiveOperationException e) {
                continue;
            }
        }
        throw this.notFoundException(Arrays.asList(queries).toString());
    }

    protected abstract T resolveObject(ResolverQuery var1) throws ReflectiveOperationException;

    protected ReflectiveOperationException notFoundException(String joinedNames) {
        return new ReflectiveOperationException("Objects could not be resolved: " + joinedNames);
    }
}

