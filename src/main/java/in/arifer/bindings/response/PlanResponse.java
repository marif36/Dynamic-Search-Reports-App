package in.arifer.bindings.response;

import lombok.Data;

@Data
public class PlanResponse {
	private Integer planId;
	private String planName;
	private String planStatus;
	private String planHolderName;
	private long planHolderSsn;
}
