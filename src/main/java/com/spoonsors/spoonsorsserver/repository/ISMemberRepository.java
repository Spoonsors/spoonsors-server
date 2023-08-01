package com.spoonsors.spoonsorsserver.repository;

import com.spoonsors.spoonsorsserver.entity.SMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISMemberRepository extends JpaRepository<SMember, String> {
}
