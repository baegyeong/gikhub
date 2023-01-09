package mergefairy.gikhub.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MoneyInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "money_info_id")
    private Long id;

    private int goalMoney;
    private int deliveryFee;
    private int currentMoney;

    @OneToOne
    private TogetherTeam togetherTeam;
}
