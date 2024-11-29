package org.example.pracainzynierska.core.entities.log;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.pracainzynierska.core.entities.BaseEntity;
import org.example.pracainzynierska.core.entities.user.UserEntity;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "activity_logs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityLog extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private LocalDateTime executionTime;

    private String method;

    private String endpoint;
}
