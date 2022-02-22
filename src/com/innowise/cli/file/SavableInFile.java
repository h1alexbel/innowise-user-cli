package com.innowise.cli.file;

import java.io.Serializable;
import java.nio.file.Path;

public interface SavableInFile<T extends Serializable> {

    void saveInFile(T entity, Path path);
}