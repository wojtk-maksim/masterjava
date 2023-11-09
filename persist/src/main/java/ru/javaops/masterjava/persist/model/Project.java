package ru.javaops.masterjava.persist.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Project {
    @NonNull
    private String name;
    @NonNull
    private String description;
}
