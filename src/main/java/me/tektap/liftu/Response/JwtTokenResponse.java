package me.tektap.liftu.Response;

public class JwtTokenResponse extends BaseResponse {
	private final String token;

	public JwtTokenResponse(int status, String token) {
		super(status);
		this.token = token;
	}

	public String getToken() {
		return token;
	}
}