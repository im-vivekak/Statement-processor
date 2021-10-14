package com.rabo.processor.handler;

import com.rabo.processor.domain.FileType;

public class FileHandlerFactory {

    public static FileHandler getFileHandler(FileType fileType) {
        switch (fileType)  {
            case CSV:
                return new CsvHandler();
            case XML:
                return new XmlHandler();
        }
        return null;
    }
}
