package ru.javaops.masterjava.service.mail;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Mail {
    @NonNull
    private LocalDateTime datetime;

    @NonNull
    private String subject;

    @NonNull
    private String body;
}
