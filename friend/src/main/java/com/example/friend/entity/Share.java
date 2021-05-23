package com.example.friend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Data
public class Share {
    @Id
    @ReadOnlyProperty
    private long sid;

    @JsonIgnore
    @ManyToOne
    @NotNull
    private User user;

    @Size(max = 180)
    private String content;

    private Timestamp timestamp;
}
