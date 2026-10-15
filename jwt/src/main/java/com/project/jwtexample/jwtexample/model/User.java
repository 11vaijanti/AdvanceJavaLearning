package com.project.jwtexample.jwtexample.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
//These are lombok annotations. They will generate all methods implicitly
public class User {
    private String userId;
    private String name;
    private String mail;
}
