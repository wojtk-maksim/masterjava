package ru.javaops.masterjava.persist.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Group {
    @NonNull
    private String name;
    @NonNull
    private String project;
    @NonNull
    private GroupType type;
}
