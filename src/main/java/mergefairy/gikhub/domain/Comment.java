package mergefairy.gikhub.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Entity
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id @GeneratedValue
    @Column(name = "COMMENT_ID")
    private Long id;
    private String content;
    private LocalDate localtime;
}
