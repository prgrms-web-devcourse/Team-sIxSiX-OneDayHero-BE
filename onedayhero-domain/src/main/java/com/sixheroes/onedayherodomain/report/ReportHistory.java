package com.sixheroes.onedayherodomain.report;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "report_histories")
@Entity
public class ReportHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reporter_id")
    private Long reporterId;

    @Column(name = "report_category_id")
    private Long reportCategoryId;

    @Column(name = "reported_id")
    private Long reportedId;

    @Lob
    @Column(name = "content")
    private String content;
}
