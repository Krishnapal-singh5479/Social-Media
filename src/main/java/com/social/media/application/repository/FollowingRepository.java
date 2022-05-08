package com.social.media.application.repository;

import com.social.media.application.model.Following;
import com.social.media.application.model.FollowingKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowingRepository extends JpaRepository<Following, FollowingKey> {
    List<Following> findAllByUserId(Long userid);
    List<Following> findAllByGotId(Long gotId);
}
