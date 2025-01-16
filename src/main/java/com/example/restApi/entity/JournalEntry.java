package com.example.restApi.entity;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.restApi.enums.Sentiment;
import com.mongodb.lang.NonNull;

import lombok.Data;
import lombok.NoArgsConstructor;

//it means any instance of JournalEntry will be treated as the row of the database
@Document(collection = "journal_entries")
@Data
@NoArgsConstructor
public class JournalEntry{

    //unique key
    @Id
    private ObjectId id;

    @NonNull
    private String title;

    private String content;

    private LocalDateTime date;

    private Sentiment sentiment;
}

