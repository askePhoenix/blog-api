package kr.co.aske.blog_api.blog.repository;

import kr.co.aske.blog_api.blog.domain.BlogInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<BlogInfo, Long> {
}
