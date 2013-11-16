package ph.gov.kabantayngbayan.porkchop.models;

public class SaobBudget extends SaobBudgetBase {

	private SaobBudgetCurrentYearBudget current_year_budget;
	private SaobBudgetContinuingAppro continuing_appro;

	public SaobBudgetCurrentYearBudget getCurrent_year_budget() {
		return current_year_budget;
	}

	public void setCurrent_year_budget(SaobBudgetCurrentYearBudget current_year_budget) {
		this.current_year_budget = current_year_budget;
	}

	public SaobBudgetContinuingAppro getContinuing_appro() {
		return continuing_appro;
	}

	public void setContinuing_appro(SaobBudgetContinuingAppro continuing_appro) {
		this.continuing_appro = continuing_appro;
	}

}
