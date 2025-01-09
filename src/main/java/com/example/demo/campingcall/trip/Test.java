package com.example.demo.campingcall.trip;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder()
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "response")
public class Test {

	private String response;  // 필드 정의

    // setter 메소드 정의
    public void setResponse(String response) {
        this.response = response;
    }

    // getter 메소드 (필요하면 추가)
    public String getResponse() {
        return response;
    }
}
