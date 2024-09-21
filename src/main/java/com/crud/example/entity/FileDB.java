package com.crud.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "files")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileDB {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")

    private String id;

    private String name;

    private String type;

    @Lob
    @Column(name = "data", columnDefinition = "LONGBLOB")
    private byte[] data;

    public FileDB(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }

}
