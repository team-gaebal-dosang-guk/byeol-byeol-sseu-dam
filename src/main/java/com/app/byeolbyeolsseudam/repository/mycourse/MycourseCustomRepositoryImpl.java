package com.app.byeolbyeolsseudam.repository.mycourse;

import com.app.byeolbyeolsseudam.domain.course.CourseDTO;
import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.domain.mycourse.MycourseDTO;
import com.app.byeolbyeolsseudam.domain.mycourse.QMycourseDTO;
import com.app.byeolbyeolsseudam.entity.course.QCourse;
import com.app.byeolbyeolsseudam.entity.mycourse.Mycourse;
import com.app.byeolbyeolsseudam.entity.mycourse.QMycourse;
import com.app.byeolbyeolsseudam.entity.spot.QSpot;
import com.app.byeolbyeolsseudam.repository.mybadge.MybadgeCustomRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

import static com.app.byeolbyeolsseudam.entity.course.QCourse.course;
import static com.app.byeolbyeolsseudam.entity.mycourse.QMycourse.mycourse;
import static com.app.byeolbyeolsseudam.entity.spot.QSpot.spot;

@Repository
@RequiredArgsConstructor
public class MycourseCustomRepositoryImpl implements MycourseCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<MycourseDTO> selectMyCourseList(Long memberId){
        return jpaQueryFactory.select(new QMycourseDTO(mycourse.mycourseId,
                mycourse.courseFinishedStatus, mycourse.member.memberId, mycourse.course.courseId,
                mycourse.course.courseName, mycourse.spot.spotId, mycourse.spot.spotName,
                mycourse.spot.spotNumber, mycourse.createdDate))
                .from(mycourse)
                .where(mycourse.member.memberId.eq(memberId))
                .orderBy(mycourse.createdDate.desc())
                .orderBy(mycourse.course.courseName.asc())
                .orderBy(mycourse.spot.spotName.asc())
                .fetch();
    }

    @Override
    public MycourseDTO selectMyCourse(Long memberId){
        return jpaQueryFactory.select(new QMycourseDTO(mycourse.mycourseId,
                mycourse.courseFinishedStatus, mycourse.member.memberId, mycourse.course.courseId,
                mycourse.course.courseName, mycourse.spot.spotId, mycourse.spot.spotName,
                mycourse.spot.spotNumber, mycourse.createdDate))
                .from(mycourse)
                .where(mycourse.member.memberId.eq(memberId))
                .orderBy(mycourse.updatedDate.desc())
                .limit(1)
                .fetchOne();
    }

    @Override
    public Mycourse findActiveCourse(MemberDTO memberDTO, String courseName){
        Optional<Mycourse> courseOtional = Optional.ofNullable(
                jpaQueryFactory.selectFrom(mycourse)
                        .from(mycourse)
                        .where(mycourse.member.memberId.eq(memberDTO.getMemberId())
                                .and(mycourse.course.courseName.contains(courseName))
                                .and(mycourse.spot.spotNumber.ne(5)))
                        .fetchOne()
        );
        return courseOtional.orElse(null);
    }

    @Override
    public int badgeCondition(Long memberId, String keyword){
        List<MycourseDTO> mycourses =  jpaQueryFactory.select(new QMycourseDTO(mycourse.mycourseId,
                mycourse.courseFinishedStatus, mycourse.member.memberId, mycourse.course.courseId,
                mycourse.course.courseName, mycourse.spot.spotId, mycourse.spot.spotName,
                mycourse.spot.spotNumber, mycourse.createdDate))
                .from(mycourse)
                .where(
                        courseIdContains(keyword),
                        mycourse.member.memberId.eq(memberId),
                        mycourse.spot.spotNumber.eq(5)
                        )
                .fetch();

        return mycourses.size();
    }

    public BooleanExpression courseIdContains(String keywrod){
        return StringUtils.hasText(keywrod)? mycourse.course.courseName.contains(keywrod) : null;
    }
}
