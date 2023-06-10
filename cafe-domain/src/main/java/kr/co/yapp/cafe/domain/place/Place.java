package kr.co.yapp.cafe.domain.place;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@EqualsAndHashCode
@EntityListeners(AuditingEntityListener.class)
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private Boolean deleted = false;

    public static Place from(PlaceCreateVo placeCreateVo) {
        Place place = new Place();
        place.name = placeCreateVo.getName();
        return place;
    }

    public void update(PlaceUpdateVo placeUpdateVo) {
        name = placeUpdateVo.getName();
    }

    public void delete() {
        deleted = true;
    }
}
