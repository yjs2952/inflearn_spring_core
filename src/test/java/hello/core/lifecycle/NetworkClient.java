package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient implements InitializingBean, DisposableBean {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect() {
        System.out.println("connect: "+ url);
    }

    public void call(String messege) {
        System.out.println("call: " + url + ", message = " + messege);
    }

    // 서비스 종료시 호출
    public void disconnect() {
        System.out.println("close: " + url);
    }

    /*
    InitializingBean 에서 제공하는 메소드
    스프링 전용 인터페이스
    초기화 콜백 : 빈이 생성되고, 빈의 의존관계 주입이 완료된 후 호출
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }

    /*
    DisposableBean 에서 제공하는 메소드
    스프링 전용 인터페이스
    소멸전 콜백 : 빈이 소멸되기 직전에 호출
     */
    @Override
    public void destroy() throws Exception {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
}
