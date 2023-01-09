package mergefairy.gikhub.web.session;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

//세션 관리의 3가지 기능(세션 생성, 세션 조회, 세션 만료)
//세션 생성 -> 세션Id 생성, 세션 저장소에 세션Id와 보관할 값 생성, 응답쿠키 생성 전달

@Component
public class SessionManager {
    public static final String SESSION_COOKIE_NAME = "mySessionId";

    //세션 저장소
    private Map<String, Object> sessionStore = new ConcurrentHashMap<>();

    /*
    세션 생성
    */
    public void createSession(Object value, HttpServletResponse response){
        //세션 id 생성, 저장
        String sessionId = UUID.randomUUID().toString();
        sessionStore.put(sessionId, value);

        //쿠키 생성
        Cookie mySessionCookie = new Cookie(SESSION_COOKIE_NAME, sessionId);
        response.addCookie(mySessionCookie);
    }

    /*
    세션 조회
    */
    public Object getSession(HttpServletRequest request){
        Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
        //찾는 쿠키 없을 경우 null 반환
        if(sessionCookie == null) return null;

        //세션 저장소에서 세션
        return sessionStore.get(sessionCookie.getValue());
    }

    /*
    * 세션 만료
    * */
    public void expireSession(HttpServletRequest request){
        Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
        if(sessionCookie != null) {
            sessionStore.remove(sessionCookie.getValue());
        }
    }

    private Cookie findCookie(HttpServletRequest request, String cookieName) {
        if(request.getCookies() == null) return null;

        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(cookieName))
                .findAny()
                .orElse(null);
    }
}
