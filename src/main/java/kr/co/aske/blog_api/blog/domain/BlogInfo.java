package kr.co.aske.blog_api.blog.domain;

import kr.co.aske.blog_api.user.domain.UserInfo;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "blog_info")
public class BlogInfo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 블로그 주인
    @ManyToOne
    @JoinColumn(name = "owner")
    private UserInfo owner;

    // 블로그 도메인 키워드
    @Column(name = "blog_domain")
    private String domain;

    @CreationTimestamp
    private LocalDateTime createAt;

    @UpdateTimestamp
    private LocalDateTime updateAt;

    @Column(name = "is_enable")
    private Boolean isEnable;


    @Builder
    public BlogInfo(Long id, UserInfo owner, String domain, LocalDateTime createAt, LocalDateTime updateAt, Boolean isEnable) {
        this.id = id;
        this.owner = owner;
        this.domain = domain;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.isEnable = isEnable;
    }
}
