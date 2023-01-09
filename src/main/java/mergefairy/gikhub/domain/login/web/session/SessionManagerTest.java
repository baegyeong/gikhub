package mergefairy.gikhub.web.session;

import mergefairy.gikhub.domain.User;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionManagerTest {

    SessionManager sessionManager = new SessionManager();

    @Test
    public void sessionTest(){
        MockHttpServletResponse response = new MockHttpServletResponse();
        User user = new User();
        sessionManager.createSession(user, response);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies());

        Object result = sessionManager.getSession(request);
        assertThat(result).isEqualTo(user);

        sessionManager.expireSession(request);
        Object expired = sessionManager.getSession(request);
        assertThat(expired).isNull();
    }
}
