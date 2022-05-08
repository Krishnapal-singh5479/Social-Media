package com.social.media.application.repository;

import com.social.media.application.model.UserPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPostRepository extends JpaRepository<UserPost,Long> {
}
