package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Bean에 등록해줘야 하므로
 * @Component 를 쓰거나 SpringConfig을 통해 수동으로 추가해줘야함
 */

@Aspect
@Component
public class TimeTraceAop {

    /**
     * AOP 는 메서드의 호출시간을 측정하기 위해서 사용
     * 공통 시간측정 로직을 만든 뒤 측정하고싶은곳을 선언만 해주면 측정이 됨
     */
    //@Around 가 측정하고 싶은곳을 타겟하는 어노테이션s
    @Around("execution(* hello.hellospring..*(..))")  // >> 옆에 선언은 해당 패키지 아래 모두 적용
    public Object execute(ProceedingJoinPoint joinPoint)  throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START : " + joinPoint.toString());

        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END : " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
