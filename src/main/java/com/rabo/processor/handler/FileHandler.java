package com.rabo.processor.handler;

import com.rabo.processor.domain.FileRecord;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileHandler {
    List<FileRecord> process(MultipartFile file);
}
