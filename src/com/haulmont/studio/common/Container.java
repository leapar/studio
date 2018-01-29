package com.haulmont.studio.common;

import javax.annotation.Nullable;

public interface Container extends IDispose {
    <T> T getBean(Class<T> var1);

    @Nullable
    <T> T getComponent(Class<T> var1);
}
