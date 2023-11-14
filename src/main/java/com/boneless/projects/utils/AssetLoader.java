package com.boneless.projects.utils;

import java.io.InputStream;
import java.util.Objects;

public class AssetLoader {
    public static InputStream loadAsset(String assetPath) {
        ClassLoader classLoader = AssetLoader.class.getClassLoader();
        return classLoader.getResourceAsStream(Objects.requireNonNull(assetPath));
    }
}
