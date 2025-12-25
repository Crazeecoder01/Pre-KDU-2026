package com.bookstore.booklibrary.model;

import java.math.BigDecimal;
import java.sql.Date;

import org.antlr.v4.runtime.misc.NotNull;
import org.apache.logging.log4j.util.PerformanceSensitive;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tools.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {


    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String author;
    private String isbn;
    
    private BigDecimal price;
    private int quantity;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date publishedOn;
}
