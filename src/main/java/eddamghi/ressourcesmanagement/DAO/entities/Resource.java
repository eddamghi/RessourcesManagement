package eddamghi.ressourcesmanagement.DAO.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Resources")
public class Resource implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "YYYY-MM-dd")
    private Date purchaseDate;
    private Double price;
    private Integer note;
}
