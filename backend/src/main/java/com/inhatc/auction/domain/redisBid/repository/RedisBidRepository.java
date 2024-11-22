package com.inhatc.auction.domain.redisBid.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.inhatc.auction.domain.redisBid.entity.RedisBid;

@Repository
public interface RedisBidRepository extends CrudRepository<RedisBid, String> {
    // 모든 입찰 내역 조회
    List<RedisBid> findAllByOrderByBidTimeAsc();

    // 경매별 입찰 내역 조회 (입찰 먼저 한 순서대로)
    List<RedisBid> findByAuctionIdOrderByBidTimeAsc(Long auctionId);

    // 경매별 입찰 내역 조회 (입찰 금액이 많은 순부터)
    List<RedisBid> findByAuctionIdOrderByBidAmountDesc(Long auctionId);

    // 경매별 최고 입찰 건 조회
    Optional<RedisBid> findFirstByAuctionIdOrderByBidAmountDesc(Long auctionId);

    // 경매별 사용자별 입찰 존재 여부 조회
    boolean existsByAuctionIdAndUserIdAndBidAmount(Long auctionId, Long userId, Long bidAmount);
}