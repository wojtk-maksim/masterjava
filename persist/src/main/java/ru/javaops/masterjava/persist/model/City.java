package ru.javaops.masterjava.persist.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class City {
    @NonNull
    private String id;
    @NonNull
    private String name;
}
