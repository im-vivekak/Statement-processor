package com.rabo.processor.domain;

import java.util.HashMap;
import java.util.Map;


public enum FileType {
    CSV("text/csv"),
    XML("application/xml", "text/xml"),
    INVALID("");

    private String[] mimeType;

    private static final Map<String, FileType> fileTypeCache;


    FileType(String... mimeType) {
        this.mimeType = mimeType;
    }

    static {
        fileTypeCache = new HashMap<>();
        for (FileType type : FileType.values()) {
            for (String instanceTypes : type.mimeType) {
                fileTypeCache.put(instanceTypes, type);
            }

        }
    }

    public static FileType findByKey(String mimeType) {
        return fileTypeCache.get(mimeType) == null ? INVALID : fileTypeCache.get(mimeType);
    }
}
