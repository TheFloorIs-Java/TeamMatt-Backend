package App.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class PublishedGame {
    @Id
    private int id;
    @Column
    private String title;
    @Column
    private String gameType;
    @Column
    private String gameData;
    @Column
    private User user;
    @Column
    private int numberOfPlays;
}
