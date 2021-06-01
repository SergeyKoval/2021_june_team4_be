package com.exadel.discount.entity;

import com.exadel.discount.dto.favorite.BaseFavoriteDto;
import com.exadel.discount.dto.favorite.FavoriteDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "favorites")

public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public static Favorite from(FavoriteDto favoriteDto) {
        Favorite favorite = new Favorite();
        return favorite;
    }

    public static Favorite from(BaseFavoriteDto baseFavoriteDto) {
        Favorite favorite = new Favorite();
        return favorite;
    }
}