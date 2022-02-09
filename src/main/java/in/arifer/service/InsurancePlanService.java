package in.arifer.service;

import java.util.List;

import in.arifer.bindings.request.SearchRequest;
import in.arifer.bindings.response.PlanResponse;

public interface InsurancePlanService {
	public List<PlanResponse> searchPlans(SearchRequest request);

	public List<String> getUniquePlanNames();

	public List<String> getUniquePlanStatues();
}
