/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class PaginationList<T>
extends ArrayList<T> {
    private int elementsPerPage;

    public PaginationList(Collection<T> collection, int elementsPerPage) {
        super(collection);
        this.elementsPerPage = elementsPerPage;
    }

    public PaginationList(int elementsPerPage) {
        this.elementsPerPage = elementsPerPage;
    }

    public PaginationList(int elementsPerPage, T ... elements) {
        super(Arrays.asList(elements));
        this.elementsPerPage = elementsPerPage;
    }

    public int getElementsPerPage() {
        return this.elementsPerPage;
    }

    public void setElementsPerPage(int elementsPerPage) {
        this.elementsPerPage = elementsPerPage;
    }

    public int getPageCount() {
        return (int)Math.ceil((double)this.size() / (double)this.elementsPerPage);
    }

    public List<T> getPage(int page) {
        if (page < 1 || page > this.getPageCount()) {
            return null;
        }
        int startIndex = (page - 1) * this.elementsPerPage;
        int endIndex = Math.min(startIndex + this.elementsPerPage, this.size());
        return this.subList(startIndex, endIndex);
    }

    public List<List<T>> getPages() {
        ArrayList<List<T>> pages = new ArrayList<List<T>>();
        for (int i = 1; i <= this.getPageCount(); ++i) {
            pages.add(this.getPage(i));
        }
        return pages;
    }

    public void addAll(T[] t) {
        for (T o : t) {
            this.add(o);
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 1; i <= this.getPageCount(); ++i) {
            res.append("Page ").append(i).append(": ").append("\n");
            for (T element : this.getPage(i)) {
                res.append(" - ").append(element).append("\n");
            }
        }
        return res.toString();
    }
}

