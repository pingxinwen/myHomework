package com.example.friend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
public class User {

    public User(Long uid,String username,String password,String message) {
        this.username = username;
        this.password = password;
        this.message = message;
        this.uid = uid;
    }

    @Id
    @ReadOnlyProperty
    @JsonProperty("uid")
    @JsonView(View.publicView.class)
    private long uid;

    @JsonProperty(value = "username")
    @JsonView(View.publicView.class)
    @NotEmpty
    private String username;

    @JsonProperty(value = "password")
    @JsonView(View.detailView.class)
    @NotEmpty
    private String password;

    @OneToMany
    @JsonIgnore
    private List<Share> shares;

    @OneToMany
    @JsonIgnore
    private List<User> follows;

    //抽象用户信息，仅作代表
    @JsonView(View.publicView.class)
    @Size(max = 40)
    private String message;

}
