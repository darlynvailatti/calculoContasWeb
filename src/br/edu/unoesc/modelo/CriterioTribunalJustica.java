package br.edu.unoesc.modelo;

public enum CriterioTribunalJustica {
	balancoBalancete("Balanço ou Balancete"),
	balanceteMensal("Balancete mensal"),
	balancoUltimoExerContab("Balanço do último exercício contabilizado");
	
	private String value;
    private CriterioTribunalJustica(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
